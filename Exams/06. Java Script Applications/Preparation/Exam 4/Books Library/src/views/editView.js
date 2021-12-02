import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js';

const editTemplate = (data, ctx) => html`
<section id="edit-page" class="edit">
    <form id="edit-form" action="#" method="">
        <fieldset>
            <legend>Edit my Book</legend>
            <p class="field">
                <label for="title">Title</label>
                <span class="input">
                    <input type="text" name="title" id="title" value=${data.title}>
                </span>
            </p>
            <p class="field">
                <label for="description">Description</label>
                <span class="input">
                    <textarea name="description"
                        id="description">${data.description}</textarea>
                </span>
            </p>
            <p class="field">
                <label for="image">Image</label>
                <span class="input">
                    <input type="text" name="imageUrl" id="image" value=${data.imageUrl}>
                </span>
            </p>
            <p class="field">
                <label for="type">Type</label>
                <span class="input">
                    <select id="type" name="type" value=${data.type}>
                        <option value="Fiction">Fiction</option>
                        <option value="Romance">Romance</option>
                        <option value="Mistery">Mistery</option>
                        <option value="Classic">Clasic</option>
                        <option value="Other">Other</option>
                    </select>
                </span>
            </p>
            <input @click=${(e) => editHandler(e, ctx)} class="button submit" type="submit" value="Save">
        </fieldset>
    </form>
</section>
`;

export async function editPage(context) {
    const data = await getSingle(context.params.id);
    context.render(editTemplate(data, context));
}

async function editHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('edit-form'));
    const title = form.get('title');
    const description = form.get('description');
    const imageUrl = form.get('imageUrl');
    const type = form.get('type');

    if (description == '' || imageUrl == '' || title == '') {
        return alert('All fields are required!');
    } 
    const editData = {
        title: title,
        description: description,
        imageUrl: imageUrl,
        type: type,
    }
    await update(context.params.id, editData);
    context.page.redirect(`/details/${context.params.id}`);
}