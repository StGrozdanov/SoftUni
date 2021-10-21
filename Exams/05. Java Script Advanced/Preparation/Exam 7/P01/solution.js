window.addEventListener('load', solution);

function solution() {
  const nameField = document.getElementById('fname');
  const emailField = document.getElementById('email');
  const phoneField = document.getElementById('phone');
  const adressField = document.getElementById('address');
  const codeField = document.getElementById('code');
  const submitButton = document.getElementById('submitBTN');

  const appendTo = document.getElementById('infoPreview');
  const editButton = document.getElementById('editBTN');
  const continueButton = document.getElementById('continueBTN');
  const result = document.getElementById('block');

  submitButton.addEventListener('click', () => {
    if (nameField.value.trim() !== '' && emailField.value.trim() !== '') {
      const name = document.createElement('li');
      const email = document.createElement('li');
      name.textContent = `Full Name: ${nameField.value}`;
      email.textContent = `Email: ${emailField.value}`;
      appendTo.appendChild(name);
      appendTo.appendChild(email);

      //optional
      let phone;
      if (phoneField.value.trim() !== '') {
        phone = document.createElement('li');
        phone.textContent = `Phone Number: ${phoneField.value}`;
        appendTo.appendChild(phone);
      }
      let adress;
      if (adressField.value.trim() !== '') {
        adress = document.createElement('li');
        adress.textContent = `Address: ${adressField.value}`;
        appendTo.appendChild(adress);
      }
      let code;
      if (codeField.value.trim() !== '') {
        code = document.createElement('li');
        code.textContent = `Postal Code: ${codeField.value}`;
        appendTo.appendChild(code);
      }

      nameField.value = '';
      emailField.value = '';
      phoneField.value = '';
      adressField.value = '';
      codeField.value = '';

      submitButton.disabled = true;
      editButton.disabled = false;
      continueButton.disabled = false;

      editButton.addEventListener('click', () => {
        nameField.value = name.textContent.replace('Full Name: ', '');
        emailField.value = email.textContent.replace('Email: ', '');
        if (phone) {
          phoneField.value = phone.textContent.replace('Phone Number: ', '');
        } else {
          phoneField.value = '';
        }
        if (adress) {
          adressField.value = adress.textContent.replace('Address: ', '');
        } else {
          adressField.value = '';
        }
        if (code) {
          codeField.value = code.textContent.replace('Postal Code: ', '');
        } else {
          codeField.value = '';
        }
        submitButton.disabled = false;
        editButton.disabled = true;
        continueButton.disabled = true;
        appendTo.textContent = '';
      });
      continueButton.addEventListener('click', () => {
        result.textContent = '';
        const h3 = document.createElement('h3');
        h3.textContent = 'Thank you for your reservation!';
        result.appendChild(h3);
      });
    }
  });
}
