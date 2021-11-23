import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('nav-container');

const guestViewTemplate = () => html`
<li class="nav-item">
    <a class="nav-link" href="/login">Login</a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/register">Register</a>
</li>
`;

const userViewTemplate = (ctx) => html`
<li class="nav-item">
    <a class="nav-link" href="#" @click=${() => logoutHandler(ctx)}>Logout</a>
</li>
`;

const navigationTemplate = (ctx) => html`
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <a class="navbar-brand text-light" href="/">Movies</a>
    <ul class="navbar-nav ml-auto ">
        <li class="nav-item">
            <a href="#" class="nav-link">Welcome, ${userIsLoggedIn() ? sessionStorage.getItem('email') : 'guest'}</a>
        </li>
        ${userIsLoggedIn() ? userViewTemplate(ctx) : guestViewTemplate()}
    </ul>
</nav>
`;

export function renderNavigation(ctx) {
    render(navigationTemplate(ctx), container);
}

async function logoutHandler(ctx) {
    await logout();
    ctx.page.redirect('/');
}

export function userIsLoggedIn() {
    return sessionStorage.getItem('authToken') !== null;
}