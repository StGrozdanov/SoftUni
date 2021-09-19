function encodeAndDecodeMessages() {
    const sendFieldElement = document.querySelector('#main > div:nth-child(1) > textarea'); 
    const recieveFieldElement = document.querySelector('#main > div:nth-child(2) > textarea');
    const sendButton = document.querySelector('#main > div:nth-child(1) > button');
    const recieveButton = document.querySelector('#main > div:nth-child(2) > button');

    sendButton.addEventListener('click', sendMessageHandler);
    recieveButton.addEventListener('click', decodeMessageHandler);

    function sendMessageHandler() {
    const message = sendFieldElement.value;
    let encryptedMessage = '';

    for (let i = 0; i < message.length; i++) {
        let modifier = message.charCodeAt(i) + 1;
        let newElement = String.fromCharCode(modifier);

        encryptedMessage += newElement;
    }
    recieveFieldElement.textContent = encryptedMessage;
    sendFieldElement.value = '';
}

function decodeMessageHandler() {
    const message = recieveFieldElement.value;
    let decodedMessage = '';

    for (let i = 0; i < message.length; i++) {
        let modifier = message.charCodeAt(i) - 1;
        let newElement = String.fromCharCode(modifier);

        decodedMessage += newElement;
    }
    recieveFieldElement.textContent = decodedMessage;
}

}