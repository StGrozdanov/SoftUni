import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAll } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const homePageTemplate = (movies) => html`
<section id="home-page">
    <div class="jumbotron jumbotron-fluid text-light" style="background-color: #343a40;">
        <img src="https://slicksmovieblog.files.wordpress.com/2014/08/cropped-movie-banner-e1408372575210.jpg"
            class="img-fluid" alt="Responsive image" style="width: 150%; height: 200px">
        <h1 class="display-4">Movies</h1>
        <p class="lead">Unlimited movies, TV shows, and more. Watch anywhere. Cancel anytime.</p>
    </div>
</section>
<h1 class="text-center">Movies</h1>

<section id="add-movie-button">
    <a href="/add-movie" class="btn btn-warning" style="${userIsLoggedIn() ? '' : 'display:none;'}">Add Movie</a>
</section>
<section id="movie">
    <div class=" mt-3 ">
        <div class="row d-flex d-wrap">
            <div class="card-deck d-flex justify-content-center"></div>

            ${movies}

        </div>
    </div>
    </div>
</section>

<footer class="page-footer font-small">
    <div class="footer-copyright text-center py-3">© 2021
        <a href="#" class="text-dark">JS
            Applications Demo Project</a>
    </div>
</footer>
`;

const movieTemplate = (data) => html`
<div class="card mb-4">
    <img class="card-img-top" src="${data.img}"
        alt="Card image cap" width="400">
    <div class="card-body">
        <h4 class="card-title">${data.title}</h4>
    </div>
    <div class="card-footer">
        <a href="/details/${data._id}" type="button" class="btn btn-info">Details</a>
    </div>

</div>
`;

export async function homePage(ctx) {
    const data = await getAll();
    const movies = data.map(movieTemplate);
    const homePageTem = homePageTemplate(movies);

    ctx.render(homePageTem);
}