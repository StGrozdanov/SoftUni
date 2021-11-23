import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { deleteFurniture, getFurniture } from '../requests.js';

const container = document.querySelector('.container');

const detailsTemplate = (furniture, isOwner, context) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Furniture Details</h1>
    </div>
</div>
<div class="row space-top">
    <div class="col-md-4">
        <div class="card text-white bg-primary">
            <div class="card-body">
                <img src="${furniture.img}" />
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <p>Make: <span>${furniture.make}</span></p>
        <p>Model: <span>${furniture.model}</span></p>
        <p>Year: <span>${furniture.year}</span></p>
        <p>Description: <span>${furniture.description}</span></p>
        <p>Price: <span>${furniture.price}$</span></p>
        <p>Material: <span>${furniture.material}</span></p>
        <div>
            <a href="/edit/${furniture._id}" class="btn btn-info ${isOwner() ? '' : 'hidden'}">Edit</a>
            <a @click=${(e) => deleteHandler(e, context)} href=”#” class="btn btn-red ${isOwner() ? '' :
        'hidden'}">Delete</a>
        </div>
    </div>
</div>
`;

export async function detailsPage(context) {
    const targetFurniture = await getFurniture(context.params.id);
    render(detailsTemplate(targetFurniture, isOwner, context), container);

    function isOwner() {
        return sessionStorage.getItem('id') === targetFurniture._ownerId;
    }
}

async function deleteHandler(e, context) {
    e.preventDefault();
    let confirmed = confirm('Are you sure you want to delete this item?');
    if (confirmed) {
        await deleteFurniture(context.params.id);
        context.page.redirect('/');
    }
}