import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('nav-container');

const guestViewTemplate = () => html`
                    <li><a href="/login">Login</a></li>
                    <li><a href="/register">Register</a></li>
`;

const userViewTemplate = (ctx) => html`
                    <li><a href="/create">Create Album</a></li>
                    <li><a @click=${() => logoutHandler(ctx)} href="javascript:void[0]">Logout</a></li>
`;

const navigationTemplate = (ctx) => html`
            <nav>
                <img src="./images/headphones.png">
                <a href="/">Home</a>
                <ul>
                    <li><a href="/catalog">Catalog</a></li>
                    <li><a href="/search">Search</a></li>
                    ${userIsLoggedIn() ? userViewTemplate(ctx) : guestViewTemplate()}
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