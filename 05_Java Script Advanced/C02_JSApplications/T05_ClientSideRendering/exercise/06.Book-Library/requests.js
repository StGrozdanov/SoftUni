export async function getData() {
    const response = await fetch('http://localhost:3030/jsonstore/collections/books');
    const data = await response.json();
    return data;
}

export async function postData(content) {
    await fetch('http://localhost:3030/jsonstore/collections/books', {
        method: 'Post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(content)
    });
}

export async function updateData(content, id) {
    await fetch(`http://localhost:3030/jsonstore/collections/books/${id}`, {
        method: 'Put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(content)
    });
}

export async function deleteData(id) {
    await fetch('http://localhost:3030/jsonstore/collections/books/' + `${id}`, {
        method: 'delete',
        headers: { 'Content-Type': 'application/json' }
    });
}