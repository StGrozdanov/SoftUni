import { html } from '../../node_modules/lit-html/lit-html.js';
import { create } from '../io/requests.js';

const createTemplate = (ctx) => html`
<section id="create-page" class="create">
    <form id="create-form" action="" method="">
        <fieldset>
            <legend>Add new Book</legend>
            <p class="field">
                <label for="title">Title</label>
                <span class="input">
                    <input type="text" name="title" id="title" placeholder="Title">
                </span>
            </p>
            <p class="field">
                <label for="description">Description</label>
                <span class="input">
                    <textarea name="description" id="description" placeholder="Description"></textarea>
                </span>
            </p>
            <p class="field">
                <label for="image">Image</label>
                <span class="input">
                    <input type="text" name="imageUrl" id="image" placeholder="Image">
                </span>
            </p>
            <p class="field">
                <label for="type">Type</label>
                <span class="input">
                    <select id="type" name="type">
                        <option value="Fiction">Fiction</option>
                        <option value="Romance">Romance</option>
                        <option value="Mistery">Mistery</option>
                        <option value="Classic">Clasic</option>
                        <option value="Other">Other</option>
                    </select>
                </span>
            </p>
            <input @click=${(e) => createHandler(e, ctx)} class="button submit" type="submit" value="Add Book">
        </fieldset>
    </form>
</section>
`;

export function createPage(context) {
    context.render(createTemplate(context));
}

async function createHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('create-form'));
    const title = form.get('title');
    const description = form.get('description');
    const imageUrl = form.get('imageUrl');
    const type = form.get('type');


    if (description == '' || imageUrl == '' || title == '') {
        return alert('All fields are required!');
    } 
    const createData = {
        title: title,
        description: description,
        imageUrl: imageUrl,
        type: type,
    }
    await create(createData);
    context.page.redirect('/');
}