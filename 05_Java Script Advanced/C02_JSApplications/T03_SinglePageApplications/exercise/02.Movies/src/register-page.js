import { appReload } from "../app.js";
import { register } from "./registry.js";

const registerPage = document.getElementById('form-sign-up');
const registerButton = document.getElementById('registerBtn');

registerButton.addEventListener('click', async (e) => {
    e.preventDefault();
    let form = new FormData(registerPage);
    let email = form.get('email');
    let password = form.get('password');
    let confirmPassword = form.get('repeatPassword');

    if (email == '' || password == '' || confirmPassword == '') {
        alert('All fields should be filled.');
        throw new Error();
    }
    if (password !== confirmPassword) {
        alert('Password and confirm password do not match');
        throw new Error();
    }

    await register(email, password);

    if (sessionStorage.getItem('authToken') != null) {
        registerPage.reset();
        appReload();
    }
});

function preview() {
    registerPage.classList.remove('hidden');
}

function close() {
    registerPage.classList.add('hidden');
}

export default {
    preview,
    close
}