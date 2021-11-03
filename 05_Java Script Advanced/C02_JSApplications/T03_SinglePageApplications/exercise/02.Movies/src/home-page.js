import addMoviePage from "./addMoviePage.js";
import { getData } from "./requests.js";

const homePage = document.getElementById('home-page');
const addMovieButton = document.getElementById('add-movie-button');

addMovieButton.addEventListener('click', (e) => {
    e.preventDefault();
    addMoviePage.preview();
    close();
});

async function preview() {
    homePage.classList.remove('hidden');
    await getData();
}

function close() {
    homePage.classList.add('hidden');
}

export default {
    preview,
    close
}