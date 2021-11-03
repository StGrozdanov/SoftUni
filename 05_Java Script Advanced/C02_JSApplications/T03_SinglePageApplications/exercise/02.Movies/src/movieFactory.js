import { appReload } from "../app.js";
import editMoviePage from "./editMoviePage.js";
import homePage from "./home-page.js";
import movieDetailsPage from "./movieDetailsPage.js";
import { deleteMovie, getLikes, isThisMovieAlreadyLiked, like } from "./requests.js";

const movieCoversContainer = document.getElementById('movie-covers');
const movieDetailsContainer = document.getElementById('details-container');

export function appendMovie(movieOwner, movieImage, movieTitle, movieId) {
    const mainDiv = createElement('div', 'card');
    mainDiv.classList.add('mb-4');
    mainDiv.id = movieOwner;
    const image = createElement('img', 'card-img-top');
    image.src = movieImage;
    image.width = '400px';
    const cardBodyDiv = createElement('div', 'card-body');
    const heading = createElement('h4', 'card-title', movieTitle);
    const cardFooterDiv = createElement('div', 'card-footer');
    cardFooterDiv.classList.add('user');
    cardFooterDiv.classList.add('hidden');
    const a = createElement('a');
    a.href = '#'

    const detailsButton = createElement('button', 'btn', 'Details');
    detailsButton.classList.add('btn-info');
    detailsButton.addEventListener('click', async (e) => {
        e.preventDefault();
        await movieDetailsPage.preview(movieId);
        homePage.close();
    });

    a.appendChild(detailsButton);
    cardFooterDiv.appendChild(a);
    cardBodyDiv.appendChild(heading);
    mainDiv.appendChild(image);
    mainDiv.appendChild(cardBodyDiv);
    mainDiv.appendChild(cardFooterDiv);

    movieCoversContainer.appendChild(mainDiv);
}

export async function previewMovie(movieOwner, movieImage, movieTitle, movieDescription, movieId) {
    movieDetailsContainer.innerHTML = '';
    const mainDiv = createElement('div', 'row');
    mainDiv.id = movieOwner;
    mainDiv.classList.add('bg-light');
    mainDiv.classList.add('text-dark');
    const title = createElement('h1', null, `Movie title: ${movieTitle}`);
    const imgDiv = createElement('div', 'col-md-8');
    const img = createElement('img', 'img-thumbnail');
    img.src = movieImage;
    const descrDiv = createElement('div', 'col-md-4');
    descrDiv.classList.add('text-center');
    const descrHeading = createElement('h3', 'my-3', 'Movie Description');
    const descrP = createElement('p', null, movieDescription);
    const deleteButton = createElement('a', 'btn', 'Delete');
    deleteButton.classList.add('btn-danger');
    deleteButton.id = 'movie-delete-btn';
    deleteButton.href = '#';
    const editButton = createElement('a', 'btn', 'Edit');
    editButton.classList.add('btn-warning');
    editButton.id = 'movie-edit-btn';
    editButton.href = '#';
    const likeButton = createElement('a', 'btn', 'Like');
    likeButton.classList.add('btn-primary');
    likeButton.id = 'movie-like-btn';
    likeButton.href = '#';

    const numberOfLikes = await getLikes(movieId);
    const likeCount = createElement('span', 'enrolled-span', `Liked ${numberOfLikes}`);

    imgDiv.appendChild(img);
    descrDiv.appendChild(descrHeading);
    descrDiv.appendChild(descrP);
    descrDiv.appendChild(deleteButton);
    descrDiv.appendChild(editButton);
    descrDiv.appendChild(likeButton);
    descrDiv.appendChild(likeCount);

    mainDiv.appendChild(title);
    mainDiv.appendChild(imgDiv);
    mainDiv.appendChild(descrDiv);

    let movieIsAlreadyLiked = await isThisMovieAlreadyLiked(sessionStorage.getItem('id'), movieId);

    //user authorization
    if (sessionStorage.getItem('id') === movieOwner) {
        likeButton.style.display = 'none';
    } else if (movieIsAlreadyLiked) {
        likeButton.style.display = 'none';
        deleteButton.style.display = 'none';
        editButton.style.display = 'none';
    } else {
        deleteButton.style.display = 'none';
        editButton.style.display = 'none';
        likeCount.style.display = 'none';
    }

    editButton.addEventListener('click', (e) => {
        e.preventDefault();
        editMoviePage.preview(movieTitle, movieDescription, movieImage, movieId);
        movieDetailsPage.close()
    });

    deleteButton.addEventListener('click', async (e) => {
        e.preventDefault();
        await deleteMovie(movieId);
        appReload();
    });

    likeButton.addEventListener('click', async (e) => {
        e.preventDefault();
        const liked = await isThisMovieAlreadyLiked(sessionStorage.getItem('id'), movieId);
        if (liked === false) {
            await like(movieId);
            await movieDetailsPage.preview(movieId);
        }
    });

    movieDetailsContainer.appendChild(mainDiv);
}

function createElement(type, clazz, content) {
    const result = document.createElement(type);
    if (clazz) {
        result.classList.add(clazz);
    } if (content) {
        result.textContent = content;
    }
    return result;
}