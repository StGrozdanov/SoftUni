import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const registeredUserViewTemplate = (id) => html`
<a href="/details/${id}" id="details">Details</a>
`

const noRecordsTemplate = () => html`
   <p>No Albums in Catalog!</p>
`;

const allRecordsTemplate = (albums) => html`
<section id="catalogPage">
    <h1>All Albums</h1>

    ${albums.length > 0 ? albums : noRecordsTemplate()}

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

export async function viewAllPage(ctx) {
    const data = await getAll();
    const singleRecords = data.map(singleRecordTemplate);
    const allRecords = allRecordsTemplate(singleRecords);

    ctx.render(allRecords);
}