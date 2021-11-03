import { html } from './node_modules/lit-html/lit-html.js';
import { deleteData } from './requests.js';
import { getData } from './app.js';

export default (data) => html`
<tr id="${data[0]}">
    <td>${data[1].author}</td>
    <td>${data[1].title}</td>
    <td>
        <button @click=${editHandler}>Edit</button>
        <button @click=${deleteHandler}>Delete</button>
    </td>
</tr>
`;

const editForm = document.getElementById('edit-form');
const addForm = document.getElementById('add-form');
const editAuthorField = document.getElementById('edit-author');
const editTitleField = document.getElementById('edit-title');
const editIdField = document.getElementById('edit-id');

async function editHandler(e) {
    editForm.classList.remove('hidden');
    addForm.classList.add('hidden');
    editAuthorField.value = e.target.parentNode.parentNode.children[0].textContent;
    editTitleField.value = e.target.parentNode.parentNode.children[1].textContent;
    editIdField.value = e.target.parentNode.parentNode.id;
    await getData();
}

async function deleteHandler(e) {
    const targetElementId = e.target.parentNode.parentNode.id;
    await deleteData(targetElementId);
    await getData();
}