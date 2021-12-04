import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, remove } from '../io/requests.js';

const ownerTemplate = (id, ctx) => html`
            <div class="actionBtn">
                <a href="/edit/${id}" class="edit">Edit</a>
                <a @click=${() => deleteHandler(id, ctx)} href="javascript:void[0]" class="remove">Delete</a>
            </div>
`;

const detailsTemplate = (data, ctx) => html`
<section id="detailsPage">
    <div class="wrapper">
        <div class="albumCover">
            <img src=${data.imgUrl}>
        </div>
        <div class="albumInfo">
            <div class="albumText">

                <h1>Name: ${data.name}</h1>
                <h3>Artist: ${data.artist}</h3>
                <h4>Genre: ${data.genre}</h4>
                <h4>Price: $${data.price}</h4>
                <h4>Date: ${data.releaseDate}</h4>
                <p>Description: ${data.description}</p>
            </div>

            ${sessionStorage.getItem('id') !== null && sessionStorage.getItem('id') === data._ownerId 
            ? ownerTemplate(data._id, ctx)
            : ''}

        </div>
    </div>
</section>
`;

export async function detailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    ctx.render(detailsTemplate(data, ctx));
}

async function deleteHandler(id, ctx) {
    const confirmed = confirm('Are you sure you want to delete this album?');
    if (confirmed) {
        await remove(id);
        ctx.page.redirect('/catalog');
    }
}