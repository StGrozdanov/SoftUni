function attachEvents() {
    const appendToMsg = document.getElementById('messages');
    const refreshButton = document.getElementById('refresh');
    const sendButton = document.getElementById('submit');
    const nameField = document.querySelector('#controls > input[type=text]:nth-child(2)');
    const msgField = document.querySelector('#controls > input[type=text]:nth-child(5)');

    refreshButton.addEventListener('click', () => getData(appendToMsg));
    sendButton.addEventListener('click', () => {
        const message = { 'author': nameField.value, 'content': msgField.value };
        postData(message);
        
        nameField.value = '';
        msgField.value = '';
    });
}

attachEvents();

async function getData(appendToMsg) {
    appendToMsg.value = '';
    const response = await fetch(`http://localhost:3030/jsonstore/messenger`);
    const data = await response.json();

    Object.values(data).forEach(e => {
        const author = e.author;
        const msg = e.content;
        appendToMsg.value += `${author}: ${msg}\n`;
    });
}

async function postData(message) {
    await fetch(`http://localhost:3030/jsonstore/messenger`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(message)
    });
}