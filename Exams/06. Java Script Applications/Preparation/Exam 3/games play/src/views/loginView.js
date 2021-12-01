import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';

const loginTemplate = (ctx) => html`
<section id="login-page" class="auth">
    <form id="login">

        <div class="container">
            <div class="brand-logo"></div>
            <h1>Login</h1>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Sokka@gmail.com">

            <label for="login-pass">Password:</label>
            <input type="password" id="login-password" name="password">
            <input type="submit" @click=${(e) => loginHandler(e, ctx)} class="btn submit" value="Login">
            <p class="field">
                <span>If you don't have profile click <a href="/register">here</a></span>
            </p>
        </div>
    </form>
</section>
`;

export function loginPage(ctx) {
    ctx.render(loginTemplate(ctx));
}

async function loginHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.getElementById('login'));
    const email = form.get('email');
    const password = form.get('password');

    if (email != '' && password != '') {
        await login(email, password);
        ctx.page.redirect('/');
    } else {
        return alert('All fields are required!');
    }
}