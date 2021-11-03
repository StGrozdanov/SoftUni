export async function getData() {
    const response = await fetch('http://localhost:3030/jsonstore/advanced/dropdown');
    const data = await response.json();
    return data;
}

export async function postData(content) {
    await fetch('http://localhost:3030/jsonstore/advanced/dropdown', {
        method: 'Post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(content)
    });
}