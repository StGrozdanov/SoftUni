const homePage = document.getElementById('home-view');
const homeButton = document.querySelector('#home');
const guestButtons = document.querySelector('#guest').children;
const logoutButton = document.querySelector('#logout');
const user = document.querySelector('#user');

//login page elements
const loginPage = document.getElementById('login-view');
loginPage.style.display = 'none';
const loginEmail = document.querySelector('#login-form > label:nth-child(1) > input[type=text]');
const loginPassword = document.querySelector('#login-form > label:nth-child(2) > input[type=password]');
const loginFieldButton = document.querySelector('#login-form > button');

loginFieldButton.addEventListener('click', (e) => {
    e.preventDefault();
    if (loginEmail.value.trim() == '' || loginPassword.value.trim() == '') {
        alert('Both email and password fields are required for successfull login.');
        throw new Error();
    }
    const email = loginEmail.value;
    const password = loginPassword.value;
    loginUser(email, password);
    loginEmail.value = '';
    loginPassword.value = '';
});

//register page elements
const registerPage = document.getElementById('register-view');
registerPage.style.display = 'none';
const registerEmail = document.querySelector('#register > label:nth-child(1) > input[type=text]');
const registerPassword = document.querySelector('#register > label:nth-child(2) > input[type=password]');
const registerConfirmPassword = document.querySelector('#register > label:nth-child(3) > input[type=password]');
const registerButton = document.querySelector('#register > button');

registerButton.addEventListener('click', (e) => {
    e.preventDefault();
    if (registerEmail.value.trim() == '' || registerPassword.value.trim() == '' ||
        registerConfirmPassword.value.trim() == '') {
        alert('Both email, password and confirm password fields are required for successfull registration.');
        throw new Error();
    }
    if (registerPassword.value !== registerConfirmPassword.value) {
        alert('Password and Confirm Password should match!');
        throw new Error();
    }
    const email = registerEmail.value;
    const password = registerPassword.value;
    registerUser(email, password);
    loginUser(email, password);
    registerEmail.value = '';
    registerPassword.value = '';
    registerConfirmPassword.value = '';
});

//guest buttons
for (const button of guestButtons) {
    button.addEventListener('click', (e) => {
        for (const button of guestButtons) {
            button.classList.remove('active');
        }
        homeButton.classList.remove('active');
        logoutButton.classList.remove('active');
        e.currentTarget.classList.add('active');

        if (button.textContent == 'Login') {
            homePage.classList.add('hidden');
            registerPage.style.display = 'none';
            loginPage.style.display = 'flex';
        } else {
            homePage.classList.add('hidden');
            registerPage.style.display = 'flex';
            loginPage.style.display = 'none';
        }
    });
}

//homepage
homeButton.addEventListener('click', homePageHandler);
const fieldSet = document.getElementById('main');
fieldSet.classList.add('hidden');
const loadButton = document.querySelector('#home-view > aside > button');
const preview = document.querySelector('.preview');
loadButton.addEventListener('click', (e) => {
    fieldSet.classList.remove('hidden');
    preview.classList.add('hidden');
    getData();
});

function homePageHandler() {
    for (const button of guestButtons) {
        button.classList.remove('active');
    }
    logoutButton.classList.remove('active');
    homeButton.classList.add('active');
    homePage.classList.remove('hidden');
    registerPage.style.display = 'none';
    loginPage.style.display = 'none';
}

//requests
async function registerUser(email, password) {
    const response = await fetch(`http://localhost:3030/users/register`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (response.ok) {
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        homePageHandler();
    } else {
        const error = await response.json();
        alert(error.message);
    }
}

async function loginUser(email, password) {
    const response = await fetch(`http://localhost:3030/users/login`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (response.ok) {
        const data = await response.json();
        sessionStorage.setItem('authToken', data.accessToken);
        homePageHandler();
        user.classList.remove('hidden');
        for (const button of guestButtons) {
            button.classList.add('hidden');
        }
        document.querySelector('body > header > nav > p > span').textContent = email;
        document.querySelector('#addForm > fieldset > button').disabled = false;
        const results = document.getElementById('catches').children;
        loadButton.removeEventListener('click', (e));
        loadButton.addEventListener('click', () => {
            for (const result of results) {
                if (result.id === data._id) {
                    console.log('owner');
                } else {
                    const deleteButton = result.lastChild;
                    const updateButton = result.children[12];
                    deleteButton.disabled = false;
                    updateButton.disabled = false;
                }
            }
        });
    } else {
        const error = await response.json();
        alert(error.message);
    }
}

async function getData() {
    const response = await fetch(`http://localhost:3030/data/catches`);
    const data = await response.json();
    document.getElementById('catches').textContent = '';
    data.forEach(catche => appendCatches(catche));
}

async function postData() {

}

function appendCatches(catche) {
    const div = createElement('div', 'catch');
    div.id = catche._id;
    const labelAngler = createElement('label', null, 'Angler');
    const inputAngler = createElement('input', 'angler');
    inputAngler.value = catche.angler;
    const labelWeight = createElement('label', null, 'Weight');
    const inputWeight = createElement('input', 'weight');
    inputWeight.value = catche.weight;
    const labelSpecies = createElement('label', null, 'Species');
    const inputSpecies = createElement('input', 'species');
    inputSpecies.value = catche.species;
    const labelLocation = createElement('label', null, 'Location');
    const inputLocation = createElement('input', 'location');
    inputLocation.value = catche.location;
    const labelBait = createElement('label', null, 'Bait');
    const inputBait = createElement('input', 'bait');
    inputBait.value = catche.bait;
    const labelCapture = createElement('label', null, 'Capture Time');
    const inputCapture = createElement('input', 'captureTime');
    inputCapture.value = catche.captureTime;
    const buttonUpdate = createElement('button', 'update', 'Update');
    const buttonDelete = createElement('button', 'delete', 'Delete');
    buttonUpdate.disabled = true;
    buttonDelete.disabled = true;

    div.appendChild(labelAngler);
    div.appendChild(inputAngler);
    div.appendChild(labelWeight);
    div.appendChild(inputWeight);
    div.appendChild(labelSpecies);
    div.appendChild(inputSpecies);
    div.appendChild(labelLocation);
    div.appendChild(inputLocation);
    div.appendChild(labelBait);
    div.appendChild(inputBait);
    div.appendChild(labelCapture);
    div.appendChild(inputCapture);
    div.appendChild(buttonUpdate);
    div.appendChild(buttonDelete);

    document.getElementById('catches').appendChild(div);
}

function createElement(type, clazz, content) {
    const result = document.createElement(type);
    if (clazz) {
        result.classList.add(clazz);
    } if (content) {
        result.textContent = content;
    }
    return result;
}