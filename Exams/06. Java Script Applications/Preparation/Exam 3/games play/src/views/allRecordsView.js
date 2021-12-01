import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js';

const noRecordsTemplate = () => html`
<h3 class="no-articles">No articles yet</h3>
`;

const allRecordsTemplate = (games) => html`
<section id="catalog-page">
    <h1>All Games</h1>
    ${games ? games : noRecordsTemplate()}
</section>
`;

const gamesTemplate = (data) => html`
<div class="allGames">
        <div class="allGames-info">
            <img src=${data.imageUrl}>
            <h6>${data.category}</h6>
            <h2>${data.title}</h2>
            <a href="/details/${data._id}" class="details-button">Details</a>
        </div>
    </div>
`;

export async function allRecordsPage(ctx) {
    const data = await getAll();

    let template;

    if (data.length > 0) {
        const games = data.map(gamesTemplate);
        template = allRecordsTemplate(games);
    } else {
        template = allRecordsTemplate();
    }

    ctx.render(template);
}