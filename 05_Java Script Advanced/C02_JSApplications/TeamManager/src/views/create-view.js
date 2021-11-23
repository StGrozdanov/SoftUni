import { html } from '../../node_modules/lit-html/lit-html.js';
import { errorDivTemplate } from '../templates/errorTemplate.js'
import { createTeam, newMemberRequest, approveMember } from '../io/requests.js';

const createTemplate = (ctx, error) => html`
<section id="create">
    <article class="narrow">
        <header class="pad-med">
            <h1>New Team</h1>
        </header>
        <form id="create-form" class="main-form pad-large">
            ${error}
            <label>Team name: <input type="text" name="name"></label>
            <label>Logo URL: <input type="text" name="logoUrl"></label>
            <label>Description: <textarea name="description"></textarea></label>
            <input @click=${(e)=> createHandler(e, ctx)} class="action cta" type="submit" value="Create Team">
        </form>
    </article>
</section>
`;

export function createPage(ctx) {
    ctx.render(createTemplate(ctx));
}

async function createHandler(e, ctx) {
    e.preventDefault();

    const form = new FormData(document.getElementById('create-form'));
    const name = form.get('name');
    const logo = form.get('logoUrl');
    const description = form.get('description');

    if (name == '' || logo == '' || description == '') {
        ctx.render(createTemplate(ctx, errorDivTemplate('All fields are required!')));
        return;
    } else if (name.toString().length < 4) {
        ctx.render(createTemplate(ctx, errorDivTemplate('Team name should be at least 4 characters long!')));
        return;
    } else if (description.toString().length < 10) {
        ctx.render(createTemplate(ctx, errorDivTemplate('Description should be at least 10 characters long!')));
        return;
    }
    try {
        const team = {
            name: name,
            logoUrl: logo,
            description: description
        }

        const createdTeam = await createTeam(team);
        
        const teamCreator = {
            username: sessionStorage.getItem('username'),
            email: sessionStorage.getItem('email'),
            teamId: createdTeam._id,
        }

        const teamCreatorRequest = await newMemberRequest(teamCreator);

        const acceptedTeamCreator = {
            username: sessionStorage.getItem('username'),
            email: sessionStorage.getItem('email'),
            teamId: createdTeam._id,
            status: 'member',
        }

        await approveMember(teamCreatorRequest._id, acceptedTeamCreator);

        ctx.page.redirect('/browse-teams');
    } catch (e) {
        ctx.render(createTemplate(ctx, errorDivTemplate(e.message)));
        return;
    }
}