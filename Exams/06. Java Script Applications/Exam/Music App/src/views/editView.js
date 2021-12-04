import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js';

const editTemplate = (data, ctx) => html`
<section class="editPage">
    <form id="edit-form">
        <fieldset>
            <legend>Edit Album</legend>

            <div class="container">
                <label for="name" class="vhide">Album name</label>
                <input id="name" name="name" class="name" type="text" value=${data.name}>

                <label for="imgUrl" class="vhide">Image Url</label>
                <input id="imgUrl" name="imgUrl" class="imgUrl" type="text" value=${data.imgUrl}>

                <label for="price" class="vhide">Price</label>
                <input id="price" name="price" class="price" type="text" value=${data.price}>

                <label for="releaseDate" class="vhide">Release date</label>
                <input id="releaseDate" name="releaseDate" class="releaseDate" type="text" value=${data.releaseDate}>

                <label for="artist" class="vhide">Artist</label>
                <input id="artist" name="artist" class="artist" type="text" value=${data.artist}>

                <label for="genre" class="vhide">Genre</label>
                <input id="genre" name="genre" class="genre" type="text" value=${data.genre}>

                <label for="description" class="vhide">Description</label>
                <textarea name="description" class="description" rows="10"
                    cols="10">${data.description}</textarea>

                <button @click=${(e) => editHandler(e, ctx)} class="edit-album" type="submit">Edit Album</button>
            </div>
        </fieldset>
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
    const name = form.get('name');
    const imgUrl = form.get('imgUrl');
    const price = form.get('price');
    const releaseDate = form.get('releaseDate');
    const artist = form.get('artist');
    const genre = form.get('genre');
    const description = form.get('description');

    if (name == '' || imgUrl == '' || price == '' || releaseDate == '' || artist == '' || genre == '' || description == '') {
        return alert('All fields are required!');
    } 
    const editData = {
        name: name,
        imgUrl: imgUrl,
        price: price,
        releaseDate: releaseDate,
        artist: artist,
        genre: genre,
        description: description,
    }
    await update(context.params.id, editData);
    context.page.redirect(`/details/${context.params.id}`);
}