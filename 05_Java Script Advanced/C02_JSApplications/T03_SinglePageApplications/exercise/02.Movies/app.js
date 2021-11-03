import homePage from "./src/home-page.js";
import loginPage from "./src/login-page.js";
import registerPage from "./src/register-page.js";
import logoutPage from "./src/logout.js";
import addMoviePage from "./src/addMoviePage.js";
import movieDetailsPage from "./src/movieDetailsPage.js";
import editMoviePage from "./src/editMoviePage.js";
import * as server from "./src/requests.js";

const guestElements = document.getElementsByClassName('guest');
const userElements = document.getElementsByClassName('user');
const welcomeMsg = document.getElementById('welcome');

window.addEventListener('load', () => {
    appReload();
});

const pages = {
    'home': homePage,
    'login': loginPage,
    'register': registerPage,
    'logout': logoutPage,
    'add-movie-page': addMoviePage,
    'movie-details': movieDetailsPage,
    'edit-movie': editMoviePage,
}

const navigationBar = document.getElementById('nav');
navigationBar.addEventListener('click', async (e) => {
    e.preventDefault();
    if (e.target.tagName == 'A') {
        closePages();
        let selectedPage = pages[e.target.id];
        await selectedPage.preview();
        authorize();
    }
});

function closePages() {
    Object.values(pages).forEach(page => page.close());
}

export function authorize() {
    if (sessionStorage.getItem('authToken') != null) {
        welcomeMsg.textContent = `Welcome, ${sessionStorage.getItem('email')}`;
        Array.from(guestElements).forEach(g => g.classList.add('hidden'));
        Array.from(userElements).forEach(u => u.classList.remove('hidden'));
    } else {
        welcomeMsg.textContent = 'Welcome, guest';
        Array.from(guestElements).forEach(g => g.classList.remove('hidden'));
        Array.from(userElements).forEach(u => u.classList.add('hidden'));
    }
}

export async function appReload() {
    await server.getData();
    closePages();
    homePage.preview();
}