import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { getMyFurniture } from '../requests.js';

const container = document.querySelector('.container');

const myFurnitureTemplate = (furnitures) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>My Furniture</h1>
        <p>This is a list of your publications.</p>
    </div>
</div>

<div class="row space-top">
    ${furnitures}
</div>
`;

const furnitureTemplate = (data) => html`
<div class="col-md-4" id="${data._id}">
    <div class="card text-white bg-primary">
        <div class="card-body">
            <img src="${data.img}" />
            <p>${data.description}</p>
            <footer>
                <p>Price: <span>${data.price} $</span></p>
            </footer>
            <div>
                <a href="/details/${data._id}" class="btn btn-info">Details</a>
            </div>
        </div>
    </div>
</div>
`;

export async function myFurniture() {
    const data = await getMyFurniture(sessionStorage.getItem('id'));
    const furnitures = data.map(furnitureTemplate);
    render(myFurnitureTemplate(furnitures), container);
}