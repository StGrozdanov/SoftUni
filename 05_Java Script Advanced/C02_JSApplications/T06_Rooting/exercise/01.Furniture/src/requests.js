export async function getAllFurniture() {
    const response = await fetch('http://localhost:3030/data/catalog');
    const data = await response.json();
    return data;
}

export async function createFurniture(data) {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch('http://localhost:3030/data/catalog', options);

    return await response.json();
}

export async function getFurniture(id) {
    const response = await fetch(`http://localhost:3030/data/catalog/${id}`);
    const data = await response.json();
    return data;
}

export async function updateFurniture(id, data) {
    const options = {
        method: 'PUT',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`http://localhost:3030/data/catalog/${id}`, options);

    await response.json();
}

export async function deleteFurniture(id) {
    const options = {
        method: 'delete',
        headers: {}
    };
    
    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`http://localhost:3030/data/catalog/${id}`, options);

    await response.json();
}

export async function getMyFurniture(userId) {
    const response = await fetch(`http://localhost:3030/data/catalog?where=_ownerId%3D%22${userId}%22`);
    const data = await response.json();
    return data;
}