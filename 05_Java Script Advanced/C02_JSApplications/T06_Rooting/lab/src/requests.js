export async function getData() {
    const response = await fetch('http://localhost:3030/data/movies');
    const data = await response.json();
    return data;
}

export async function postData(data) {
    const options = {
        method: 'post',
        headers: {},
        body: JSON.stringify(data)
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }
    
    const response = await fetch(`http://localhost:3030/data/movies`, options);

    return await response.json();
}

export async function getMovie(id) {
    const response = await fetch(`http://localhost:3030/data/movies/${id}`);
    const data = await response.json();
    return data;
}

export async function updateMovie(id, data) {
    const options = {
        method: 'put',
        headers: {},
        body: JSON.stringify(data)
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`http://localhost:3030/data/movies/${id}`, options);

    await response.json();
}

export async function deleteMovie(id) {
    const options = {
        method: 'delete',
        headers: {}
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`http://localhost:3030/data/movies/${id}`, options);

    await response.json();
}

export async function getLikes(movieId) {
    const response = await fetch(`http://localhost:3030/data/likes?where=movieId%3D%22${movieId}%22&distinct=_ownerId&count`);
    const data = await response.json();
    return data;
}

export async function like(movieId) {
    const options = {
        method: 'post',
        headers: {},
        body: JSON.stringify({ movieId: movieId })
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`http://localhost:3030/data/likes`, options);

    await response.json();
}

export async function isThisMovieAlreadyLiked(userId, movieId) {
    const response = await fetch(`http://localhost:3030/data/likes?where=movieId%3D%22${movieId}%22%20and%20_ownerId%3D%22${userId}%22`);
    const data = await response.json();
    if (data.some(movie => movie.movieId === movieId)) {
        return true;
    }
    return false;
}