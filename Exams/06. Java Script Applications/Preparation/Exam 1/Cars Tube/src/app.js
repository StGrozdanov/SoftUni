import { homePage } from './views/homeView.js';
import page from '../node_modules/page/page.mjs';
import { loginPage } from './views/loginView.js';
import { registerPage } from './views/registerView.js';
import { setUp } from './middlewares/setUpMidware.js';
import { allListingsPage } from './views/allListingsView.js';
import { createPage } from './views/createListingView.js';
import { carDetailsPage } from './views/detailsView.js';
import { editPage } from './views/editCarView.js';
import { myListingsPage } from './views/myListingsView.js';
import { searchPage } from './views/searchView.js';

page('/', setUp, homePage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/all-listings', setUp, allListingsPage);
page('/create', setUp, createPage);
page('/car-details/:id', setUp, carDetailsPage);
page('/edit-car/:id', setUp, editPage);
page('/my-listings/:id', setUp, myListingsPage);
page('/search', setUp, searchPage);

page.start();