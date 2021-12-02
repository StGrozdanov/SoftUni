import { html } from '../../node_modules/lit-html/lit-html.js';
import { getMyPublications } from '../io/requests.js';

const noRecordsTemplate = () => html`
<p class="no-books">No books in database!</p>
`;

const allRecordsTemplate = (books) => html`
<section id="my-books-page" class="my-books">
    <h1>My Books</h1>
    <ul class="my-books-list">
    ${books ? books : ''}
    </ul>
    ${books.length === 0 ? noRecordsTemplate() : ''}
</section>
`;

const singleRecordTemplate = (data) => html`
        <li class="otherBooks">
            <h3>${data.title}</h3>
            <p>Type: ${data.type}</p>
            <p class="img"><img src=${data.imageUrl}></p>
            <a class="button" href="/details/${data._id}">Details</a>
        </li>
`;

export async function myPublicationsPage(ctx) {
    const data = await getMyPublications(sessionStorage.getItem('id'));
    const singleRecords = data.map(singleRecordTemplate);
    const allRecords = allRecordsTemplate(singleRecords);

    ctx.render(allRecords);
}