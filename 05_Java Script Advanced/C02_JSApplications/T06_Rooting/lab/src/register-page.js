import { html, render } from '../node_modules/lit-html/lit-html.js';
import { register as userRegister } from './registry.js';

const registerTemplate = (context) => html`
        <section>
            <form class="text-center border border-light p-5" id="form-sign-up" action="#" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" placeholder="Email" name="email" value="">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password" value="">
                </div>
        
                <div class="form-group">
                    <label for="repeatPassword">Repeat Password</label>
                    <input type="password" class="form-control" placeholder="Repeat-Password" name="repeatPassword" value="">
                </div>
                <button type="submit" class="btn btn-primary" id="registerBtn" @click=${(e)=> registerHandler(e,
                    context)}>Register</button>
            </form>
        </section>
`;

export function register(context) {
    render(registerTemplate(context), document.getElementById('root'));
}

async function registerHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('form-sign-up'));
    const email = form.get('email');
    const password = form.get('password');
    const rePassword = form.get('repeatPassword');

    if (email == '' && password == '' && rePassword == '') {
        return alert('Both email and password fields are mandatory.');
    } else if (rePassword !== password) {
        return alert('Passwords don\'t match!');
    }
    await userRegister(email, password);
    context.page.redirect('/');
}