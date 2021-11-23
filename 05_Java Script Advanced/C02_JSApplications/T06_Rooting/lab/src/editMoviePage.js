import { html, render } from '../node_modules/lit-html/lit-html.js';

const editMovieTemplate = () => html`
        <section id="edit-movie">
            <form class="text-center border border-light p-5" action="#" method="">
                <h1>Edit Movie</h1>
                <div class="form-group">
                    <label for="title">Movie Title</label>
                    <input type="text" class="form-control" placeholder="Movie Title" value="" name="title" id="edit-title">
                </div>
                <div class="form-group">
                    <label for="description">Movie Description</label>
                    <textarea class="form-control" placeholder="Movie Description..." name="description"
                        id="edit-description"></textarea>
                </div>
                <div class="form-group">
                    <label for="imageUrl">Image url</label>
                    <input type="text" class="form-control" placeholder="Image Url" value="" name="imageUrl" id="edit-img">
                </div>
                <button type="submit" class="btn btn-primary" id="edit-movie-btn">Submit</button>
            </form>
        </section>
`;

export function editMovie() {
    render(editMovieTemplate(), document.getElementById('root'));
}