import { show } from "./topicPage.js";

const topicsContainer = document.querySelector('#topics');

export async function getData() {
    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts');
    const data = await response.json();
    
    topicsContainer.innerHTML = '';

    Object.values(data).forEach(topic => {
        const newElementHTML = `<div id="${topic._id}" class="topic-name-wrapper">
        <div class="topic-name">
        <a href="#" class="normal link">
        <h2>${topic.title}</h2>
        </a>
        <div class="columns">
        <div>
        <p>Date:
        <time>${topic.date}</time>
        </p>
        <div class="nick-name">
        <p>Username: <span>${topic.username}</span></p>
        </div>
        </div>
        </div>
        </div>
        </div>`;
        topicsContainer.innerHTML += newElementHTML;

        const topicLinks = document.querySelectorAll('.link');
        for (const link of topicLinks) {
            link.addEventListener('click', async (e) => {
                e.preventDefault();
                const targetId = e.target.parentNode.parentNode.parentNode.id;
                const data = await openPost(targetId);
                show(data);
            });
        }
    });
}

export async function postData(data) {
    const response = await fetch(`http://localhost:3030/jsonstore/collections/myboard/posts`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });
    await response.json();
}

export async function openPost(id) {
    const response = await fetch(`http://localhost:3030/jsonstore/collections/myboard/posts/${id}`);
    const data = await response.json();
    return data;
}