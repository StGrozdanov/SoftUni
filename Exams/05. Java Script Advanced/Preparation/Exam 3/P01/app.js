function solve() {
    const taskField = document.querySelector('#task');
    const descriptionField = document.querySelector('#description');
    const dateField = document.querySelector('#date');
    const addButton = document.querySelector('#add');
    const openTask = document.querySelector('section:nth-child(2) div:nth-child(2)');
    const inProgress = document.querySelector('#in-progress');
    const complete = document.querySelector('body > main > div > section:nth-child(4) > div:nth-child(2)');

    addButton.addEventListener('click', (e) => {
        e.preventDefault();

        if (taskField.value.trim() == '' || descriptionField.value.trim() == '' || dateField.value.trim() == '') {
            return;
        }

        const article = createElement('article', null, null);
        const heading = createElement('h3', null, taskField.value);
        const descrParagraph = createElement('p', null, 'Description: ' + descriptionField.value);
        const dateParagraph = createElement('p', null, 'Due Date: ' + dateField.value);
        const div = createElement('div', 'flex', null);
        const deleteButton = createElement('button', 'red', 'Delete');
        const startButton = createElement('button', 'green', 'Start');

        deleteButton.addEventListener('click', () => {
            article.remove();
        });

        startButton.addEventListener('click', () => {
            const progressArticle = createElement('article', null, null);
            const progressHeading = createElement('h3', null, heading.textContent);
            const progressDescr = createElement('p', null, descrParagraph.textContent);
            const progressDate = createElement('p', null, dateParagraph.textContent);
            const progressDiv = createElement('div', 'flex', null);
            const deleteButton = createElement('button', 'red', 'Delete');
            const finishButton = createElement('button', 'orange', 'Finish');

            deleteButton.addEventListener('click', () => { progressArticle.remove() });
            finishButton.addEventListener('click', () => {
                const completeArticle = createElement('article', null, null);
                const completeHeading = createElement('h3', null, progressHeading.textContent);
                const completeDescr = createElement('p', null, 'Description: ' + progressDescr.textContent);
                const completeDate = createElement('p', null, 'Due Date: ' + progressDate.textContent);

                completeArticle.appendChild(completeHeading);
                completeArticle.appendChild(completeDescr);
                completeArticle.appendChild(completeDate);
                complete.appendChild(completeArticle);

                progressArticle.remove();
            });


            progressDiv.appendChild(deleteButton);
            progressDiv.appendChild(finishButton);

            progressArticle.appendChild(progressHeading);
            progressArticle.appendChild(progressDescr);
            progressArticle.appendChild(progressDate);
            progressArticle.appendChild(progressDiv);

            inProgress.appendChild(progressArticle);
            article.remove();
        });

        div.appendChild(startButton);
        div.appendChild(deleteButton);

        article.appendChild(heading);
        article.appendChild(descrParagraph);
        article.appendChild(dateParagraph);
        article.appendChild(div);
        openTask.appendChild(article);

        taskField.value = '';
        descriptionField.value = '';
        dateField.value = '';
    });

    function createElement(type, clazz, content) {
        const result = document.createElement(type);
        if (clazz) {
            result.classList.add(clazz);
        }
        if (content) {
            result.textContent = content;
        }
        return result;
    }
}