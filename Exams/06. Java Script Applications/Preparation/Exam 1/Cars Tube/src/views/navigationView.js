import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { logout } from '../io/registry.js'

const container = document.getElementById('nav-header');

const guestViewTemplate = () => html`
<div id="guest">
    <a href="/login">Login</a>
    <a href="/register">Register</a>
</div>
`;

const userViewTemplate = (ctx) => html`
<div id="profile">
    <a>Welcome ${sessionStorage.getItem('username')}</a>
    <a href="/my-listings/${sessionStorage.getItem('id')}">My Listings</a>
    <a href="/create">Create Listing</a>
    <a @click=${()=> logoutHandler(ctx)} href="#">Logout</a>
</div>
`;

const navigationTemplate = (ctx) => html`
    <nav @click=${trackActiveLink}>
        <a id="home" class="active" href="/">Home</a>
        <a href="/all-listings">All Listings</a>
        <a href="/search">By Year</a>
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

function userIsLoggedIn() {
    return sessionStorage.getItem('authToken') !== null;
}