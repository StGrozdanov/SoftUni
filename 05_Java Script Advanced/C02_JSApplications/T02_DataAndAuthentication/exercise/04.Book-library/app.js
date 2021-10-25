//submit form
const submitForm = document.getElementById('submit-form');
const author = document.getElementById('author');
const title = document.getElementById('title');
//table
const table = document.querySelector('tbody');
const loadButton = document.getElementById('loadBooks');
const submitButton = document.getElementById('submit');
//edit form
const editForm = document.getElementById('edit-form');
const titleField = document.getElementById('edit-title');
const authorField = document.getElementById('edit-author');
const idField = document.getElementById('edit-id')
const saveButton = document.getElementById('btnSave');

async function getData() {
    table.textContent = '';
    const response = await fetch('http://localhost:3030/jsonstore/collections/books');
    const data = await response.json();
    Object.entries(data).forEach(e => appendBooks(e[1].title, e[1].author, e[0]));
}

async function postData(msg) {
    await fetch('http://localhost:3030/jsonstore/collections/books', {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(msg)
    });
}

async function deleteData(id) {
    await fetch('http://localhost:3030/jsonstore/collections/books/' + `${id}`, {
        method: 'delete',
        headers: { 'Content-Type': 'application/json' }
    });
}

async function updateData(id, content) {
    await fetch('http://localhost:3030/jsonstore/collections/books/' + `${id}`, {
        method: 'put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(content)
    });
}

loadButton.addEventListener('click', getData);

submitButton.addEventListener('click', (e) => {
    e.preventDefault();
    if (author.value.trim() !== '' && title.value.trim() !== '') {
        const newBook = { 'author': author.value, 'title': title.value };
        postData(newBook);
        title.value = '';
        author.value = '';
    }
});

function appendBooks(title, author, id) {
    const resultTr = document.createElement('tr');
    const titleTd = document.createElement('td');
    titleTd.textContent = title;
    const authorTd = document.createElement('td');
    authorTd.textContent = author;
    const buttonTd = document.createElement('td');
    const editButton = document.createElement('button');
    editButton.textContent = 'Edit';
    resultTr.id = id;
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';

    buttonTd.appendChild(editButton);
    buttonTd.appendChild(deleteButton);
    resultTr.appendChild(titleTd);
    resultTr.appendChild(authorTd);
    resultTr.appendChild(buttonTd);

    deleteButton.addEventListener('click', () => deleteData(id));
    saveButton.addEventListener('click', saveHandler);
    editButton.addEventListener('click', () => {
        editForm.style.display = 'block';
        submitForm.style.display = 'none';
        authorField.value = authorTd.textContent;
        titleField.value = titleTd.textContent;
        idField.value = resultTr.id;
    });
    table.appendChild(resultTr);
}

function saveHandler(e) {
    e.preventDefault();
    const content = { 'author': authorField.value, 'title': titleField.value };
    const id = idField.value;
    updateData(id, content);
    editForm.style.display = 'none';
    submitForm.style.display = 'block';
}