import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('nav-container');

const guestViewTemplate = () => html`
<div class="guest">
    <div class="profile">
        <a href="/login">Login</a>
        <a href="/register">Register</a>
    </div>
    <a class="active" href="/">Home Page</a>
</div>
`;

const userViewTemplate = (ctx) => html`
<div class="user">
    <a href="/create-meme">Create Meme</a>
    <div class="profile">
        <span>Welcome, ${sessionStorage.getItem('email')}</span>
        <a href="/my-profile/${sessionStorage.getItem('id')}">My Profile</a>
        <a @click=${() => logoutHandler(ctx)} href="javascript:void[0]">Logout</a>
    </div>
</div>
`;

const navigationTemplate = (ctx) => html`
<a href="/all-memes">All Memes</a>
${userIsLoggedIn() ? userViewTemplate(ctx) : guestViewTemplate()}
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

function userIsLoggedIn() {
    return sessionStorage.getItem('authToken') !== null;
}