import { render } from "./node_modules/lit-html/lit-html.js";
import { contacts } from "./contacts.js";
import template from './template.js';

const divContainer = document.getElementById('contacts');
const contactStructure = contacts.map(template);
render(contactStructure, divContainer);