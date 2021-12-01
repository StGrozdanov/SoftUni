import { allRecordsPage } from './views/allRecordsView.js';
import page from '../node_modules/page/page.mjs';
import { loginPage } from './views/loginView.js';
import { registerPage } from './views/registerView.js';
import { setUp } from './middlewares/setUpMidware.js';
import { createPage } from './views/createView.js';
import { detailsPage } from './views/detailsView.js';
import { editPage } from './views/editView.js';
import { homePage } from './views/homeView.js';

page('/', setUp, homePage);
page('/all', setUp, allRecordsPage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/add', setUp, createPage);
page('/details/:id', setUp, detailsPage);
page('/edit/:id', setUp, editPage);

page.start();