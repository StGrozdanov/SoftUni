import { html } from './node_modules/lit-html/lit-html.js';
import movieTemplate from './baseTemplate.js';

export default (movies) => html`
    <ul class="movie-list">
        ${movies.map(movie => html`<li>${movieTemplate(movie)}</li>`)}
    </ul>
`;