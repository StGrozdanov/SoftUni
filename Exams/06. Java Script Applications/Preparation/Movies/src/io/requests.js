const baseUrl = 'https://movies-server-api.herokuapp.com/data';

export async function getAll() {
    const response = await fetch(`${baseUrl}/movies`);
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

    const response = await fetch(`${baseUrl}/movies`, options);

    return await response.json();
}

export async function getSingle(id) {
    const response = await fetch(`${baseUrl}/movies/${id}`);
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

    const response = await fetch(`${baseUrl}/movies/${id}`, options);

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

    const response = await fetch(`${baseUrl}/movies/${id}`, options);

    await response.json();
}

export async function getMyPublications(userId) {
    const response = await fetch(`${baseUrl}?where=_ownerId%3D%22${userId}%22`);
    const data = await response.json();
    return data;
}

export async function getLikes(movieId) {
    const response = await fetch(`${baseUrl}/likes?where=movieId%3D%22${movieId}%22&distinct=_ownerId&count`);
    const data = await response.json();
    return data;
}

export async function getUserLikes(movieId, userId) {
    const response = await fetch(`${baseUrl}/likes?where=movieId%3D%22${movieId}%22%20and%20_ownerId%3D%22${userId}%22 `);
    const data = await response.json();

    let result = {data: data, liked: false};
    
    if (data.some(movie => movie.movieId === movieId)) {
        result.liked = true;
    }
    return result;
}

export async function likeMovie(id) {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify({ movieId: id })
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/likes`, options);

    return await response.json();
}

export async function unlikeMovie(id) {
    const options = {
        method: 'delete',
        headers: {}
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/likes/${id}`, options);
}