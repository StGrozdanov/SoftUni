import { render } from './node_modules/lit-html/lit-html.js';
import * as server from './requests.js';
import template from './template.js';

//load data
const rootElement = document.getElementById('books');
const loadButton = document.getElementById('loadBooks');
loadButton.addEventListener('click', getData);

//submit data
const addForm = document.getElementById('add-form');
const addTitle = document.getElementById('add-title');
const addAuthor = document.getElementById('add-author');
const submitButton = document.getElementById('btnSubmit');
submitButton.addEventListener('click', submitData);

//edit data
const editAuthorField = document.getElementById('edit-author');
const editTitleField = document.getElementById('edit-title');
const editIdField = document.getElementById('edit-id');
const saveButton = document.getElementById('btnSave');
saveButton.addEventListener('click', saveData);


export async function getData() {
    const data = await server.getData();
    const books = Object.entries(data).map(template);
    render(books, rootElement);
}

async function submitData(e) {
    e.preventDefault();
    if (addAuthor.value.trim() !== '' && addTitle.value.trim() !== '') {
        const newBook = { 'author': addAuthor.value, 'title': addTitle.value };
        await server.postData(newBook);
        addTitle.value = '';
        addAuthor.value = '';
        await getData();
    }
}

async function saveData(e) {
    e.preventDefault();
    const content = { 'author': editAuthorField.value, 'title': editTitleField.value };
    await server.updateData(content, editIdField.value);
    e.target.parentNode.classList.add('hidden');
    addForm.classList.remove('hidden');
    await getData();
}