import { html, render } from "./node_modules/lit-html/lit-html.js";
import { towns } from "./towns.js";

const template = (towns) => html`
<ul>${towns.map(town => html`<li>${town}</li>`)}</ul>
`
const townsLocation = document.getElementById('towns');
render(template(towns), townsLocation);

const inputField = document.getElementById('searchText');
const searchButton = document.getElementById('searchBtn');
const townsFoundField = document.getElementById('result');

searchButton.addEventListener('click', (e) => {
   const townListItems = e.target.parentNode.children[0].querySelectorAll('ul li');
   unselectAllTowns(townListItems);

   if (inputField.value.trim() != '') {
      const searchCriteria = inputField.value;
      findAndSelectTowns(searchCriteria, townListItems);
   } else {
      townsFoundField.textContent = '0 matches found';
   }
});

function unselectAllTowns(items) {
   Array.from(items).forEach(t => t.classList.remove('active'));
}

function findAndSelectTowns(criteria, towns) {
   const townsFound = Array.from(towns)
      .filter(t => t.textContent.toLowerCase().includes(criteria.toLowerCase()));

   townsFound.forEach(t => t.classList.add('active'));

   townsFoundField.textContent = `${townsFound.length} matches found`
   inputField.value = '';
}