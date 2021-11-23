import { html, render } from '../node_modules/lit-html/lit-html.js';
import { getLikes, getMovie } from './requests.js';

const movieDetailsTemplate = (data, likes) => html`
                <section id="${data.id}">
                    <div class="container" id="details-container">
                        <div class="row bg-light text-dark">
                            <h1>Movie title: ${data.title}</h1>
                
                            <div class="col-md-8">
                                <img class="img-thumbnail" src="${data.img}" alt="Movie">
                            </div>
                            <div class="col-md-4 text-center">
                                <h3 class="my-3 ">Movie Description</h3>
                                <p>${data.description}</p>
                                <a class="btn btn-danger ${sessionStorage.getItem('id') !== data._ownerId ? "hidden" : ""}" href="#">Delete</a>
                                <a class="btn btn-warning ${sessionStorage.getItem('id') !== data._ownerId ? "hidden" : ""}" href="#">Edit</a>
                                <a class="btn btn-primary ${sessionStorage.getItem('id') === data._ownerId ? "hidden" : ""}" href="#">Like</a>
                                <span class="enrolled-span">Liked ${likes}</span>
                            </div>
                        </div>
                    </div>
                </section>
`;

export async function movieDetails(context) {
    const movie = await getMovie(context.params.id);
    const likes = await getLikes();
    const movieView = movieDetailsTemplate(movie, likes);
    render(movieView, document.getElementById('root'));
}

