import { html } from '../../node_modules/lit-html/lit-html.js';
import { getAllTeamsIncludingTheMember, getASingleTeamMembersAndCandidates } from '../io/requests.js';

const notAMemberOfAnyTeamTemplate = () => html`
<article class="layout narrow">
    <div class="pad-med">
        <p>You are not a member of any team yet.</p>
        <p><a href="/browse-teams">Browse all teams</a> to join one, or use the button bellow to cerate your own
            team.</p>
    </div>
    <div class=""><a href="/create-team" class="action cta">Create Team</a></div>
</article>
`;

const memberOfTeamTemplate = (data) => html`
<article id="#${data.team._id}" class="layout">
    <img src="${data.team.logoUrl}" class="team-logo left-col">
    <div class="tm-preview">
        <h2>${data.team.name}</h2>
        <p>${data.team.description}</p>
        <span class="details"></span>
        <div><a href="/team-details/${data.team._id}" class="action">See details</a></div>
    </div>
</article>
`;

const myTeamsTemplate = (teams) => html`
<section id="my-teams">

    <article class="pad-med">
        <h1>My Teams</h1>
    </article>

    ${teams}

</section>
`;

export async function myTeamsPage(ctx) {
    const teamsIncludingTheMember = await getAllTeamsIncludingTheMember(sessionStorage.getItem('id'));

    let includedTeamsTemplate;

    if (teamsIncludingTheMember.length === 0) {
        includedTeamsTemplate = notAMemberOfAnyTeamTemplate();
    } else {
        includedTeamsTemplate = teamsIncludingTheMember.map(memberOfTeamTemplate);
    }

    ctx.render(myTeamsTemplate(includedTeamsTemplate));

    includedTeamsTemplate.forEach(team => {
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