import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, remove } from '../io/requests.js';

const ownerTemplate = (id, ctx) => html`
<a class="button warning" href="/edit/${id}">Edit</a>
<button @click=${() => deleteHandler(id, ctx)} class="button danger">Delete</button>
`;

const detailsTemplate = (data, ctx) => html`
        <section id="meme-details">
            <h1>Meme Title: ${data.title}</h1>
            <div class="meme-details">
                <div class="meme-img">
                    <img alt="meme-alt" src=${data.imageUrl}>
                </div>
                <div class="meme-description">
                    <h2>Meme Description</h2>
                    <p>
                        ${data.description}
                    </p>
                   ${sessionStorage.getItem('id') === data._ownerId ? ownerTemplate(data._id, ctx) : ''}
                </div>
            </div>
        </section>
`;

export async function detailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    ctx.render(detailsTemplate(data, ctx));
}

async function deleteHandler(id, ctx) {
    const confirmed = confirm('Are you sure you want to delete this meme?');
    if (confirmed) {
        await remove(id);
        ctx.page.redirect('/all-memes');
    }
}