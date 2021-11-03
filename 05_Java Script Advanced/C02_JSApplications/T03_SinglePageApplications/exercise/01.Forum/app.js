import * as commentPage from "./data/topicPage.js"
import * as homePage from "./data/homePage.js"
import { getData } from "./data/requests.js";

getData();
const homeButton = document.getElementById('home-button');
homeButton.addEventListener('click', (e) => {
    e.preventDefault();
    homePage.show()
    commentPage.close();
    getData();
});