import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js';

const editMovieTemplate = (data, ctx) => html`
<section id="edit-movie">
    <form class="text-center border border-light p-5" action="#" method="">
        <h1>Edit Movie</h1>
        <div class="form-group">
            <label for="title">Movie Title</label>
            <input id="title" type="text" class="form-control" placeholder="Movie Title" value="${data.title}"
                name="title">
        </div>
        <div class="form-group">
            <label for="description">Movie Description</label>
            <textarea class="form-control" placeholder="Movie Description..."
                name="description">${data.description}</textarea>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image url</label>
            <input id="imageUrl" type="text" class="form-control" placeholder="Image Url" value="${data.img}"
                name="imageUrl">
        </div>
        <button @click=${(e)=> editHandler(e, ctx)} type="submit" class="btn btn-primary">Submit</button>
    </form>
</section>
`;

export async function editMoviePage(ctx) {
    const data = await getSingle(ctx.params.id);
    const editTemp = editMovieTemplate(data, ctx);
    ctx.render(editTemp);
}

async function editHandler(e, ctx) {
    e.preventDefault();

    const formData = new FormData(document.querySelector('#edit-movie form'));
    const title = formData.get('title');
    const description = formData.get('description');
    const image = formData.get('imageUrl');

    const editedMovie = { title: title, description: description, img: image };

    await update(ctx.params.id, editedMovie);

    ctx.page.redirect(`/details/${ctx.params.id}`);
}