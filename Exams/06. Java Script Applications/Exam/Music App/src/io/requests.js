const baseUrl = 'http://localhost:3030/data/albums';

export async function getAll() {
    const response = await fetch(baseUrl + '?sortBy=_createdOn%20desc&distinct=name');
    const data = await response.json();
    return data;
}

export async function create(data) {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(baseUrl, options);

    return await response.json();
}

export async function getSingle(id) {
    const response = await fetch(`${baseUrl}/${id}`);
    const data = await response.json();
    return data;
}

export async function update(id, data) {
    const options = {
        method: 'PUT',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/${id}`, options);

    await response.json();
}

export async function remove(id) {
    const options = {
        method: 'delete',
        headers: {}
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/${id}`, options);

    await response.json();
}

export async function search(query) {
    const response = await fetch(`${baseUrl}?where=name%20LIKE%20%22${query}%22`);
    const data = await response.json();
    return data;
}