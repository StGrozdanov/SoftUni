function toggle() {
    const hiddenText = document.getElementById('extra');
    const buttonText = document.getElementsByClassName('button');

    if (hiddenText.style.display == 'block'){
        buttonText[0].textContent = 'More';
        hiddenText.style.display = 'none';
    } else {
        buttonText[0].textContent = 'Less';
        hiddenText.style.display = 'block';
    }
}