import { appReload } from "../app.js";
import { postData } from "./requests.js";

const addMoviePage = document.getElementById('add-movie-page');
const submitNewMovie = document.getElementById('submit-movie');

const movieTitle = document.getElementById('add-movie-title')
const movieDescription = document.getElementById('add-movie-description');
const movieCoverImg = document.getElementById('add-movie-img');

submitNewMovie.addEventListener('click', async (e) => {
    e.preventDefault();
    if (movieTitle.value.trim() == '' || movieDescription.value.trim() == '' || movieCoverImg.value.trim() == '') {
        alert('All fields are required in order to submit the new movie!');
        return;
    }
    
    await postData({ title: movieTitle.value, description: movieDescription.value, img: movieCoverImg.value });
    
    movieTitle.value = '';
    movieDescription.value = '';
    movieCoverImg.value = '';
    appReload();
});

function preview() {
    addMoviePage.classList.remove('hidden');
}

function close() {
    addMoviePage.classList.add('hidden');
}

export default {
    preview,
    close
}