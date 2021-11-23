import { homePage } from './views/home-view.js';
import page from '../node_modules/page/page.mjs';
import { loginPage } from './views/login-view.js';
import { registerPage } from './views/register-view.js';
import { setUp } from './middlewares/setUpMidware.js';
import { browseTeamsPage } from './views/browse-teams-view.js';
import { teamDetails } from './views/team-details-view.js';
import { createPage } from './views/create-view.js';
import { myTeamsPage } from './views/my-teams-view.js';
import { editTeamPage } from './views/edit-team-view.js';


page('/', setUp, homePage);
page('/login', setUp, loginPage);
page('/register', setUp, registerPage);
page('/browse-teams', setUp, browseTeamsPage);
page('/team-details/:id', setUp, teamDetails);
page('/create-team', setUp, createPage);
page('/my-teams', setUp, myTeamsPage);
page('/edit-team/:id', setUp, editTeamPage);

page.start();