function lockedProfile() {
    const showButtonElements = document.querySelectorAll('div button');
    const hiddenFieldElements = document.querySelectorAll('#user1HiddenFields, #user2HiddenFields, #user3HiddenFields');
    const fieldLockerElements = document.querySelectorAll('input[type=radio]');

    for (let i = 0; i < showButtonElements.length; i++) {
        const showButton = showButtonElements[i];
        let hiddenField = hiddenFieldElements[i];

        for (let i = 0; i < fieldLockerElements.length; i++) {
            const field = fieldLockerElements[i];
            field.addEventListener('click', (e) => {
            let status = e.target.value;
           if (status == 'unlock') {
                showButton.addEventListener('click', showInfoHandler);
            } else if (status == 'lock') {
                showButton.removeEventListener('click', showInfoHandler);
            }
        })
    }  
        function showInfoHandler(){
            const buttonStatus = showButton.textContent;

            if (buttonStatus == 'Hide it'){
                hiddenField.style.display = '';
                showButton.textContent = 'Show more';
            } else {
                hiddenField.style.display = 'inline-block';
                showButton.textContent = 'Hide it';
            }
        }
    }
}