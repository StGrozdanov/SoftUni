import { render } from '../../node_modules/lit-html/lit-html.js';
import { renderNavigation } from "../views/navigationView.js";

const mainRootElement = document.getElementById('site-content');

export function setUp(ctx, next) {
    renderNavigation(ctx);
    ctx.render = (content) => render(content, mainRootElement);

    next();
}