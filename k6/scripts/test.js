import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1s', target: 3 },
        { duration: '2s', target: 3 },
        { duration: '1s', target: 0 },
    ],
    thresholds: {
        'http_req_duration': ['p(95)<200'],
        'http_req_failed': ['rate<0.01'],
    },
};

export default function () {
    const username = 'root';
    const password = 'root';

    // 1. Login to get JWT token
    const loginPayload = JSON.stringify({
        username: username,
        password: password,
    });

    const loginParams = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const loginRes = http.post('http://localhost:8081/auth/login', loginPayload, loginParams);

    check(loginRes, {
        'Login successful': (r) => r.status === 200,
    });

    if (loginRes.status !== 200) {
        console.error(`❌ Login failed for ${username}: ${loginRes.status} ${loginRes.body}`);
        return; // Stop execution if login fails
    }

    const accessToken = loginRes.json('accessToken');
    console.log(`✅ Login successful for ${username}`);

    const authHeaders = {
        headers: {
            'Authorization': `Bearer ${accessToken}`,
            'Content-Type': 'application/json',
        },
    };

    // 2. Create a new product
    const prodName = `ProdName-${__VU}-${__ITER}`;
    const createPayload = JSON.stringify({
        productCode: __VU, // Use VU number for unique product code
        prodName: prodName,
        detailDesc: `Description for ${prodName}`,
        productType: { productTypeNo: 1 },
        graphicalAppearance: { graphicalAppearanceNo: 1 },
        colourGroup: { colourGroupCode: 1 },
        perceivedColourValue: { perceivedColourValueId: 1 },
        perceivedColourMaster: { perceivedColourMasterId: 1 },
        department: { departmentNo: 1 },
        index: { indexCode: 'A' },
        indexGroup: { indexGroupNo: 1 },
        section: { sectionNo: 1 },
        garmentGroup: { garmentGroupNo: 101 },
        productGroup: { productGroupName: "Accessories" }
    });

    const createRes = http.post('http://localhost:8084/products', createPayload, authHeaders);

    check(createRes, {
        'Product creation successful': (r) => r.status === 201,
    });

    let productId;
    if (createRes.status === 201) {
        // In Spring Data JPA, the returned object from save has the ID.
        // Let's assume the response body is the created product.
        productId = createRes.json('productId');
        console.log(`✅ Product created successfully: ${productId}`);
    } else {
        console.error(`❌ Product creation failed: ${createRes.status} ${createRes.body}`);
        return;
    }
    sleep(1);

    // 3. Read the product
    const readRes = http.get(`http://localhost:8084/products/${productId}`, authHeaders);

    check(readRes, {
        'Product read successful': (r) => r.status === 200,
        'Product name is correct': (r) => r.json('prodName') === prodName,
    });

    if (readRes.status === 200) {
        console.log(`✅ Product read successfully: ${productId}`);
    } else {
        console.error(`❌ Product read failed: ${readRes.status} ${readRes.body}`);
    }
    sleep(1);

    // 4. Update the product
    const updatedProdName = `Updated-${prodName}`;
    const updatePayload = JSON.stringify({
        productCode: __VU + 1000, // new product code
        prodName: updatedProdName,
        detailDesc: `Updated description for ${prodName}`,
        productType: { productTypeNo: 1 },
        graphicalAppearance: { graphicalAppearanceNo: 1 },
        colourGroup: { colourGroupCode: 1 },
        perceivedColourValue: { perceivedColourValueId: 1 },
        perceivedColourMaster: { perceivedColourMasterId: 1 },
        department: { departmentNo: 1 },
        index: { indexCode: 'B' },
        indexGroup: { indexGroupNo: 2 },
        section: { sectionNo: 2 },
        garmentGroup: { garmentGroupNo: 102 },
        productGroup: { productGroupName: "Garment" }
    });

    const updateRes = http.put(`http://localhost:8084/products/${productId}`, updatePayload, authHeaders);

    check(updateRes, {
        'Product update successful': (r) => r.status === 200,
        'Updated product name is correct': (r) => r.json('prodName') === updatedProdName,
    });

    if (updateRes.status === 200) {
        console.log(`✅ Product updated successfully: ${productId}`);
    } else {
        console.error(`❌ Product update failed: ${updateRes.status} ${updateRes.body}`);
    }
    sleep(1);

    // 5. Delete the product
    const deleteRes = http.del(`http://localhost:8084/products/${productId}`, null, authHeaders);

    check(deleteRes, {
        'Product deletion successful': (r) => r.status === 204,
    });

    if (deleteRes.status === 204) {
        console.log(`✅ Product deleted successfully: ${productId}`);
    } else {
        console.error(`❌ Product deletion failed: ${deleteRes.status} ${deleteRes.body}`);
    }
    sleep(1);
}
