import { html } from '../../node_modules/lit-html/lit-html.js';
import { getMyPublications } from '../io/requests.js'

const noListingsTemplate = () => html`<p class="no-cars"> You haven't listed any cars yet.</p>`;

const listingTemplate = (data) => html`
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
`;

const allListingsTemplate = (listings) => html`
<section id="my-listings">
    <h1>My car listings</h1>
    <div class="listings">
        ${listings}
    </div>
`;

export async function myListingsPage(ctx) {
    const data = await getMyPublications(sessionStorage.getItem('id'));
    if (data.length > 0) {
        const listings = data.map(listingTemplate);
        ctx.render(allListingsTemplate(listings));
    } else {
        ctx.render(allListingsTemplate(noListingsTemplate()));
    }
}