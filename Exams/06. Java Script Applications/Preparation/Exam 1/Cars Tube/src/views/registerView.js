import { html } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../io/registry.js'

const registerTemplate = (context) => html`
<section id="register">
    <div class="container">
        <form id="register-form">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <p>Username</p>
            <input type="text" placeholder="Enter Username" name="username" required>

            <p>Password</p>
            <input type="password" placeholder="Enter Password" name="password" required>

            <p>Repeat Password</p>
            <input type="password" placeholder="Repeat Password" name="repeatPass" required>
            <hr>

            <input @click=${(e) => registerHandler(e, context)} type="submit" class="registerbtn" value="Register">
        </form>
        <div class="signin">
            <p>Already have an account?
                <a href="/login">Sign in</a>.
            </p>
        </div>
    </div>
</section>
`;

export function registerPage(context) {
    context.render(registerTemplate(context));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('register-form'));
    const username = form.get('username');
    const password = form.get('password');
    const rePassword = form.get('repeatPass');

    if (username == '' || password == '' || rePassword == '') {
        return alert('All fields are required!');
    } else if (password !== rePassword) {
        return alert('Passwords don\'nt match!')
    }
    await register(username, password);
    context.page.redirect('/all-listings');
}