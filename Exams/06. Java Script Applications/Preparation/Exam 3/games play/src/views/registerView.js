import { html } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../io/registry.js'

const registerTemplate = (context) => html`
<section id="register-page" class="content auth">
    <form id="register">
        <div class="container">
            <div class="brand-logo"></div>
            <h1>Register</h1>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="maria@email.com">

            <label for="pass">Password:</label>
            <input type="password" name="password" id="register-password">

            <label for="con-pass">Confirm Password:</label>
            <input type="password" name="confirm-password" id="confirm-password">

            <input class="btn submit" @click=${(e) => registerHandler(e, context)} type="submit" value="Register">

            <p class="field">
                <span>If you already have profile click <a href="/login">here</a></span>
            </p>
        </div>
    </form>
</section>
`;

export function registerPage(context) {
    context.render(registerTemplate(context));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('register'));
    const email = form.get('email');
    const password = form.get('password');
    const rePassword = form.get('confirm-password');

    if (email == '' || password == '' || rePassword == '') {
        return alert('All fields are required!');
    } else if (password !== rePassword) {
        return alert('Passwords don\'nt match!')
    }
    await register(email, password);
    context.page.redirect('/');
}