function solve() {
    const lectureName = document.querySelector('div:nth-child(1) > input[type=text]');
    const lectureDate = document.querySelector('div:nth-child(2) > input[type=datetime-local]');
    const lectureModule = document.querySelector('div:nth-child(3) > select');
    const appendTo = document.querySelector('section.user-view.section-view > div');

    let modules = [];

    const addButton = document.querySelector('div:nth-child(4) > button');
    addButton.addEventListener('click', (e) => {
        e.preventDefault();
        if (lectureName.value.trim() != '' && lectureDate.value.trim() != '' && lectureModule.value != 'Select module') {
            //2021-09-22T21:34
            const tokens = lectureDate.value.split('T');
            let date = tokens[0].split('-').join('/');
            let time = tokens[1];
            const result = `${lectureName.value} - ${date} - ${time}`;
            const moduleText = `${lectureModule.value.toUpperCase()}-MODULE`;

            if (!modules.includes(moduleText)) {
                modules.push(moduleText);
                const div = createElement('div', 'module');
                const heading = createElement('h3', lectureModule.value, moduleText);
                const ul = createElement('ul');
                const li = createElement('li', 'flex');
                const descr = createElement('h4', null, result);
                const deleteButton = createElement('button', 'red', 'Del');
                li.appendChild(descr);
                li.appendChild(deleteButton);
                deleteButton.addEventListener('click', () => deleteHandler(li, heading));

                ul.appendChild(li);
                div.appendChild(heading);
                div.appendChild(ul);
                appendTo.appendChild(div);
                sort(ul);
            } else {
                const headingLocation = document.querySelector(`.${lectureModule.value}`);
                const ulLocation = headingLocation.parentNode.children[1];
                const li = createElement('li', 'flex');
                const descr = createElement('h4', null, result);
                const deleteLiButton = createElement('button', 'red', 'Del');

                deleteLiButton.addEventListener('click', () => deleteHandler(li, headingLocation));

                li.appendChild(descr);
                li.appendChild(deleteLiButton);
                ulLocation.appendChild(li);
                sort(ulLocation);
            }
        }
    });
    function deleteHandler(li, headingLocation) {
        const ulLocation = headingLocation.parentNode.children[1];
        if (ulLocation.children.length === 1) {
            let target = modules.find(name => name === headingLocation.textContent);
            let index = modules.indexOf(target);
            modules.splice(index, 1);
            headingLocation.parentNode.remove();
            headingLocation.remove();
        }
        li.remove();
        sort(ulLocation);
    }
    function createElement(type, clazz, content) {
        const result = document.createElement(type);
        if (clazz) {
            result.classList.add(clazz);
        }
        result.textContent = content;
        return result;
    }

    function sort(ulLocation) {
        Array.from(ulLocation.children)
            .sort((a, b) => a.textContent.localeCompare(b.textContent))
            .forEach(g => ulLocation.appendChild(g));
    }
}