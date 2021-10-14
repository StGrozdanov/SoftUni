const appendTo = document.getElementById('main');

fetch('http://localhost:3030/jsonstore/advanced/articles/details')
    .then(response => response.json())
    .then(result => {
        Object.entries(result).forEach(r => {
            const mainDiv = document.createElement('div');
            mainDiv.classList.add('accordion');
            const headDiv = document.createElement('div');
            headDiv.classList.add('head');
            const titleSpan = document.createElement('span');
            titleSpan.textContent = r[1].title;
            const button = document.createElement('button');
            button.classList.add('button');
            button.id = `${r[1]._id}`;
            button.textContent = 'MORE';
            const infoDiv = document.createElement('div');
            infoDiv.classList.add('extra');
            const description = document.createElement('p');
            description.textContent = `${r[1].content}`

            infoDiv.appendChild(description);

            button.addEventListener('click', () => {
                if (button.textContent == 'MORE') {
                    infoDiv.classList.remove('extra');
                    button.textContent = 'LESS';
                } else {
                    infoDiv.classList.add('extra');
                    button.textContent = 'MORE';
                }
            });

            headDiv.appendChild(titleSpan);
            headDiv.appendChild(button);

            mainDiv.appendChild(headDiv);
            mainDiv.appendChild(infoDiv);
            appendTo.appendChild(mainDiv);
        });

    });
