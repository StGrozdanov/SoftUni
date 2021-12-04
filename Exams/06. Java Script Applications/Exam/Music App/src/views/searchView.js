import { html } from '../../node_modules/lit-html/lit-html.js';
import { search } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const registeredUserViewTemplate = (id) => html`
<a href="/details/${id}" id="details">Details</a>
`

const noRecordsTemplate = () => html`
<p class="no-result">No result.</p>
`;

const resultTemplate = (albums) => html`
    <div class="search-result">
        ${albums.length > 0 ? albums : noRecordsTemplate()}
    </div>
`;

const searchTemplate = (albums, ctx) => html`
<section id="searchPage">
    <h1>Search by Name</h1>

    <div class="search">
        <input id="search-input" type="text" name="search" placeholder="Enter desired albums's name">
        <button @click=${()=> searchHandler(ctx)} class="button-list">Search</button>
    </div>

    <h2>Results:</h2>

    ${albums ? resultTemplate(albums) : ''}

</section>
`;

const singleRecordTemplate = (data) => html`
    <div class="card-box">
        <img src=${data.imgUrl}>
        <div>
            <div class="text-center">
                <p class="name">Name: ${data.name}</p>
                <p class="artist">Artist: ${data.artist}</p>
                <p class="genre">Genre: ${data.genre}</p>
                <p class="price">Price: $${data.price}</p>
                <p class="date">Release Date: ${data.releaseDate}</p>
            </div>
            <div class="btn-group">
                ${userIsLoggedIn() ? registeredUserViewTemplate(data._id) : ''}
            </div>
        </div>
    </div>
`;

export async function searchPage(ctx) {
    const search = searchTemplate(null, ctx);

    ctx.render(search);
}

async function searchHandler(ctx) {
    const query = document.getElementById('search-input').value;

    if (query.trim() !== '') {
        const matchingAlbums = await search(query);

        const albums = matchingAlbums.map(singleRecordTemplate);
        ctx.render(searchTemplate(albums, ctx));
    } else {
        return alert('You must include searching parameters!');
    }
}