import { html } from '../../node_modules/lit-html/lit-html.js';
import { addComment, getAllComments, getSingle, remove } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const ownerTemplate = (id, ctx) => html`
        <div class="buttons">
            <a href="/edit/${id}" class="button">Edit</a>
            <a @click=${()=> deleteHandler(id, ctx)} href="javascript:void[0]" class="button">Delete</a>
        </div>
        </div>
`;

const addCommentTemplate = (ctx) => html`
    <article class="create-comment">
        <label>Add new comment:</label>
        <form id="add-comment-form" class="form">
            <textarea id="comment-text" name="comment" placeholder="Comment......"> </textarea>
            <input @click=${(e)=> addCommentHandler(e, ctx)} class="btn submit" type="submit" value="Add Comment">
        </form>
    </article>
`;

const noCommentsTemplate = () => html`
<p class="no-comment">No comments.</p>
`;

const singleCommentTemplate = (data) => html`
<p>Content: ${data.comment}</p>
`;

const commentsTemplate = (comments) => html`
<ul>
    <li class="comment">
        ${comments}
    </li>
</ul>
`;

const commentsContainerTemplate = (data) => html`
<div class="details-comments">
    <h2>Comments:</h2>
    ${data}
</div>
`;



const detailsTemplate = (data, ctx, commentsData, addCommentForm) => html`
<section id="game-details">
    <h1>Game Details</h1>
    <div class="info-section">

        <div class="game-header">
            <img class="game-img" src=${data.imageUrl} />
            <h1>${data.title}</h1>
            <span class="levels">MaxLevel: ${data.maxLevel}</span>
            <p class="type">${data.category}</p>
        </div>

        <p class="text">
            ${data.summary}
        </p>

        ${sessionStorage.getItem('id') === data._ownerId ? ownerTemplate(data._id, ctx) : ''}

        ${commentsData}
        ${userIsLoggedIn() && sessionStorage.getItem('id') !== data._ownerId ? addCommentForm : ''}

</section>
`;

export async function detailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    const commentsData = await getAllComments(ctx.params.id);

    let commentTemp;

    if (commentsData.length > 0) {
        const allComments = commentsData.map(singleCommentTemplate);
        commentTemp = allComments.map(commentsTemplate);
    } else {
        commentTemp = noCommentsTemplate();
    }

    const commentContainer = commentsContainerTemplate(commentTemp);
    const commentForm = addCommentTemplate(ctx);

    ctx.render(detailsTemplate(data, ctx, commentContainer, commentForm));
}

async function deleteHandler(id, ctx) {
    const confirmed = confirm('Are you sure you want to delete this game?');
    if (confirmed) {
        await remove(id);
        ctx.page.redirect('/');
    }
}

async function addCommentHandler(e, ctx) {
    e.preventDefault();
    const formData = new FormData(document.getElementById('add-comment-form'));

    let comment = formData.get('comment');

    if (comment.trim() !== '') {
        const commentData = {
            gameId: ctx.params.id,
            comment: comment,
        }
        await addComment(commentData);
        document.getElementById('comment-text').value = '';
        ctx.page.redirect(`/details/${ctx.params.id}`);
    }
}