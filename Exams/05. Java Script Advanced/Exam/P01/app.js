window.addEventListener('load', solve);

function solve() {
    const addButton = document.getElementById('add-btn');
    const genre = document.getElementById('genre');
    const name = document.getElementById('name');
    const author = document.getElementById('author');
    const date = document.getElementById('date');
    const hits = document.getElementsByClassName('all-hits-container');
    const saved = document.getElementsByClassName('saved-container');
    const collectionOfSongs = hits[0];
    const savedSongs = saved[0];
    const likes = document.querySelector('#total-likes > div > p');


    addButton.addEventListener('click', (e) => {
        e.preventDefault();
        if (genre.value.trim() !== '' && name.value.trim() !== '' && author.value.trim() !== '' && date.value.trim() !== '') {
            const div = createElement('div', 'hits-info');
            const img = createElement('img');
            img.src = "./static/img/img.png";
            const genreH = createElement('h2', null, `Genre: ${genre.value}`);
            const nameH = createElement('h2', null, `Name: ${name.value}`);
            const authorH = createElement('h2', null, `Author: ${author.value}`);
            const dateH = createElement('h3', null, `Date: ${date.value}`);
            const saveButton = createElement('button', 'save-btn', 'Save song');
            const likeButton = createElement('button', 'like-btn', 'Like song');
            const deleteButton = createElement('button', 'delete-btn', 'Delete');

            div.appendChild(img);
            div.appendChild(genreH);
            div.appendChild(nameH);
            div.appendChild(authorH);
            div.appendChild(dateH);
            div.appendChild(saveButton);
            div.appendChild(likeButton);
            div.appendChild(deleteButton);

            deleteButton.addEventListener('click', () => { div.remove() });

            likeButton.addEventListener('click', () => {
                let pattern = /\d+/;
                let match = likes.textContent.match(pattern);
                let totalLikes = Number(match[0]);
                totalLikes += 1;
                likes.textContent = `Total Likes: ${totalLikes}`;

                likeButton.disabled = true;
            });

            saveButton.addEventListener('click', () => {
                likeButton.remove();
                saveButton.remove();
                savedSongs.appendChild(div);
            });

            collectionOfSongs.appendChild(div);
            genre.value = '';
            name.value = '';
            author.value = '';
            date.value = '';
        }
    });
    function createElement(type, clazz, content) {
        const result = document.createElement(type);
        if (clazz) {
            result.classList.add(clazz);
        } if (content) {
            result.textContent = content;
        }
        return result;
    }
}