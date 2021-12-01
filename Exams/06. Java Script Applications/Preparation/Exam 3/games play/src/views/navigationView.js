import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('nav-container');

const guestViewTemplate = () => html`
                <div id="guest">
                    <a href="/login">Login</a>
                    <a href="/register">Register</a>
                </div>
`;

const userViewTemplate = (ctx) => html`
                <div id="user">
                    <a href="/add">Create Game</a>
                    <a @click=${() => logoutHandler(ctx)} href="javascript:void[0]">Logout</a>
                </div>
`;

const navigationTemplate = (ctx) => html`
            <h1><a class="home" href="/">GamesPlay</a></h1>
            <nav>
                <a href="/all">All games</a>
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

function trackActiveLink(e) {
    if (e.path[0].localName == 'a') {
        const navAnkerTags = Array.from(e.currentTarget.getElementsByTagName('a'));

        navAnkerTags.forEach(tag => tag.classList.remove('active'));

        e.target.classList.add('active');
    }
}

export function userIsLoggedIn() {
    return sessionStorage.getItem('authToken') !== null;
}