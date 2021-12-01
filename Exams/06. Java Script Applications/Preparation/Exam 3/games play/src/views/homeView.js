import { html } from '../../node_modules/lit-html/lit-html.js';
import { getLastThree } from '../io/requests.js';

const noRecordsTemplate = () => html`
<p class="no-articles">No games yet</p>
`;

const homePageTemplate = (games) => html`
<section id="welcome-world">

    <div class="welcome-message">
        <h2>ALL new games are</h2>
        <h3>Only in GamesPlay</h3>
    </div>
    <img src="./images/four_slider_img01.png" alt="hero">

    <div id="home-page">
        <h1>Latest Games</h1>
        ${games ? games : noRecordsTemplate()}
    </div>
</section>
`;

const gamesTemplate = (data) => html`
        <div class="game">
            <div class="image-wrap">
                <img src=${data.imageUrl}>
            </div>
            <h3>${data.title}</h3>
            <div class="rating">
                <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
            </div>
            <div class="data-buttons">
                <a href="/details/${data._id}" class="btn details-btn">Details</a>
            </div>
        </div>
`;

export async function homePage(ctx) {
    const data = await getLastThree();
    let lastThreeGames = data.slice(0, 3);

    let template;

    if (lastThreeGames.length > 0) {
        const games = lastThreeGames.map(gamesTemplate);
        template = homePageTemplate(games);
    } else {
        template = homePageTemplate();
    }

    ctx.render(template);
}