import movieDetailsPage from "./movieDetailsPage.js";
import { updateMovie } from "./requests.js";

const editMoviePage = document.getElementById('edit-movie');
const editTitle = document.getElementById('edit-title');
const editDescription = document.getElementById('edit-description');
const editImage = document.getElementById('edit-img');
const submitBtn = document.getElementById('edit-movie-btn');
let targetMovieId;

submitBtn.addEventListener('click', async (e) => {
    e.preventDefault();
    await updateMovie(targetMovieId, { title: editTitle.value, description: editDescription.value, img: editImage.value });
    close();
    await movieDetailsPage.preview(targetMovieId);
});

function preview(title, description, img, id) {
    editMoviePage.classList.remove('hidden');
    editTitle.value = title;
    editDescription.value = description;
    editImage.value = img;
    targetMovieId = id;
}

function close() {
    editMoviePage.classList.add('hidden');
}

export default {
    preview,
    close
}