function deleteByEmail() {
    const emailElements = document.querySelectorAll('#customers td:last-child');
    const inputFieldElement = document.querySelector('label input');
    const resultElement = document.querySelector('#result');

    let inputText = inputFieldElement.value;

    for (const emailElement of emailElements) {
        let email = emailElement.textContent;
        if (email == inputText){
            emailElement.parentNode.remove();
            resultElement.textContent = "Deleted.";
            break;
        } else {
            resultElement.textContent = "Not found.";
        }
    } 
    inputFieldElement.value = '';
}