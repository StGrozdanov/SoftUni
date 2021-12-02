import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, getTotalLikesForBook, getTotalLikesFromAUser, likeABook, remove } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const ownerTemplate = (id, ctx) => html`
<a class="button" href="/edit/${ctx.params.id}">Edit</a>
            <a @click=${() => deleteHandler(id, ctx)} class="button" href="javascript:void[0]">Delete</a>
`;

const likeButtonTemplate = (ctx) => html`
 <a @click=${(e) => likeHandler(e, ctx)} class="button" href="javascript:void[0]">Like</a>
`;

const detailsTemplate = (data, ctx, alreadyLiked, totalLikes) => html`
       <section id="details-page" class="details">
    <div class="book-information">
        <h3>${data.title}</h3>
        <p class="type">Type: ${data.type}</p>
        <p class="img"><img src=${data.imageUrl}></p>
        <div class="actions">
            ${sessionStorage.getItem('id') === data._ownerId ? ownerTemplate(data._id, ctx) : ''}

            ${userIsLoggedIn() && sessionStorage.getItem('id') !== data._ownerId && !alreadyLiked ? likeButtonTemplate(ctx) : ''}

            <div class="likes">
                <img class="hearts" src="/images/heart.png">
                <span id="total-likes">Likes: ${totalLikes}</span>
            </div>
        </div>
    </div>
    <div class="book-description">
        <h3>Description:</h3>
        <p>${data.description}</p>
    </div>
</section>
`;

export async function detailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    const totalLikes = await getTotalLikesForBook(ctx.params.id);
    const alreadyLiked = await userAlreadyLikedThatBook(ctx);

    ctx.render(detailsTemplate(data, ctx, alreadyLiked, totalLikes));
}

async function deleteHandler(id, ctx) {
    const confirmed = confirm('Are you sure you want to delete this book?');

    if (confirmed) {
        await remove(id);
        ctx.page.redirect('/');
    }
}

async function userAlreadyLikedThatBook(ctx) {
    let alreadyLiked;

    sessionStorage.getItem('id') !== null 
    ? alreadyLiked = await getTotalLikesFromAUser(ctx.params.id, sessionStorage.getItem('id'))
    : alreadyLiked === 1;

    return alreadyLiked;
}

async function likeHandler(e, ctx) {
    await likeABook(ctx.params.id);
    
    ctx.page.redirect(`/details/${ctx.params.id}`);
}