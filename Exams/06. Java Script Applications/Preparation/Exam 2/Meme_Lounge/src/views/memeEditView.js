import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js';
import { showNotification } from './common/notification.js';

const editTemplate = (data, ctx) => html`
        <section id="edit-meme">
            <form id="edit-form">
                <h1>Edit Meme</h1>
                <div class="container">
                    <label for="title">Title</label>
                    <input id="title" type="text" placeholder="Enter Title" name="title" value=${data.title}>
                    <label for="description">Description</label>
                    <textarea id="description" placeholder="Enter Description" name="description">${data.description}</textarea>
                    <label for="imageUrl">Image Url</label>
                    <input id="imageUrl" type="text" placeholder="Enter Meme ImageUrl" name="imageUrl" value=${data.imageUrl}>
                    <input @click=${(e) => editHandler(e, ctx)} type="submit" class="registerbtn button" value="Edit Meme">
                </div>
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

    if (description == '' || imageUrl == '' || title == '') {
        return showNotification('All fields are required!');
    } 
    const editData = {
        title: title,
        description: description,
        imageUrl: imageUrl
    }
    await update(context.params.id, editData);
    context.page.redirect(`/details/${context.params.id}`);
}