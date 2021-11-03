import { html } from 'lit-html';

export default (data) => html`
    <div class="contact card">
        <div>
            <i class="far fa-user-circle gravatar"></i>
        </div>
        <div class="info">
            <h2>Name: ${data.name}</h2>
            <button @click=${onDetailsClickHandler} class="detailsBtn">Details</button>
            <div class="details" id="${data.id}">
                <p>Phone number: ${data.phoneNumber}</p>
                <p>Email: ${data.email}</p>
            </div>
        </div>
    </div>
`;

function onDetailsClickHandler(e) {
    const button = e.target;
    const hiddenDiv = e.target.parentNode.children[2];
    if (button.textContent == 'Details') {
        hiddenDiv.classList.remove('details');
        button.textContent = 'Hide Details';
    } else {
        button.textContent = 'Details';
        hiddenDiv.classList.add('details');
    }
}