function attachEvents() {
    const baseUrl = 'http://localhost:3030/jsonstore/phonebook';
    const phoneBook = document.getElementById('phonebook');
    const loadButton = document.getElementById('btnLoad');
    const createButton = document.getElementById('btnCreate');
    const personField = document.getElementById('person');
    const phoneField = document.getElementById('phone');
    loadButton.addEventListener('click', () => getData(baseUrl, phoneBook));
    createButton.addEventListener('click', () => {
        const msg = { 'person': personField.value, 'phone': phoneField.value };
        postData(baseUrl, msg);
        personField.value = '';
        phoneField.value = '';
    });
}

attachEvents();

async function getData(baseUrl, phoneBook) {
    phoneBook.textContent = '';
    const response = await fetch(baseUrl);
    const data = await response.json();
    Object.values(data).forEach(e => {
        const key = e._id;
        const person = e.person;
        const phone = e.phone;
        const result = `${person}: ${phone}`;

        const li = document.createElement('li');
        li.textContent = result;

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => {
            deleteHandler(baseUrl, key);
            getData(baseUrl, phoneBook);
        });

        li.appendChild(deleteButton);
        phoneBook.appendChild(li);
    });
}

async function deleteHandler(baseUrl, key) {
    await fetch(`${baseUrl}/${key}`, {
        method: 'delete',
        headers: { 'Content-Type': 'application/json' },
    });
}

async function postData(baseUrl, message) {
    await fetch(baseUrl, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(message),
    });
}