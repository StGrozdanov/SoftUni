import { appReload } from "../app.js";
import { login } from "./registry.js";

const loginPage = document.getElementById('form-login');
const loginButton = document.getElementById('loginBtn');

loginButton.addEventListener('click', async (e) => {
    e.preventDefault();
    let form = new FormData(loginPage);
    let email = form.get('email');
    let password = form.get('password');

    await login(email, password);

    if (sessionStorage.getItem('authToken') != null) {
        loginPage.reset();
        appReload();
    }
});

function preview() {
    loginPage.classList.remove('hidden');
}

function close() {
    loginPage.classList.add('hidden');
}

export default {
    preview,
    close
}