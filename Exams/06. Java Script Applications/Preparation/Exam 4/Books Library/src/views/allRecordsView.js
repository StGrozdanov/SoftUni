import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js';

const noRecordsTemplate = () => html`
<p class="no-books">No books in database!</p>
`;

const allRecordsTemplate = (books, ctx) => html`
<section id="dashboard-page" class="dashboard">
    <h1>Dashboard</h1>
    <ul @click=${(e) => redirectHandler(e, ctx)} class="other-books-list">
        ${books ? books : ''}
    </ul>
    ${books.length === 0 ? noRecordsTemplate() : ''}
</section>
`;

const singleRecordTemplate = (data) => html`
            <li class="otherBooks" id=${data._id}>
                <h3>${data.title}</h3>
                <p>Type: ${data.type}</p>
                <p class="img"><img src=${data.imageUrl}></p>
                <a class="button" href="/details/${data._id}">Details</a>
            </li>
`;

export async function viewAllPage(ctx) {
    const data = await getAll();
    const singleRecords = data.map(singleRecordTemplate);
    const allRecords = allRecordsTemplate(singleRecords, ctx);

    ctx.render(allRecords);
}

function redirectHandler(e, ctx) {
    let target;
    if (e.target.tagName === 'IMG') {
        target = e.target.parentNode.parentNode.id;
    } else {
        target = e.target.id;
    }
    ctx.page.redirect(`/details/${target}`);
}