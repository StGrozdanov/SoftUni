import { authorize } from '../../app.js';
import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../registry.js'

const container = document.querySelector('.container');

const loginTemplate = (context) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Login User</h1>
        <p>Please fill all fields.</p>
    </div>
</div>
<form id="form-login">
    <div class="row space-top">
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="email">Email</label>
                <input class="form-control" id="email" type="text" name="email">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="password">Password</label>
                <input class="form-control" id="password" type="password" name="password">
            </div>
            <input @click=${(e)=> loginHandler(e, context)} id="loginBtn" type="submit" class="btn btn-primary"
            value="Login" />
        </div>
    </div>
</form>
`;

export function loginPage(context) {
    render(loginTemplate(context), container);
}

async function loginHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('form-login'));
    const email = form.get('email');
    const password = form.get('password');

    if (email != '' && password != '') {
        await login(email, password);
        context.page.redirect('/');
        authorize();
    } else {
        return alert('All fields are required!');
    }
}