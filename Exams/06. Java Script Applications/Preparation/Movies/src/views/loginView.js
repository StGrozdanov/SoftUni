import { html } from '../../node_modules/lit-html/lit-html.js';
import { login } from '../io/registry.js';

const loginTemplate = (ctx) => html`
<section id="form-login">
    <form class="text-center border border-light p-5" action="" method="">
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" type="email" class="form-control" placeholder="Email" name="email" value="">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" type="password" class="form-control" placeholder="Password" name="password" value="">
        </div>

        <button type="submit" class="btn btn-primary" @click=${(e) => loginHandler(e, ctx)}>Login</button>
    </form>
</section>
</form>
`;

export function loginPage(ctx) {
    ctx.render(loginTemplate(ctx));
}

async function loginHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.querySelector('#form-login form'));
    const email = form.get('email');
    const password = form.get('password');

    if (email != '' && password != '') {
        await login(email, password);
        ctx.page.redirect('/');
    } else {
        return alert('All fields are required!');
    }
}