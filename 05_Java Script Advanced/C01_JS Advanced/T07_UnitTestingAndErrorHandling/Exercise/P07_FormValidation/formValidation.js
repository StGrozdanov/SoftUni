function validate() {
    //input fields
    const usernameInputElement = document.querySelector('#username');
    const emailInputElement = document.querySelector('#email');
    const passwordInputElement = document.querySelector('#password');
    const confirmPasswordInputElement = document.querySelector('#confirm-password');
    const companyNumberInputElement = document.querySelector('#companyInfo');

    //elements
    const buttonElement = document.querySelector('#submit');
    const companyCheckBox = document.querySelector('#company');

    companyCheckBox.addEventListener('change', () => {
        companyCheckBox.checked ? companyNumberInputElement.style.display = 'block' : companyNumberInputElement.style.display = 'none';
    });
    
    buttonElement.addEventListener('click', (e) => {
        //prevent page refresh because of stupid HTML implementation uppon button click
        e.preventDefault();
        //validation
        const requestUsername = usernameInputElement.value;
        const requestEmail = emailInputElement.value;
        const requestPassword = passwordInputElement.value;
        const requestConfirmPassword = confirmPasswordInputElement.value;
        let requestCompanyElement = document.querySelector('#companyNumber');

        if (companyNumberInputElement.style.display == 'none'){
            requestCompanyElement.value = '';
        }
        let requestCompany = Number(requestCompanyElement.value);

        const validUsername = /^[A-Za-z0-9]{3,20}$/;
        const validPassword = /^[\w]{5,15}$/;

        let validCompany;
        if (requestCompany !== NaN && requestCompany >= 1000 && requestCompany < 10000 && requestCompany !== ''){
            validCompany = true;
        } else {
            validCompany = false;
        }
        let validEmail;
        requestEmail.includes('@') && requestEmail.includes('.') ? validEmail = true : validEmail = false;

        const passwordMatch = requestPassword.match(validPassword);
        const confirmPasswordMatch = requestConfirmPassword.match(validPassword);
        const usernameMatch = requestUsername.match(validUsername);

        usernameMatch ? usernameInputElement.style.borderColor = '' : usernameInputElement.style.borderColor = 'red';
        validEmail ? emailInputElement.style.borderColor = '' : emailInputElement.style.borderColor = 'red';
        passwordMatch ? passwordInputElement.style.borderColor = '' : passwordInputElement.style.borderColor = 'red';
        if (!confirmPasswordMatch || requestPassword !== requestConfirmPassword){
            confirmPasswordInputElement.style.borderColor = 'red';
            passwordInputElement.style.borderColor = 'red';
        } else {
            confirmPasswordInputElement.style.borderColor = '';
        }

        if (!validCompany && companyCheckBox.checked){
            requestCompanyElement.style.borderColor = 'red';
        } else {
            requestCompanyElement.style.borderColor = '';
        }

        //check if all fields are valid and act
        const fieldElements = document.querySelectorAll('.pairContainer');
        const validFieldElement = document.querySelector('#valid');
        let condition = 'valid';

        for (const element of fieldElements) {
            if (element.children[1].style.borderColor == 'red'){
                condition = 'invalid';
                break;
            }
        }
        condition == 'valid' ? validFieldElement.style.display = 'block' : validFieldElement.style.display = 'none';
    });
}
