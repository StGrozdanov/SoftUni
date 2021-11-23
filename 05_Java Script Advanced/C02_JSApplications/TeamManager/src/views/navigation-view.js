import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('titlebar');

const guestViewTemplate = () => html`
    <a href="/browse-teams" class="action">Browse Teams</a>
    <a href="/login" class="action">Login</a>
    <a href="/register" class="action">Register</a>
`;

const userViewTemplate = (ctx) => html`
    <a href="/browse-teams" class="action">Browse Teams</a>
    <a href="/my-teams" class="action">My Teams</a>
    <a @click=${() => logoutHandler(ctx)} href="#" class="action">Logout</a>
`;

const navigationTemplate = (ctx) => html`
<a href="/" class="site-logo">Team Manager</a>
<nav>
    ${userIsLoggedIn() 
         ?  userViewTemplate(ctx)
         :  guestViewTemplate()}
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