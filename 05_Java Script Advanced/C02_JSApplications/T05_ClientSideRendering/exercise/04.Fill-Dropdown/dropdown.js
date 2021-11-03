import { html, render } from "./node_modules/lit-html/lit-html.js";
import * as server from './requests.js';

const rootElement = document.getElementById('menu');
const textField = document.getElementById('itemText');

solution();

function solution() {
    updateApplication();

    document.getElementById('btnAdd').addEventListener('click', async (e) => {
        e.preventDefault();
        if (textField.value.trim() != '') {
            await server.postData({ text: textField.value });
            textField.value = '';
            updateApplication();
        }
    });
}

async function updateApplication() {
    const data = await server.getData();

    const template = (input) => html`<option value="${input._id}">${input.text}</option>`;

    const dropdownElements = Object.values(data).map(template);

    render(dropdownElements, rootElement);
}