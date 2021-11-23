import { html } from '../../node_modules/lit-html/lit-html.js';
import { create } from '../io/requests.js';

const createCarTemplate = (context) => html`
<section id="create-listing">
    <div class="container">
        <form id="create-form">
            <h1>Create Car Listing</h1>
            <p>Please fill in this form to create an listing.</p>
            <hr>

            <p>Car Brand</p>
            <input type="text" placeholder="Enter Car Brand" name="brand">

            <p>Car Model</p>
            <input type="text" placeholder="Enter Car Model" name="model">

            <p>Description</p>
            <input type="text" placeholder="Enter Description" name="description">

            <p>Car Year</p>
            <input type="number" placeholder="Enter Car Year" name="year">

            <p>Car Image</p>
            <input type="text" placeholder="Enter Car Image" name="imageUrl">

            <p>Car Price</p>
            <input type="number" placeholder="Enter Car Price" name="price">

            <hr>
            <input @click=${(e)=> createHandler(e, context)} type="submit" class="registerbtn" value="Create Listing">
        </form>
    </div>
</section>
`;

export function createPage(context) {
    context.render(createCarTemplate(context));
}

async function createHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('create-form'));

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
    await create(newCar);
    context.page.redirect('/all-listings');
}