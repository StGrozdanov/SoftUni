import { html, render } from '../../node_modules/lit-html/lit-html.js';
import { getFurniture, updateFurniture } from '../requests.js';

const container = document.querySelector('.container');

const editTemplate = (furniture, context, make, model, year, description, price, img) => html`
<div class="row space-top">
    <div class="col-md-12">
        <h1>Edit Furniture</h1>
        <p>Please fill all fields.</p>
    </div>
</div>
<form id="edit-form">
    <div class="row space-top">
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="new-make">Make</label>
                <input class="form-control ${styleEmptyFields(make)}" id="new-make" type="text" name="make"
                    value="${furniture.make}">
            </div>
            <div class="form-group has-success">
                <label class="form-control-label" for="new-model">Model</label>
                <input class="form-control ${styleEmptyFields(model)}" id="new-model" type="text" name="model"
                    value="${furniture.model}">
            </div>
            <div class="form-group has-danger">
                <label class="form-control-label" for="new-year">Year</label>
                <input class="form-control ${styleEmptyFields(year)}" id="new-year" type="number" name="year"
                    value="${furniture.year}">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-description">Description</label>
                <input class="form-control ${styleEmptyFields(description)}" id="new-description" type="text"
                    name="description" value="${furniture.description}">
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label class="form-control-label" for="new-price">Price</label>
                <input class="form-control ${styleEmptyFields(price)}" id="new-price" type="number" name="price"
                    value="${furniture.price}">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-image">Image</label>
                <input class="form-control ${styleEmptyFields(img)}" id="new-image" type="text" name="img"
                    value="${furniture.img}">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="new-material">Material (optional)</label>
                <input class="form-control" id="new-material" type="text" name="material" value="${furniture.material}">
            </div>
            <input @click=${(e) => editHandler(e, context)} type="submit" class="btn btn-info" value="Edit" />
        </div>
    </div>
</form>
</div>
`;

export async function editPage(context) {
    const targetFurniture = await getFurniture(context.params.id);
    render(editTemplate(targetFurniture, context), container);
}

async function editHandler(e, context) {
    e.preventDefault();
    const targetFurniture = await getFurniture(context.params.id);

    const editForm = document.getElementById('edit-form');
    const form = new FormData(editForm);
    const make = form.get('make');
    const model = form.get('model');
    const year = form.get('year');
    const description = form.get('description');
    const price = form.get('price');
    const img = form.get('img');
    const material = form.get('material');

    if (make == '' || model == '' || year == '' || description == '' || price == '' || img == '') {
        render(editTemplate(targetFurniture, context, make, model, year, description, price, img), container);
        return alert('All fields, except material are required!');
    }

    if (fieldsAreValid(make, model, year, description, price)) {
        const data = {
            make: make,
            model: model,
            year: year,
            description: description,
            price: price,
            img: img,
            material: material
        }
        const id = context.params.id;
        await updateFurniture(id, data);
        context.page.redirect('/');
    }
}

function fieldsAreValid(make, model, year, description, price) {

    const invalidFieldsContainer = [];
    const validFieldsContainer = [];

    if (make.length < 4) {
        alert('Make must be at least 4 symbols long');
        invalidFieldsContainer.push('make');
    } else {
        validFieldsContainer.push('make');
    }

    if (model.length < 4) {
        alert('Model must be at least 4 symbols long');
        invalidFieldsContainer.push("model");
    } else {
        validFieldsContainer.push('model');
    }

    if (Number(year) < 1950 || Number(year) > 2050) {
        alert('Year must be between 1950 and 2050');
        invalidFieldsContainer.push("year");
    } else {
        validFieldsContainer.push('year');
    }

    if (description.length <= 10) {
        alert('Description must be more than 10 symbols');
        invalidFieldsContainer.push('description');
    } else {
        validFieldsContainer.push('description');
    }

    if (Number(price) < 0) {
        alert('Price must be a positive number');
        invalidFieldsContainer.push('price');
    } else {
        validFieldsContainer.push('price');
    }

    //image field is always valid if it's not empty and if it's in this step it's not empty.
    const imageField = document.querySelector('#new-image');
    imageField.classList.remove('is-invalid');
    imageField.classList.add('is-valid');

    //style valid fields
    if (invalidFieldsContainer.length === 0) {
        validFieldsContainer.forEach(f => {
            document.querySelector(`#new-${f}`).classList.add('is-valid');
        });
        return true;
    } else {
        //remove invalid fields style if they are corrected
        validFieldsContainer.forEach(f => {
            const field = document.querySelector(`#new-${f}`);
            field.classList.remove('is-invalid');
            field.classList.add('is-valid');
        });
        //style invalid fields
        invalidFieldsContainer.forEach(f => {
            document.querySelector(`#new-${f}`).classList.add('is-invalid');
        });
        return false;
    }
}

function styleEmptyFields(value) {
    return value != '' ? 'is-valid' : 'is-invalid';
}