import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js';

const noMemesTemplate = () => html`
<p class="no-memes">No memes in database.</p>
`;

const allMemesPageTemplate = (memes) => html`
    <section id="meme-feed">
        <h1>All Memes</h1>
        <div id="memes">
            ${memes ? memes : noMemesTemplate()}
        </div>
    </section>
`;

const memeTemplate = (data) => html`
            <div class="meme">
                <div class="card">
                    <div class="info">
                        <p class="meme-title">${data.title}</p>
                        <img class="meme-image" alt="meme-img" src=${data.imageUrl}>
                    </div>
                    <div id="data-buttons">
                        <a class="button" href="/details/${data._id}">Details</a>
                    </div>
                </div>
            </div>
`;

export async function allMemesPage(ctx) {
    const data = await getAll();
    const memes = data.map(memeTemplate);
    ctx.render(allMemesPageTemplate(memes));
}