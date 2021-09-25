function validate() {
    const emailInputElement = document.querySelector('#email');
    emailInputElement.addEventListener('change', validate);

    const requestEmail = emailInputElement.value;
    let regex = /[a-z0-9&]+@[a-z0-9&]+.[a-z0-9&]+/;
    let match = requestEmail.match(regex);

    if (requestEmail != '' && !match){
        emailInputElement.classList.add('error');
    } else {
        emailInputElement.classList.remove('error');
    }
}