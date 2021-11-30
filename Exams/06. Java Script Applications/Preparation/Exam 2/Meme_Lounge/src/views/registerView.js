import { html } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../io/registry.js'
import { showNotification } from './common/notification.js';

const registerTemplate = (context) => html`
        <section id="register">
            <form id="register-form">
                <div class="container">
                    <h1>Register</h1>
                    <label for="username">Username</label>
                    <input id="username" type="text" placeholder="Enter Username" name="username">
                    <label for="email">Email</label>
                    <input id="email" type="text" placeholder="Enter Email" name="email">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="Enter Password" name="password">
                    <label for="repeatPass">Repeat Password</label>
                    <input id="repeatPass" type="password" placeholder="Repeat Password" name="repeatPass">
                    <div class="gender">
                        <input type="radio" name="gender" id="female" value="female">
                        <label for="female">Female</label>
                        <input type="radio" name="gender" id="male" value="male" checked>
                        <label for="male">Male</label>
                    </div>
                    <input @click=${(e) => registerHandler(e, context)} type="submit" class="registerbtn button" value="Register">
                    <div class="container signin">
                        <p>Already have an account?<a href="/login">Sign in</a>.</p>
                    </div>
                </div>
            </form>
        </section>
`;

export function registerPage(context) {
    context.render(registerTemplate(context));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('register-form'));
    const username = form.get('username');
    const email = form.get('email');
    const password = form.get('password');
    const rePassword = form.get('repeatPass');
    const gender = form.get('gender');

    if (email == '' || password == '' || rePassword == '' || username == '' || gender == '') {
        return showNotification('All fields are required!');
    } else if (password !== rePassword) {
        return showNotification('Passwords don\'t match!')
    }
    await register(username, email, password, gender);
    context.page.redirect('/all-memes');
}