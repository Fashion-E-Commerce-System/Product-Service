package com.ecommerce.backend.service;

import com.ecommerce.backend.domain.OutboxEvent;
import com.ecommerce.backend.repository.OutboxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxEventService {

    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void createOutboxEvent(String aggregateType, String aggregateId, String eventType, Object payload) {
        try {
            String payloadJson = objectMapper.writeValueAsString(payload);
            OutboxEvent outboxEvent = OutboxEvent.builder()
                    .aggregateType(aggregateType)
                    .aggregateId(aggregateId)
                    .eventType(eventType)
                    .payload(payloadJson)
                    .createdAt(LocalDateTime.now())
                    .build();
            outboxEventRepository.save(outboxEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing payload for outbox event", e);
        }
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void publishOutboxEvents() {
        List<OutboxEvent> events = outboxEventRepository.findAllByProcessedAtIsNullOrderByCreatedAtAsc();
        if (events.isEmpty()) {
            return;
        }

        log.info("Found {} unprocessed events in outbox to publish.", events.size());

        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send("product-events", event.getAggregateId(), event.getPayload());
                log.debug("Event {} for aggregate {} published to Kafka topic '{}'.",
                        event.getEventType(), event.getAggregateId(), "product-events");

                event.setProcessedAt(LocalDateTime.now());
                outboxEventRepository.save(event);

            } catch (Exception e) {
                log.error("Failed to publish event {} for aggregate {}: {}",
                        event.getEventType(), event.getAggregateId(), e.getMessage());
            }
        }
    }
}