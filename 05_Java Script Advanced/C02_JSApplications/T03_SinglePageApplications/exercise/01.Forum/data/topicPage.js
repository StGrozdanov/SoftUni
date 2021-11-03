import * as homePage from "./homePage.js";
const commentPage = document.getElementById('add-comment');
const commentContent = document.getElementById('comments');

const commentForm = document.getElementById('new-comment-form');
const comment = document.getElementById('comment');
const username = document.getElementById('user');
const postButton = document.getElementById('postFBtn');

postButton.addEventListener('click', (e) => {
    e.preventDefault();
    if (comment.value.trim() !== '' && comment.value.trim() !== '') {
        const date = new Date(Date.now()).toLocaleString();
        post(username.value, date, comment.value);
        commentForm.reset();
    }
});

export function show(data) {
    commentPage.classList.remove('hidden');
    homePage.close();

    const date = data.date;
    const username = data.username;
    const title = data.title;
    const comment = data.comment;

    const innerHtml = `<header class="header custom">
    <p><h2>${title}</h2></p>
    <p><span>${username}</span> posted on <time>${date}</time></p>
    </header>
    <div class="comment-main">
        <div class="userdetails">
            <img src="./static/profile.png" alt="avatar">
        </div>
        <div class="post-content">
            <p>${comment}</p>
        </div>
    </div>`;

    commentContent.innerHTML += innerHtml;
}

export function close() {
    commentPage.classList.add('hidden');
}

function post(username, date, comment) {
    const innerHTML = `<div class="topic-name-wrapper">
        <div class="topic-name">
        <h2>${username} commented on ${date}</h2>
        <div class="columns">
        <div>
        <p>${comment}</p>
        </div>
        </div>
        </div>
        </div>`;
    commentContent.innerHTML += innerHTML;
}