import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';

const loginTemplate = (ctx) => html`
<section id="loginPage">
    <form id="login-form">
        <fieldset>
            <legend>Login</legend>

            <label for="email" class="vhide">Email</label>
            <input id="email" class="email" name="email" type="text" placeholder="Email">

            <label for="password" class="vhide">Password</label>
            <input id="password" class="password" name="password" type="password" placeholder="Password">

            <button @click=${(e) => loginHandler(e, ctx)} type="submit" class="login">Login</button>

            <p class="field">
                <span>If you don't have profile click <a href="/register">here</a></span>
            </p>
        </fieldset>
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
        ctx.page.redirect('/');
    } else {
        return alert('All fields are required!');
    }
}