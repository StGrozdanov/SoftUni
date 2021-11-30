import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';
import { showNotification } from './common/notification.js';

const loginTemplate = (ctx) => html`
        <section id="login">
            <form id="login-form">
                <div class="container">
                    <h1>Login</h1>
                    <label for="email">Email</label>
                    <input id="email" placeholder="Enter Email" name="email" type="text">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="Enter Password" name="password">
                    <input @click=${(e) => loginHandler(e, ctx)} type="submit" class="registerbtn button" value="Login">
                    <div class="container signin">
                        <p>Dont have an account?<a href="/register">Sign up</a>.</p>
                    </div>
                </div>
            </form>
        </section>
`;

export function loginPage(ctx) {
    ctx.render(loginTemplate(ctx));
}

async function loginHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.getElementById('login-form'));
    const email = form.get('email');
    const password = form.get('password');

    if (email != '' && password != '') {
        await login(email, password);
        ctx.page.redirect('/all-memes');
    } else {
        return showNotification('All fields are required!');
    }
}