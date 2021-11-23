import page from '../node_modules/page/page.mjs';
import { homePage } from './views/homeView.js';
import { loginPage } from './views/loginView.js';
import { registerPage } from './views/registerView.js';
import { setUp } from './middlewares/setUpMidware.js';
import { detailsPage } from './views/detailsView.js';
import { addMoviePage } from './views/addMovieView.js';
import { editMoviePage } from './views/editMovieView.js';



page('/', setUp, homePage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/details/:id', setUp, detailsPage);
page('/add-movie', setUp, addMoviePage);
page('/edit-movie/:id', setUp, editMoviePage);

page.start();