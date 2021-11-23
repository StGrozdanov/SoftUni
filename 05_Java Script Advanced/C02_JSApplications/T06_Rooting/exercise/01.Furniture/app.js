import page from './node_modules/page/page.mjs';
import { createPage } from './src/pages/create-furniture-page.js';
import { detailsPage } from './src/pages/details-page.js';
import { editPage } from './src/pages/edit-page.js';
import { homePage } from './src/pages/home-page.js';
import { loginPage } from './src/pages/login-page.js';
import { myFurniture } from './src/pages/my-publications-page.js';
import { registerPage } from './src/pages/register-page.js';
import { logout } from './src/registry.js';

page('/', homePage);
page('/login', loginPage);
page('/register', registerPage);
page('/details/:id', detailsPage);
page('/edit/:id', editPage);
page('/create', createPage);
page('/myFurniture', myFurniture);

page.start();

const logoutBtn = document.getElementById('logoutBtn')
logoutBtn.addEventListener('click', async () => {
    await logout();
    page.redirect('/');
    authorize();
});

authorize();

export function authorize() {
    const homeButton = document.getElementById('catalogLink');
    homeButton.classList.add('active');
    
    const userButtons = document.querySelectorAll('#user');
    const guestButtons = document.querySelectorAll('#guest');

    if (sessionStorage.getItem('authToken') != null) {
        guestButtons.forEach(b => b.classList.add('hidden'));
        userButtons.forEach(b => b.classList.remove('hidden'));
    } else {
        guestButtons.forEach(b => b.classList.remove('hidden'));
        userButtons.forEach(b => b.classList.add('hidden'));
    }
}

document.querySelectorAll('nav a').forEach(button => button.addEventListener('click', (e) => {
    removeActive();
    e.target.classList.add('active');
}));

function removeActive() {
    document.querySelectorAll('nav a').forEach(button => button.classList.remove('active'));
}