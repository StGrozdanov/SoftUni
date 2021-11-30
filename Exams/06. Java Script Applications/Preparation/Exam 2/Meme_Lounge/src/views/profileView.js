import { html } from '../../node_modules/lit-html/lit-html.js';
import { getMyPublications } from '../io/requests.js';

const noMemesTemplate = () => html`
<p class="no-memes">No memes in database.</p>
`;

const memeTemplate = (data) => html`
<div class="user-meme">
    <p class="user-meme-title">${data.title}</p>
    <img class="userProfileImage" alt="meme-img" src=${data.imageUrl}>
    <a class="button" href="/details/${data._id}">Details</a>
</div>
`;

const profileTemplate = (memes, memesCount) => html`
        <section id="user-profile-page" class="user-profile">
            <article class="user-info">
                <img id="user-avatar-url" alt="user-profile" src="/images/${sessionStorage.getItem('gender')}.png">
                <div class="user-content">
                    <p>Username: ${sessionStorage.getItem('username')}</p>
                    <p>Email: ${sessionStorage.getItem('email')}</p>
                    <p>My memes count: ${memesCount}</p>
                </div>
            </article>
            <h1 id="user-listings-title">User Memes</h1>
            <div class="user-meme-listings">
                ${memes}
            </div>
        </section>
`;

export async function myPublicationsPage(ctx) {
    const userMemes = await getMyPublications(ctx.params.id);

    if (userMemes.length > 0) {
        const memesTemplate = userMemes.map(memeTemplate);
        ctx.render(profileTemplate(memesTemplate, userMemes.length));
    } else {
        ctx.render(profileTemplate(noMemesTemplate(), 0));
    }
}