import * as registry from './registry.js'
import * as database from './data.js'

const registerForm = document.getElementById('register-form');
registerForm.addEventListener('submit', registerHandler);
const registerHeading = document.getElementById('register-heading');

const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', loginHandler);
const loginHeading = document.getElementById('login-heading');

const createForm = document.getElementById('create-form');
createForm.addEventListener('submit', createHandler);
const movieHeading = document.getElementById('movie-heading');

const resultElement = document.getElementById('result');

async function registerHandler(e) {
    e.preventDefault();

    const formData = new FormData(registerForm);

    const email = formData.get('email');
    const password = formData.get('password');
    const repeatPassword = formData.get('repeat-password');

    if (email == '' || password == '') {
        return alert('Email, Password and Repeat Password fields are all required!');
    } else if (password !== repeatPassword) {
        return alert('Password and Repeat Password does not match!');
    }
    const response = await registry.register(email, password);
    if (response == 'ok') {
        registerForm.style.display = 'none';
        registerHeading.style.display = 'none';
    }
}

async function loginHandler(e) {
    e.preventDefault();

    const formData = new FormData(loginForm);

    const email = formData.get('email');
    const password = formData.get('password');

    if (email == '' || password == '') {
        return alert('Email, Password and Repeat Password fields are all required!');
    }

    const response = await registry.login(email, password);
    if (response == 'ok') {
        registerForm.style.display = 'none';
        registerHeading.style.display = 'none';
        loginForm.style.display = 'none';
        loginHeading.style.display = 'none';
        createForm.classList.remove('hidden');
        movieHeading.classList.remove('hidden');
        resultElement.classList.remove('hidden');
        generateData();
    }
}

async function createHandler(e) {
    e.preventDefault();

    const formData = new FormData(createForm);
    let title = formData.get('title');
    let description = formData.get('description');
    let image = formData.get('img');

    const data = {
        title: title,
        description: description,
        img: image,
    }

    database.postData(data);

    let heading = document.createElement('h1');
    heading.textContent = title;
    let paragraph = document.createElement('p');
    paragraph.textContent = description;
    let img = document.createElement('img');
    img.src = image;

    resultElement.appendChild(heading);
    resultElement.appendChild(img);
    resultElement.appendChild(paragraph);

    createForm.reset();
}

async function generateData() {
    let data = await database.getData();

    data.forEach(movie => {
        let heading = document.createElement('h1');
        heading.textContent = movie.title;
        let paragraph = document.createElement('p');
        paragraph.textContent = movie.description;
        let img = document.createElement('img');
        img.src = movie.img;

        resultElement.appendChild(heading);
        resultElement.appendChild(img);
        resultElement.appendChild(paragraph);
    });
}