import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js'

const editTemplate = (ctx, data) => html`
<section id="edit-listing">
    <div class="container">

        <form id="edit-form">
            <h1>Edit Car Listing</h1>
            <p>Please fill in this form to edit an listing.</p>
            <hr>

            <p>Car Brand</p>
            <input type="text" placeholder="Enter Car Brand" name="brand" value="${data.brand}">

            <p>Car Model</p>
            <input type="text" placeholder="Enter Car Model" name="model" value="${data.model}">

            <p>Description</p>
            <input type="text" placeholder="Enter Description" name="description" value="${data.description}">

            <p>Car Year</p>
            <input type="number" placeholder="Enter Car Year" name="year" value="${data.year}">

            <p>Car Image</p>
            <input type="text" placeholder="Enter Car Image" name="imageUrl" value="${data.imageUrl}">

            <p>Car Price</p>
            <input type="number" placeholder="Enter Car Price" name="price" value="${data.price}">

            <hr>
            <input @click=${(e) => editHandler(e, ctx)} type="submit" class="registerbtn" value="Edit Listing">
        </form>
    </div>
</section>
`;

export async function editPage(ctx) {
    const data = await getSingle(ctx.params.id);
    ctx.render(editTemplate(ctx, data));
}

async function editHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.getElementById('edit-form'));

    const brand = form.get('brand');
    const model = form.get('model');
    const description = form.get('description');
    const year = form.get('year');
    const imageUrl = form.get('imageUrl');
    const price = form.get('price');

    if (brand == '' || model == '' || description == '' || year == '' || imageUrl == '' || price == '') {
        return alert('All fields are required!');
    } if (Number(year) < 0 || Number(price) < 0) {
        return alert('Year and price must be positive numbers!')
    }
    const newCar = {
        brand: brand,
        model: model,
        description: description,
        year: Number(year),
        imageUrl: imageUrl,
        price: Number(price),
    }
    await update(ctx.params.id, newCar);
    ctx.page.redirect(`/car-details/${ctx.params.id}`);
}