import { homePage } from './views/homePageView.js';
import page from '../node_modules/page/page.mjs';
import { loginPage } from './views/loginView.js';
import { registerPage } from './views/registerView.js';
import { setUp } from './middlewares/setUpMidware.js';
import { allMemesPage } from './views/allMemesView.js';
import { createPage } from './views/createMemeView.js';
import { detailsPage } from './views/memeDetailsView.js';
import { editPage } from './views/memeEditView.js';
import { myPublicationsPage } from './views/profileView.js';
import { showNotification } from './views/common/notification.js';

page('/', setUp, homePage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/all-memes', setUp, allMemesPage);
page('/create-meme', setUp, createPage);
page('/details/:id', setUp, detailsPage);
page('/edit/:id', setUp, editPage);
page('/my-profile/:id', setUp, myPublicationsPage);

page.start();