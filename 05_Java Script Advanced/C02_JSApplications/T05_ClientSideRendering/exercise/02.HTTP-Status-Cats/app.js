import { html, render } from './node_modules/lit-html/lit-html.js'
import { cats } from './catSeeder.js'

const template = (data) => html`
    <ul>${data.map(cat => html`
        <li>
            <img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap">
            <div class="info">
                <button @click=${showStatus} class="showBtn">Show status code</button>
                <div class="status" style="display: none" id="100">
                    <h4>${cat.statusCode}</h4>
                    <p>${cat.statusMessage}</p>
                </div>
            </div>
        </li>
        `
    )}
    </ul>
`;

const rootElement = document.getElementById('allCats');
render(template(cats), rootElement);

function showStatus(e) {
    const hiddenDiv = e.target.parentNode.children[1];
    if (e.target.textContent == 'Show status code') {
        e.target.textContent = 'Hide status code';
        hiddenDiv.style.display = 'block';
    } else {
        e.target.textContent = 'Show status code';
        hiddenDiv.style.display = 'none';
    }
}