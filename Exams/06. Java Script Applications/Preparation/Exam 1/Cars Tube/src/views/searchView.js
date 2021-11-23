import { html } from '../../node_modules/lit-html/lit-html.js';
import { findCarsByYear } from '../io/requests.js';

const allRecordsTemplate = (data) => html`
    <h2>Results:</h2>
    <div class="listings">
        <div class="listing">
            <div class="preview">
                <img src="${data.imageUrl}">
            </div>
            <h2>${data.brand} ${data.model}</h2>
            <div class="info">
                <div class="data-info">
                    <h3>Year: ${data.year}</h3>
                    <h3>Price: ${data.price} $</h3>
                </div>
                <div class="data-buttons">
                    <a href="/car-details/${data._id}" class="button-carDetails">Details</a>
                </div>
            </div>
        </div>
    </div>
`;

const noRecordsTemplate = () => html`
<h2>Results:</h2>
<div class="listings">
    <p class="no-cars"> No results.</p>
</div>
`;

const searchTemplate = (ctx, data) => html`
<section id="search-cars">
    <h1>Filter by year</h1>

    <div class="container">
        <input id="search-input" type="text" name="search" placeholder="Enter desired production year">
        <button @click=${(e)=> searchHandler(e, ctx)} class="button-list">Search</button>
    </div>
    ${data ? data : ''}
</section>
`;

export async function searchPage(ctx) {
    ctx.render(searchTemplate(ctx));
}

async function searchHandler(e, ctx) {
    e.preventDefault();

    const query = Number(document.getElementById('search-input').value);
    const data = await findCarsByYear(query);

    let results;

    if (data.length > 0) {
        const records = data.map(allRecordsTemplate);
        results = records;
    } else {
        const noData = noRecordsTemplate();
        results = noData;
    }
    ctx.render(searchTemplate(ctx, results));
}