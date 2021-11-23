import { html } from '../../node_modules/lit-html/lit-html.js';
import { approveMember, getASingleTeam, getASingleTeamMembersAndCandidates, newMemberRequest, removeMember } from '../io/requests.js';

const detailsTemplate = (ctx, data, teamMembers, teamCandidates, userIsOwner, userIsGuest, usersIsMemberOfTheTeam, userRequestIsPending) => html`
            <section id="team-home">
                <article class="layout">
                    <img src="${data.logoUrl}" class="team-logo left-col">
                    <div class="tm-preview">
                        <h2>${data.name}</h2>
                        <p>${data.description}</p>
                        <span class="details">${teamMembers.length} Members</span>
                        <div>
                            <a href="/edit-team/${data._id}" class="action" style=${userIsOwner() ? '' : 'display:none;'}>Edit team</a>
                            <a @click=${(e) => joinHandler(e, ctx)} href="#" class="action" style=${userIsGuest() && !usersIsMemberOfTheTeam() && !userRequestIsPending() ? '' : 'display:none;'}>Join team</a>
                            <a @click=${(e) => cancelMembership(e, ctx)} href="#" class="action invert" style=${usersIsMemberOfTheTeam() && !userIsOwner() ? '' : 'display:none;'}>Leave team</a>
                            <p style=${userRequestIsPending() ? '' : 'display:none;'}>Membership pending. <a @click=${(e) => cancelMembership(e, ctx)} href="#">Cancel request</a></p>
                        </div>
                    </div>
                    <div class="pad-large">
                        <h3>Members</h3>
                        <ul class="tm-members">
                            ${teamMembers.map(m => 
                                html`
                            <li>${m.user.username}<a @click=${(e) => removeFromTeamHandler(e, ctx, m.user.username)} href="#" class="tm-control action" style=${userIsOwner() && sessionStorage.getItem('username') !== m.user.username ? '' : 'display:none;'}>Remove from team</a></li>
                            `)}
                        </ul>
                    </div>
                    <div class="pad-large" style=${userIsOwner() ? '' : 'display:none;'}>
                    <h3>Membership Requests</h3>
                        <ul class="tm-members">
                            ${teamCandidates.map(c => html`
                            <li>${c.user.username}
                            <a @click=${(e) => approveMemberHandler(e, ctx, c.user.username)} href="#" class="tm-control action" style=${userIsOwner() ? '' : 'display:none;'}>Approve</a>
                            <a @click=${(e) => removeFromTeamHandler(e, ctx, c.user.username)} href="#" class="tm-control action" style=${userIsOwner() ? '' : 'display:none;'}>Decline</a></li>
                            `)}
                        </ul>
                    </div>
                    </div>
                </article>
            </section>
            `;

export async function teamDetails(ctx) {
    const teamData = await getASingleTeam(ctx.params.id);
    const teamMembersAndCandidatesData = await getASingleTeamMembersAndCandidates(ctx.params.id);

    const teamMembers = teamMembersAndCandidatesData.filter(t => t.status == 'member');
    const teamCandidates = teamMembersAndCandidatesData.filter(t => t.status !== 'member');

    ctx.render(detailsTemplate(ctx, teamData, teamMembers, teamCandidates, userIsOwner, userIsGuest, usersIsMemberOfTheTeam, userRequestIsPending));


    function userIsGuest() {
        return sessionStorage.getItem('authToken') !== null;
    }

    function userIsOwner() {
        return sessionStorage.getItem('id') === teamData._ownerId;
    }

    function usersIsMemberOfTheTeam() {
        return teamMembers.some(m => m._ownerId === sessionStorage.getItem('id'));
    }

    function userRequestIsPending() {
        return teamCandidates.some(m => m._ownerId === sessionStorage.getItem('id'));
    }
}

async function joinHandler(e, ctx) {
    e.preventDefault();
    const newMember = {
            username: sessionStorage.getItem('username'),
            email: sessionStorage.getItem('email'),
            teamId: ctx.params.id,
    }

    await newMemberRequest(newMember);
    
    ctx.page.redirect(`/team-details/${ctx.params.id}`);
}

async function cancelMembership(e, ctx) {
    e.preventDefault();

    const targetUsername = sessionStorage.getItem('username');

    const teamMembers = await getASingleTeamMembersAndCandidates(ctx.params.id);

    const targetMember = teamMembers.find(member => member.user.username === targetUsername);

    await removeMember(targetMember._id);
    
    ctx.page.redirect(`/team-details/${ctx.params.id}`);
}

async function removeFromTeamHandler(e, ctx, username) {
    e.preventDefault();

    const teamMembers = await getASingleTeamMembersAndCandidates(ctx.params.id);

    const targetMember = teamMembers.find(member => member.user.username === username);

    await removeMember(targetMember._id);
    
    ctx.page.redirect(`/team-details/${ctx.params.id}`);
}

async function approveMemberHandler(e, ctx, username) {
    e.preventDefault();

    const teamMembers = await getASingleTeamMembersAndCandidates(ctx.params.id);

    const targetMember = teamMembers.find(member => member.user.username === username);
    targetMember.status = 'member';
    
    await approveMember(targetMember._id, targetMember)
    
    ctx.page.redirect(`/team-details/${ctx.params.id}`);
}