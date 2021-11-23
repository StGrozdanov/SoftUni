import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';

const loginTemplate = (ctx) => html`
<section id="login">
    <div class="container">
        <form id="login-form" action="#" method="post">
            <h1>Login</h1>
            <p>Please enter your credentials.</p>
            <hr>

            <p>Username</p>
            <input placeholder="Enter Username" name="username" type="text">

            <p>Password</p>
            <input type="password" placeholder="Enter Password" name="password">
            <input @click=${(e) => loginHandler(e, ctx)} type="submit" class="registerbtn" value="Login">
        </form>
        <div class="signin">
            <p>Dont have an account?
                <a href="/register">Sign up</a>.
            </p>
        </div>
    </div>
</section>
`;

export function loginPage(ctx) {
    ctx.render(loginTemplate(ctx));
}

async function loginHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.getElementById('login-form'));
    const username = form.get('username');
    const password = form.get('password');

    if (username != '' && password != '') {
        await login(username, password);
        ctx.page.redirect('/all-listings');
    } else {
        return alert('All fields are required!');
    }
}