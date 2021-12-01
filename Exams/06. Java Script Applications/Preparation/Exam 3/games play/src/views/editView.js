import { html } from '../../node_modules/lit-html/lit-html.js';
import { getSingle, update } from '../io/requests.js';

const editTemplate = (data, ctx) => html`
<section id="edit-page" class="auth">
    <form id="edit">
        <div class="container">

            <h1>Edit Game</h1>
            <label for="leg-title">Legendary title:</label>
            <input type="text" id="title" name="title" value=${data.title}>

            <label for="category">Category:</label>
            <input type="text" id="category" name="category" value=${data.category}>

            <label for="levels">MaxLevel:</label>
            <input type="number" id="maxLevel" name="maxLevel" min="1" value=${data.maxLevel}>

            <label for="game-img">Image:</label>
            <input type="text" id="imageUrl" name="imageUrl" value=${data.imageUrl}>

            <label for="summary">Summary:</label>
            <textarea name="summary" id="summary">${data.summary}</textarea>
            <input @click=${(e) => editHandler(e, ctx)} class="btn submit" type="submit" value="Edit Game">

        </div>
    </form>
</section>
`;

export async function editPage(context) {
    const data = await getSingle(context.params.id);
    context.render(editTemplate(data, context));
}

async function editHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('edit'));
    const title = form.get('title');
    const category = form.get('category');
    const maxLevel = form.get('maxLevel');
    const imageUrl = form.get('imageUrl');
    const summary = form.get('summary');

    if (category == '' || imageUrl == '' || title == '' || maxLevel == '' || summary == '') {
        return alert('All fields are required!');
    } 
    const editData = {
        title: title,
        category: category,
        maxLevel: maxLevel,
        imageUrl: imageUrl,
        summary: summary
    }
    await update(context.params.id, editData);
    context.page.redirect(`/details/${context.params.id}`);
}