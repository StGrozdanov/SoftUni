import { html } from './node_modules/lit-html/lit-html.js';

export default (movie) => html`
<div>
    <img src="${movie.img}">
    <div>
        <h2>${movie.title}</h2>
    </div>
    <div>
        <h5>${movie.description}</h5>
    </div>
    <div>
        <button>Details</button>
    </div>
</div>`;

