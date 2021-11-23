import { html } from '../../node_modules/lit-html/lit-html.js';
import { getASingleTeamMembersAndCandidates, getAllTeams } from '../io/requests.js';
import { userIsLoggedIn } from './navigation-view.js';

const createTeamBtnTemplate = () => html`
<article class="layout narrow">
    <div class="pad-small"><a href="/create-team" class="action cta">Create Team</a></div>
</article>`;

const browseTeamsTemplate = (teams) => html`
<section id="browse">

    <article class="pad-med">
        <h1>Team Browser</h1>
    </article>

    ${userIsLoggedIn() ? createTeamBtnTemplate() : ''}

    ${teams}

</section>
`;


const teamTemplate = (data) => html`
<article id="#${data._id}" class="layout">
    <img src="${data.logoUrl}" class="team-logo left-col">
    <div class="tm-preview">
        <h2>${data.name}</h2>
        <p>${data.description}</p>
        <span class="details"></span>
        <div><a href="/team-details/${data._id}" class="action">See details</a></div>
    </div>
</article>
`;

export async function browseTeamsPage(ctx) {
    const teamsData = await getAllTeams();
    ctx.params.teamMembers = [];

    const teamTemplates = teamsData.map(teamTemplate);
    ctx.render(browseTeamsTemplate(teamTemplates));

    teamTemplates.forEach(team => {
        find();
        async function find() {
            const teamId = team.values[0];
            const teamMembersAndCandidates = await getASingleTeamMembersAndCandidates(teamId);

            const teamMembers = teamMembersAndCandidates.filter(t => t.status == 'member');

            let encodedTeamId = '#\\#' + teamMembers[0].teamId;
            const membersElement = document.querySelector(`${encodedTeamId} div span`);

            membersElement.textContent = `${teamMembers.length} Members`;
        }
    });
}