import { html } from '../../node_modules/lit-html/lit-html.js';
import { editTeam, getASingleTeam } from '../io/requests.js';
import { errorDivTemplate } from '../templates/errorTemplate.js';

const editTeamTemplate = (data, ctx, error) => html`
<section id="edit">
    <article class="narrow">
        <header class="pad-med">
            <h1>Edit Team</h1>
        </header>
        <form id="edit-form" class="main-form pad-large">
            ${error}
            <label>Team name: <input type="text" name="name" .value="${data.name}"></label>
            <label>Logo URL: <input type="text" name="logoUrl" .value="${data.logoUrl}"></label>
            <label>Description: <textarea name="description" .value="${data.description}"></textarea></label>
            <input @click=${(e)=> editHandler(e, ctx, data)} class="action cta" type="submit" value="Save Changes">
        </form>
    </article>
</section>
`;

export async function editTeamPage(ctx) {
    const targetTeam = await getASingleTeam(ctx.params.id);

    ctx.render(editTeamTemplate(targetTeam, ctx))
}

async function editHandler(e, ctx, targetTeam) {
    e.preventDefault();

    const form = new FormData(document.getElementById('edit-form'));
    const teamName = form.get('name');
    const logo = form.get('logoUrl');
    const description = form.get('description');

    if (teamName == '' || logo == '' || description == '') {
        ctx.render(editTeamTemplate(targetTeam, ctx, errorDivTemplate('All fields are required!')));
        return;
    } else if (teamName.toString().length < 4) {
        ctx.render(editTeamTemplate(targetTeam, ctx, errorDivTemplate('Team name should be at least 4 characters long!')));
        return;
    } else if (description.toString().length < 10) {
        ctx.render(editTeamTemplate(targetTeam, ctx, errorDivTemplate('Description should be at least 10 characters long!')));
        return;
    }
    try {
        const team = {
            name: teamName,
            logoUrl: logo,
            description: description
        }

        await editTeam(ctx.params.id, team);

        ctx.page.redirect(`/team-details/${ctx.params.id}`);

    } catch (e) {
        ctx.render(editTeamTemplate(targetTeam, ctx, errorDivTemplate(e.message)));
        return;
    }
}