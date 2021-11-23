import { render } from '../../node_modules/lit-html/lit-html.js';
import { renderNavigation } from "../views/navigation-view.js";

const mainRootElement = document.getElementById('container');

export function setUp(ctx, next) {
    renderNavigation(ctx);
    ctx.render = (content) => render(content, mainRootElement);

    next();
}