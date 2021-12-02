import { viewAllPage } from './views/allRecordsView.js';
import page from '../node_modules/page/page.mjs';
import { loginPage } from './views/loginView.js';
import { registerPage } from './views/registerView.js';
import { setUp } from './middlewares/setUpMidware.js';
import { createPage } from './views/createView.js';
import { detailsPage } from './views/detailsView.js';
import { editPage } from './views/editView.js';
import { myPublicationsPage } from './views/myRecordsView.js';

page('/', setUp, viewAllPage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/add', setUp, createPage);
page('/details/:id', setUp, detailsPage);
page('/edit/:id', setUp, editPage);
page('/my-books/:id', setUp, myPublicationsPage);

page.start();