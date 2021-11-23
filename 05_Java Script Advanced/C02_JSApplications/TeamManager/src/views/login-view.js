import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';
import { errorDivTemplate } from '../templates/errorTemplate.js'

const loginTemplate = (ctx, error) => html`
<section id="login">
    <article class="narrow">
        <header class="pad-med">
            <h1>Login</h1>
        </header>
        <form id="login-form" class="main-form pad-large">
            ${error}
            <label>E-mail: <input type="text" name="email"></label>
            <label>Password: <input type="password" name="password"></label>
            <input @click=${(e)=> loginHandler(e, ctx)} class="action cta" type="submit" value="Sign In">
        </form>
        <footer class="pad-small">Don't have an account? <a href="/register" class="invert">Sign up here</a>
        </footer>
    </article>
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
        try {
            await login(email, password);
            ctx.page.redirect('/');
        } catch (e) {
            ctx.render(loginTemplate(ctx, errorDivTemplate(e.message)));
            return;
        }
    } else {
        ctx.render(loginTemplate(ctx, errorDivTemplate('All fields are required!')))
        return;
    }
}