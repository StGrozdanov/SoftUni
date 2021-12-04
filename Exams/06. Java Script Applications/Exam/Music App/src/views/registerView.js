import { html } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../io/registry.js'

const registerTemplate = (context) => html`
<section id="registerPage">
    <form id="register-form">
        <fieldset>
            <legend>Register</legend>

            <label for="email" class="vhide">Email</label>
            <input id="email" class="email" name="email" type="text" placeholder="Email">

            <label for="password" class="vhide">Password</label>
            <input id="password" class="password" name="password" type="password" placeholder="Password">

            <label for="conf-pass" class="vhide">Confirm Password:</label>
            <input id="conf-pass" class="conf-pass" name="conf-pass" type="password" placeholder="Confirm Password">

            <button @click=${(e) => registerHandler(e, context)} type="submit" class="register">Register</button>

            <p class="field">
                <span>If you already have profile click <a href="/login">here</a></span>
            </p>
        </fieldset>
    </form>
</section>
`;

export function registerPage(context) {
    context.render(registerTemplate(context));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('register-form'));
    const email = form.get('email');
    const password = form.get('password');
    const rePassword = form.get('conf-pass');

    if (email == '' || password == '' || rePassword == '') {
        return alert('All fields are required!');
    } else if (password !== rePassword) {
        return alert('Passwords don\'t match!')
    }
    await register(email, password);
    context.page.redirect('/');
}