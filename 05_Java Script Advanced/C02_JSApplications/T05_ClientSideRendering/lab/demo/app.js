import { getData } from "./server.js";
import template from './listTemplate.js';
import { render } from './node_modules/lit-html/lit-html.js';

const targetElement = document.getElementById('container');

async function solve() {
    const movies = await getData();
    render(template(movies), targetElement);
}

solve();