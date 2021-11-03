export async function register(email, password) {
    const response = await fetch(`http://localhost:3030/users/register`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (response.ok) {
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        sessionStorage.setItem('id', data._id);
        sessionStorage.setItem('email', data.email);
    } else {
        const error = await response.json();
        alert(error.message);
        throw new Error();
    }
}

export async function login(email, password) {
    const response = await fetch(`http://localhost:3030/users/login`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (response.ok) {
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        sessionStorage.setItem('id', data._id);
        sessionStorage.setItem('email', data.email);
    } else {
        const error = await response.json();
        alert(error.message);
        throw new Error();
    }
}

export async function logout() {
    const response = await fetch(`http://localhost:3030/users/logout`, {
        method: 'get',
        headers: { 'X-Authorization': sessionStorage.getItem('authToken') }
    });
    if (response.ok) {
        sessionStorage.removeItem('authToken');
        sessionStorage.removeItem('id');
        sessionStorage.removeItem('email', response.email);
    } else {
        alert(response.status);
        throw new Error();
    }
}