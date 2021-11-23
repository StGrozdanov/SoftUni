import { authorize } from '../app.js';
import { html, render } from '../node_modules/lit-html/lit-html.js';
import { getData } from './requests.js';

const loadingTemplate = () => html`
<h1 style="margin-left: 510px;">Loading...</h1>
`;

const homePageTemplate = () => html`
<section id="home-page">
    <div class="jumbotron jumbotron-fluid text-light" style="background-color: #343a40;">
        <img src="https://slicksmovieblog.files.wordpress.com/2014/08/cropped-movie-banner-e1408372575210.jpg"
            class="img-fluid" alt="Responsive image" style="width: 150%; height: 200px">
        <h1 class="display-4">Movies</h1>
        <p class="lead">Unlimited movies, TV shows, and more. Watch anywhere. Cancel anytime.</p>
    </div>

    <h1 class="text-center">Movies</h1>

    <a href="/addMovie" id="add-movie-button" class="btn btn-warning ${sessionStorage.getItem('authToken') == null ? " hidden"
        : "" } "}>Add Movie</a>

    <section id=" movie">
        <div class=" mt-3 ">
            <div class="row d-flex d-wrap">
                <div class="card-deck d-flex justify-content-center" id="movie-covers">

                </div>
            </div>
        </div>
</section>

</section>
`;

const movieTemplate = (data) => html`
<div class="card mb-4" id="${data._ownerId} )}"><img class="card-img-top" src="${data.img}" width="0">
    <div class="card-body">
        <h4 class="card-title">${data.title}</h4>
    </div>
    <div class="card-footer ${sessionStorage.getItem('authToken') == null ? " hidden" : "" }">
        <a href="/movieDetails/${data._id}"><button class="btn btn-info">Details</button></a>
    </div>
</div>
`;

export async function homePage() {
    render(homePageTemplate(), document.getElementById('root'));

    const movieData = await getData();
    const movieDetails = Object.values(movieData);

    const movieContainer = document.getElementById('movie-covers');

    if (movieData) {
        const movieCovers = movieDetails.map(movieTemplate);
        render(movieCovers, movieContainer);
        authorize();
    } else {
        render(loadingTemplate(), movieContainer);
    }
}