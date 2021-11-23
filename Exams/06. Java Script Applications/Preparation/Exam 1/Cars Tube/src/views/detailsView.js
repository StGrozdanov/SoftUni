import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, remove } from '../io/requests.js'

const ownerTemplate = (ctx, data) => html`
        <div class="listings-buttons">
            <a href="/edit-car/${data._id}" class="button-list">Edit</a>
            <a @click=${(e)=> deleteHandler(e, ctx)} href="#" class="button-list">Delete</a>
        </div>
`;

const carDetailsTemplate = (data, ctx) => html`
<section id="listing-details">
    <h1>Details</h1>
    <div class="details-info">
        <img src="${data.imageUrl}">
        <hr>
        <ul class="listing-props">
            <li><span>Brand:</span>${data.brand}</li>
            <li><span>Model:</span>${data.model}</li>
            <li><span>Year:</span>${data.year}</li>
            <li><span>Price:</span>${data.price}$</li>
        </ul>

        <p class="description-para">${data.description}</p>

        ${sessionStorage.getItem('id') === data._ownerId ? ownerTemplate(ctx, data) : ''}
    </div>
</section>
`;

export async function carDetailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    ctx.render(carDetailsTemplate(data, ctx));
}

async function deleteHandler(e, ctx) {
    e.preventDefault();

    if (confirm('Are you sure you want to delete this car?')) {
        await remove(ctx.params.id);
        ctx.page.redirect('/all-listings');
    }
}