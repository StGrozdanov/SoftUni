function solution() {
    const inputField = document.querySelector('input[type=text]');
    const listGifts = document.querySelector('section:nth-child(2) ul');
    const sendGifts = document.querySelector('section:nth-child(3) ul');
    const discardGifts = document.querySelector('section:nth-child(4) ul');
    const addButton = document.querySelector('div section:nth-child(1) div button');

    addButton.addEventListener('click', clickHandler);

    function clickHandler() {
        const newElement = createElement('li', 'gift', inputField.value);
        const sendButton = createElement('button', 'sendButton', 'Send');
        const discardButton = createElement('button', 'discardButton', 'Discard');

        sendButton.addEventListener('click', () => appendHandler(sendGifts, newElement));
        discardButton.addEventListener('click', () => appendHandler(discardGifts, newElement));

        newElement.appendChild(sendButton);
        newElement.appendChild(discardButton);

        listGifts.appendChild(newElement);
        sort();
        inputField.value = '';
    }

    function createElement(type, clazz, content) {
        const result = document.createElement(type);
        if (clazz) {
            result.classList.add(clazz);
        }
        result.textContent = content;
        return result;
    }

    function appendHandler(location, element) {
        const newElement = createElement('li', 'gift', element.childNodes[0].textContent);
        location.appendChild(newElement);
        element.remove();
    }

    function sort() {
        Array.from(listGifts.children)
            .sort((a, b) => a.textContent.localeCompare(b.textContent))
            .forEach(g => listGifts.appendChild(g));
    }
}