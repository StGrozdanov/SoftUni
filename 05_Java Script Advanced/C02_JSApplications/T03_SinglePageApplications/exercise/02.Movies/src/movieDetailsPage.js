import { getMovie } from "./requests.js";

const movieDetailsPage = document.getElementById('movie-details');

async function preview(id) {
    await getMovie(id);
    movieDetailsPage.classList.remove('hidden');
}

function close() {
    movieDetailsPage.classList.add('hidden');
}

export default {
    preview,
    close,
}