import { getData, postData } from "./requests.js";

const homePage = document.getElementById('main-page');

const newTopicForm = document.querySelector('#create-topic form');
const postButton = document.getElementById('postBtn');
const cancelButton = document.getElementById('cancelBtn');
const title = document.getElementById('topicName');
const username = document.getElementById('username');
const post = document.getElementById('postText');

cancelButton.addEventListener('click', (e) => {
    e.preventDefault();
    newTopicForm.reset();
});

postButton.addEventListener('click', (e) => {
    e.preventDefault();
    if (title.value.trim() !== '' && username.value.trim() !== '' && post.value.trim() !== '') {
        const content = {
            title: title.value,
            username: username.value,
            comment: post.value,
            date: new Date(Date.now()).toLocaleString()
        }
        postData(content);
        newTopicForm.reset();
        getData();
    }
});

export function show() {
    homePage.classList.remove('hidden');
}

export function close() {
    homePage.classList.add('hidden');
}
