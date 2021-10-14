export async function register(email, password){
    const response = await fetch(`http://localhost:3030/users/register`, {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password})
    });
    if (response.ok){
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        return 'ok';
    } else {
        const error = await response.json();
        alert(error.message);
    }
}

export async function login(email, password){
    const response = await fetch(`http://localhost:3030/users/login`, {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email, password})
    });
    if (response.ok){
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        return 'ok';
    } else {
        const error = await response.json();
        alert(error.message);
    }
}