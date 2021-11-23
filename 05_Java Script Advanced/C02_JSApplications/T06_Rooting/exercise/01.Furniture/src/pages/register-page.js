import { authorize } from '../../app.js';
import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { register } from '../registry.js'

const container = document.querySelector('.container');

const registerTemplate = (context) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Register New User</h1>
        <p>Please fill all fields.</p>
    </div>
</div>
<form id="form-register">
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
            <div class="form-group">
                <label class="form-control-label" for="rePass">Repeat</label>
                <input class="form-control" id="rePass" type="password" name="rePass">
            </div>
            <input @click=${(e) => registerHandler(e, context)} type="submit" class="btn btn-primary" value="Register" />
        </div>
    </div>
</form>
`;

export function registerPage(context) {
    render(registerTemplate(context), container);
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('form-register'));
    const email = form.get('email');
    const password = form.get('password');
    const rePassword = form.get('rePass');

    if (email == '' || password == '' || rePassword == '') {
        return alert('All fields are required!');
    } else if (password !== rePassword) {
        return alert('Passwords don\'nt match!')
    }
    await register(email, password);
    context.page.redirect('/');
    authorize();
}