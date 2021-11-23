import { html, render } from '../node_modules/lit-html/lit-html.js';
import { postData } from './requests.js';

const addMovieTemplate = (context) => html`
<section id="add-movie-page">
    <form class="text-center border border-light p-5" id="add-movie-form" action="#" method="">
        <h1>Add Movie</h1>
        <div class="form-group">
            <label for="title">Movie Title</label>
            <input id="add-movie-title" type="text" class="form-control" placeholder="Title" name="title" value="">
        </div>
        <div class="form-group">
            <label for="description">Movie Description</label>
            <textarea id="add-movie-description" class="form-control" placeholder="Description"
                name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image url</label>
            <input id="add-movie-img" type="text" class="form-control" placeholder="Image Url" name="imageUrl" value="">
        </div>
        <button type="submit" class="btn btn-primary" id="submit-movie" @click=${(e) => submitHandler(e,
    context)}>Submit</button>
    </form>
</section>
`;

export function addMovie(context) {
    render(addMovieTemplate(context), document.getElementById('root'));
}

async function submitHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('add-movie-form'));
    const title = form.get('title');
    const description = form.get('description');
    const img = form.get('imageUrl');

    if (title != '' && description != '' && img != '') {
        const data = { title: title, description: description, img: img };
        await postData(data);
        context.page.redirect('/');
    } else {
        return alert('All fields are mandatory.');
    }
}