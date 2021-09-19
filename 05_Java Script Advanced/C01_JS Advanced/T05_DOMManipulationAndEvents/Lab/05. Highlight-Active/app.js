function focused() {
    const mainDiv = document.querySelectorAll('div input');

    for (let element of mainDiv) {
        element.addEventListener('focus', focusHandler);
        element.addEventListener('blur', blurHandler);
    }

    function focusHandler(e) {
        const parent = e.target.parentNode;
        parent.classList.add('focused');
    }
    function blurHandler(e) {
        const parent = e.target.parentNode;
        parent.classList.remove('focused');
    }

 }