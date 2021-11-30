import { html } from '../../node_modules/lit-html/lit-html.js';
import { create } from '../io/requests.js';
import { showNotification } from './common/notification.js';

const exampleTemplate = (ctx) => html`
        <section id="create-meme">
            <form id="create-form">
                <div class="container">
                    <h1>Create Meme</h1>
                    <label for="title">Title</label>
                    <input id="title" type="text" placeholder="Enter Title" name="title">
                    <label for="description">Description</label>
                    <textarea id="description" placeholder="Enter Description" name="description"></textarea>
                    <label for="imageUrl">Meme Image</label>
                    <input id="imageUrl" type="text" placeholder="Enter meme ImageUrl" name="imageUrl">
                    <input @click=${(e) => createHandler(e, ctx)} type="submit" class="registerbtn button" value="Create Meme">
                </div>
            </form>
        </section>
`;

export function createPage(context) {
    context.render(exampleTemplate(context));
}

async function createHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('create-form'));
    const title = form.get('title');
    const description = form.get('description');
    const imageUrl = form.get('imageUrl');

    if (description == '' || imageUrl == '' || title == '') {
        return showNotification('All fields are required!');
    } 
    const createData = {
        title: title,
        description: description,
        imageUrl: imageUrl
    }
    await create(createData);
    context.page.redirect('/all-memes');
}