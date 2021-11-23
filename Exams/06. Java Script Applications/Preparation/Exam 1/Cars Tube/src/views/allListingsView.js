import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js'

const noListingsTemplate = () => html`<p class="no-cars">No cars in database.</p>`;

// {
//     "_ownerId": "35c62d76-8152-4626-8712-eeb96381bea8",
//     "brand": "Audi",
//     "model": "A3",
//     "description": "Lorem ipsum dolor sit, amet consectetur adipisicing elit.",
//     "year": 2018,
//     "imageUrl": "/images/audia3.jpg",
//     "price": 25000,
//     "_createdOn": 1616162253496,
//     "_id": "3987279d-0ad4-4afb-8ca9-5b256ae3b298"
// }

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
<section id="car-listings">
    <h1>Car Listings</h1>
    <div class="listings">
        ${listings}
    </div>
`;

export async function allListingsPage(ctx) {
    const data = await getAll();
    if (data.length > 0) {
        const listings = data.map(listingTemplate);
        ctx.render(allListingsTemplate(listings));
    } else {
        ctx.render(allListingsTemplate(noListingsTemplate()));
    }
}