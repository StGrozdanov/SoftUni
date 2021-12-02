import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('site-header');

const guestViewTemplate = () => html`
 <div id="guest">
            <a class="button" href="/login">Login</a>
            <a class="button" href="/register">Register</a>
        </div>
`;

const userViewTemplate = (ctx) => html`
<div id="user">
            <span>Welcome, ${sessionStorage.getItem('email')}</span>
            <a class="button" href="/my-books/${sessionStorage.getItem('id')}">My Books</a>
            <a class="button" href="/add">Add Book</a>
            <a @click=${() => logoutHandler(ctx)} class="button" href="javascript:void[0]">Logout</a>
        </div>
`;

const navigationTemplate = (ctx) => html`
<nav class="navbar">
    <section class="navbar-dashboard">
        <a href="/">Dashboard</a>
        ${userIsLoggedIn() ? userViewTemplate(ctx) : guestViewTemplate()}
    </section>
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