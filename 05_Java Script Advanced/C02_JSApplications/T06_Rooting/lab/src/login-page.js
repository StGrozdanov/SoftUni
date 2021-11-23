import { html, render } from '../node_modules/lit-html/lit-html.js';
import { login as userLogin } from '../src/registry.js'

const loginTemplate = (context) => html`
        <section>
            <form class="text-center border border-light p-5" action="" method="" id="form-login">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" placeholder="Email" name="email" value="">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password" value="">
                </div>
        
                <button type="submit" class="btn btn-primary" id="loginBtn" @click=${(e)=> loginHandler(e,
    context)}>Login</button>
            </form>
        </section>
`;

export function login(context) {
    render(loginTemplate(context), document.getElementById('root'));
}

async function loginHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('form-login'));
    const email = form.get('email');
    const password = form.get('password');

    if (email != '' && password != '') {
        await userLogin(email, password);
        context.page.redirect('/');
    } else {
        return alert('Both email and password fields are mandatory.');
    }
}