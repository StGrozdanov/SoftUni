import { html } from '../../node_modules/lit-html/lit-html.js';
import { getLikes, getSingle, getUserLikes, likeMovie, remove, unlikeMovie } from '../io/requests.js';
import { userIsLoggedIn } from './navigationView.js';

const userButtonsTemplate = (id, ctx) => html`
<a @click=${(e) => likeHandler(e, id, ctx)} class="btn btn-primary" href="#">Like</a>
`;

const unlikeMovieTemplate = (id, ctx) => html`
<a @click=${(e) => unlikeHandler(e, id, ctx)} class="btn btn-danger" href="#">Unlike</a>
`;

const ownerButtonsTemplate = (id, ctx) => html`
                <a @click=${(e) => deleteHandler(e, id, ctx)} class="btn btn-danger" href="#">Delete</a>
                <a class="btn btn-warning" href="/edit-movie/${id}">Edit</a>
`;

const detailsTemplate = (data, ctx, totalLikes, thisMovieIsAlreadyLiked) => html`
<section id="movie-example">
    <div class="container">
        <div class="row bg-light text-dark">
            <h1>Movie title: ${data.title}</h1>

            <div class="col-md-8">
                <img class="img-thumbnail" src="${data.img}" alt="Movie">
            </div>
            <div class="col-md-4 text-center">
                <h3 class="my-3 ">Movie Description</h3>
                <p>${data.description}</p>
                
                ${thisMovieIsAlreadyLiked ? unlikeMovieTemplate(data._id, ctx) : ''}

                ${userIsLoggedIn() && !userIsOwner(data._ownerId) && !thisMovieIsAlreadyLiked 
                    ? userButtonsTemplate(data._id, ctx) 
                    : ''}

                ${userIsOwner(data._ownerId) ? ownerButtonsTemplate(data._id, ctx) : ''}
                
                <span class="enrolled-span">Liked ${totalLikes}</span>
            </div>
        </div>
    </div>
</section>
`;

export async function detailsPage(ctx) {
    const data = await getSingle(ctx.params.id);
    const totalLikes = await getLikes(ctx.params.id);
    const thisMovieIsAlreadyLiked = await getUserLikes(ctx.params.id, sessionStorage.getItem('id'));

    ctx.render(detailsTemplate(data, ctx, totalLikes, thisMovieIsAlreadyLiked.liked));
}

function userIsOwner(ownerId) {
    return sessionStorage.getItem('id') === ownerId;
}

async function deleteHandler(e, id, ctx) {
    e.preventDefault();

    if (confirm('Are you sure you want to delete this movie?')) {
        await remove(id);
        ctx.page.redirect('/');
    }
}

async function likeHandler(e, id, ctx) {
    e.preventDefault();

    await likeMovie(id);

    e.target.style.display = 'none';

    ctx.page.redirect(`/details/${ctx.params.id}`);
}

async function unlikeHandler(e, id, ctx) {
    e.preventDefault();

    const likedMovies = await getUserLikes(id, sessionStorage.getItem('id'));

    const targetMovie = likedMovies.data.find(movie => movie.movieId === id);

    await unlikeMovie(targetMovie._id);
    
    e.target.style.display = 'none';

    ctx.page.redirect(`/details/${ctx.params.id}`);
}