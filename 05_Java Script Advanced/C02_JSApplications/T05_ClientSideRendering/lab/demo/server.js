export async function getData() {
    const response = await fetch('http://localhost:3030/data/movies');
    const data = await response.json();
    return data;
}