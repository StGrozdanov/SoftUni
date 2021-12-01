import { html } from '../../node_modules/lit-html/lit-html.js';
import { create } from '../io/requests.js';

const createTemplate = (ctx) => html`
<section id="create-page" class="auth">
    <form id="create">
        <div class="container">

            <h1>Create Game</h1>
            <label for="leg-title">Legendary title:</label>
            <input type="text" id="title" name="title" placeholder="Enter game title...">

            <label for="category">Category:</label>
            <input type="text" id="category" name="category" placeholder="Enter game category...">

            <label for="levels">MaxLevel:</label>
            <input type="number" id="maxLevel" name="maxLevel" min="1" placeholder="1">

            <label for="game-img">Image:</label>
            <input type="text" id="imageUrl" name="imageUrl" placeholder="Upload a photo...">

            <label for="summary">Summary:</label>
            <textarea name="summary" id="summary"></textarea>
            <input @click=${(e) => createHandler(e, ctx)} class="btn submit" type="submit" value="Create Game">
        </div>
    </form>
</section>
`;

export function createPage(context) {
    context.render(createTemplate(context));
}

async function createHandler(e, context) {
    e.preventDefault();

    const form = new FormData(document.getElementById('create'));
    const title = form.get('title');
    const category = form.get('category');
    const maxLevel = form.get('maxLevel');
    const imageUrl = form.get('imageUrl');
    const summary = form.get('summary');

    if (category == '' || imageUrl == '' || title == '' || maxLevel == '' || summary == '') {
        return alert('All fields are required!');
    } 
    const createData = {
        title: title,
        category: category,
        maxLevel: maxLevel,
        imageUrl: imageUrl,
        summary: summary
    }
    await create(createData);
    context.page.redirect('/');
}