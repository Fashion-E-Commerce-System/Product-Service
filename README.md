# Product Service Event Protocol

This document describes the event protocol for the Product Service, which uses an outbox pattern to publish events for product changes.

## Event Structure

Events are published to a message broker (e.g., Kafka) from the `outbox_events` table. Each event has the following structure:

```json
{
  "id": 123,
  "aggregateType": "product",
  "aggregateId": "42",
  "eventType": "PRODUCT_CREATED",
  "payload": "{\"productId\":42,\"prodName\":\"New Product Name\"}",
  "createdAt": "2025-12-02T10:00:00.000000",
  "processedAt": null
}
```

- `id`: The unique ID of the outbox event.
- `aggregateType`: The type of the aggregate root that the event belongs to. For this service, it will always be `"product"`.
- `aggregateId`: The ID of the aggregate root that was changed.
- `eventType`: The type of event that occurred.
- `payload`: A JSON string containing the details of the event.
- `createdAt`: The timestamp when the event was created.
- `processedAt`: The timestamp when the event was processed and published. It is `null` until the event is published.

## Event Types

### `PRODUCT_CREATED`

Published when a new product is created.

**`eventType`**: `PRODUCT_CREATED`

**Payload**:
```json
{
  "productId": 42,
  "prodName": "New Product Name"
}
```

### `PRODUCT_UPDATED`

Published when an existing product is updated.

**`eventType`**: `PRODUCT_UPDATED`

**Payload**:
```json
{
  "productId": 42,
  "prodName": "Updated Product Name"
}
```

### `PRODUCT_DELETED`

Published when a product is deleted.

**`eventType`**: `PRODUCT_DELETED`

**Payload**:
The payload for a `PRODUCT_DELETED` event is simply the ID of the product that was deleted.

```json
42
```
