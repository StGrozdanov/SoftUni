const baseUrl = 'http://localhost:3030/data/games';

export async function getAll() {
    const response = await fetch(baseUrl + '?sortBy=_createdOn%20desc');
    const data = await response.json();
    return data;
}

export async function getLastThree() {
    const response = await fetch(baseUrl + '?sortBy=_createdOn%20desc&distinct=category');
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

export async function getMyPublications(userId) {
    const response = await fetch(`${baseUrl}?where=_ownerId%3D%22${userId}%22`);
    const data = await response.json();
    return data;
}

export async function getAllComments(gameId) {
    const response = await fetch(`http://localhost:3030/data/comments?where=gameId%3D%22${gameId}%22`);
    const data = await response.json();
    return data;
}

export async function addComment(data) {
    try {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch('http://localhost:3030/data/comments', options);

    return await response.json();
} catch(e) {
    console.log(e);
}
}