const baseUrl = 'http://localhost:3030/data';

export async function getAllTeams() {
    const response = await fetch(`${baseUrl}/teams`);
    const data = await response.json();
    return data;
}

export async function getAllMembers() {
    const response = await fetch(`${baseUrl}/members?where=status%3D%22member%22`);
    const data = await response.json();
    return data;
}

export async function getAllTeamsIncludingTheMember(id) {
    const response = await fetch(`${baseUrl}/members?where=_ownerId%3D%22${id}%22%20AND%20status%3D%22member%22&load=team%3DteamId%3Ateams`);
    const data = await response.json();
    return data;
}

export async function createTeam(data) {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/teams`, options);

    return await response.json();
}

export async function newMemberRequest(data) {
    const options = {
        method: 'POST',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/members`, options);

    return await response.json();
}

export async function approveMember(id, data) {
    const options = {
        method: 'PUT',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/members/${id}`, options);

    return await response.json();
}

export async function getASingleTeam(id) {
    const response = await fetch(`${baseUrl}/teams/${id}`);
    const data = await response.json();
    return data;
}

export async function getASingleTeamMembersAndCandidates(id) {
    const response = await fetch(`${baseUrl}/members?where=teamId%3D%22${id}%22&load=user%3D_ownerId%3Ausers`);
    const data = await response.json();
    return data;
}

export async function editTeam(id, data) {
    const options = {
        method: 'PUT',
        headers: {},
        body: JSON.stringify(data)
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/teams/${id}`, options);

    await response.json();
}

export async function removeMember(id) {
    const options = {
        method: 'delete',
        headers: {}
    };

    const token = sessionStorage.getItem('authToken');
    if (token !== null) {
        options.headers['X-Authorization'] = token;
    }

    const response = await fetch(`${baseUrl}/members/${id}`, options);

    await response.json();
}