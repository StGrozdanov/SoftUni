import { html } from '../../node_modules/lit-html/lit-html.js';
import { create } from '../io/requests.js';

const addMovieTemplate = (ctx) => html`
<section id="add-movie">
    <form class="text-center border border-light p-5" action="#" method="">
        <h1>Add Movie</h1>
        <div class="form-group">
            <label for="title">Movie Title</label>
            <input id="title" type="text" class="form-control" placeholder="Title" name="title" value="">
        </div>
        <div class="form-group">
            <label for="description">Movie Description</label>
            <textarea class="form-control" placeholder="Description" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image url</label>
            <input id="imageUrl" type="text" class="form-control" placeholder="Image Url" name="imageUrl" value="">
        </div>
        <button @click=${(e) => addMovieHandler(e, ctx)} type="submit" class="btn btn-primary">Submit</button>
    </form>
</section>
`;

export async function addMoviePage(ctx) {
    ctx.render(addMovieTemplate(ctx));
}

async function addMovieHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.querySelector('#add-movie form'));
    const title = form.get('title');
    const description = form.get('description');
    const imageUrl = form.get('imageUrl');

    if (title == '' || description == '' || imageUrl == '') {
        return alert('All fields are required!');
    } 
    const newMovie = {
        title: title,
        description: description,
        img: imageUrl
    }

    const response = await create(newMovie);

    context.page.redirect(`/details/${response._id}`);
}