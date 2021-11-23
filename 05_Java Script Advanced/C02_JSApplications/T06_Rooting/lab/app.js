import page from "./node_modules/page/page.mjs";
import { addMovie } from './src/addMoviePage.js';
import { editMovie } from './src/editMoviePage.js';
import { homePage } from './src/home-page.js';
import { login } from './src/login-page.js';
import { movieDetails } from './src/movieDetailsPage.js';
import { register } from './src/register-page.js';
import { logout } from './src/registry.js'

page('/', homePage);
page('/login', login);
page('/register', register);
page('/addMovie', addMovie);
page('/movieDetails/:id', movieDetails);

page.start();

const logoutBtn = document.getElementById('logout');
logoutBtn.addEventListener('click', async () => {
    await logout();
    page.redirect('/');
});

authorize();

export function authorize() {
    const loginBtn = document.getElementById('login');
    const registerBtn = document.getElementById('register');
    const welcomeMsg = document.getElementById('welcome');

    if (sessionStorage.getItem('authToken') != null) {
        logoutBtn.classList.remove('hidden');
        loginBtn.classList.add('hidden');
        registerBtn.classList.add('hidden');
        welcomeMsg.textContent = `Welcome, ${sessionStorage.getItem('email')}`;
    } else {
        logoutBtn.classList.add('hidden');
        loginBtn.classList.remove('hidden');
        registerBtn.classList.remove('hidden');
        welcomeMsg.textContent = 'Welcome, guest';
    }
}