import { html, render } from './node_modules/lit-html/lit-html.js'

const loadButton = document.getElementById('btnLoadTowns');
const inputField = document.getElementById('towns');
const root = document.getElementById('root');

const template = (towns) => html`
<ul>
    ${towns.map(town => html`<li>${town}</li>`)}
</ul>
`
loadButton.addEventListener('click', (e) => {
    e.preventDefault();
    const towns = inputField.value.split(',').map(e => e.trim());
    render(template(towns), root);
});