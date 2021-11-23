import { html } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../io/registry.js'
import { errorDivTemplate } from '../templates/errorTemplate.js'

const registerTemplate = (context, error) => html`
<section id="register">
    <article class="narrow">
        <header class="pad-med">
            <h1>Register</h1>
        </header>
        <form id="register-form" class="main-form pad-large">
            ${error}
            <label>E-mail: <input type="text" name="email"></label>
            <label>Username: <input type="text" name="username"></label>
            <label>Password: <input type="password" name="password"></label>
            <label>Repeat: <input type="password" name="repass"></label>
            <input @click=${(e) => registerHandler(e, context)} class="action cta" type="submit" value="Create Account">
        </form>
        <footer class="pad-small">Already have an account? <a href="/login" class="invert">Sign in here</a>
        </footer>
    </article>
</section>
`;

export function registerPage(context) {
    context.render(registerTemplate(context));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('register-form'));
    const email = form.get('email');
    const username = form.get('username');
    const password = form.get('password');
    const rePassword = form.get('repass');

    if (email == '' || password == '' || rePassword == '' || username == '') {
        context.render(registerTemplate(context, errorDivTemplate('All fields are required!')));
        return;
    } else if (password !== rePassword) {
        context.render(registerTemplate(context, errorDivTemplate('Password don\'t match!')));
        return;
    } else if (username.toString().length < 3) {
        context.render(registerTemplate(context, errorDivTemplate('Username should be at least 3 characters long.')));
        return;
    } else if (password.toString().length < 3) {
        context.render(registerTemplate(context, errorDivTemplate('Password should be at least 3 characters/digits long.')));
        return;
    }
    try {
        await register(email, username, password);
        context.page.redirect('/');
    } catch (e) {
        ctx.render(registerTemplate(ctx, errorDivTemplate(e.message)));
        return;
    }
}