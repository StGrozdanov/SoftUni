function lockedProfile() {

    fetch('http://localhost:3030/jsonstore/advanced/profiles')
        .then(response => response.json())
        .then(result => {
            Object.entries(result).forEach((e, index) => {
                let obj = e[1];
                const profileDiv = generateUsers(obj, index + 1);
                const main = document.getElementById('main');
                main.appendChild(profileDiv);
            });
        });

    function generateUsers(userData, userNumber) {
        const profileDiv = document.createElement('div');
        profileDiv.classList.add('profile');

        const img = document.createElement('img');
        img.src = './iconProfile2.png';
        img.classList.add('userIcon');

        const lockLabel = document.createElement('label');
        lockLabel.textContent = 'Lock';

        const lockInput = document.createElement('input');
        lockInput.type = 'radio';
        lockInput.name = `user${userNumber}Locked`;
        lockInput.value = 'lock';
        lockInput.checked = true;

        const unlockLabel = document.createElement('label');
        unlockLabel.textContent = 'Unlock';

        const unlockInput = document.createElement('input');
        unlockInput.type = 'radio';
        unlockInput.name = `user${userNumber}Locked`;
        unlockInput.value = 'unlock';
        unlockInput.checked = false;

        const usernameLabel = document.createElement('label');
        usernameLabel.textContent = 'Username';

        const usernameInput = document.createElement('input');
        usernameInput.type = 'text';
        usernameInput.name = `user${userNumber}Username`;
        usernameInput.value = `${userData.username}`;
        usernameInput.disabled = 'readonly';

        const userHiddenFields = document.createElement('div');
        userHiddenFields.id = `user${userNumber}HiddenFields`;

        const emailLabel = document.createElement('label');
        emailLabel.textContent = 'Email:';

        const emailInput = document.createElement('input');
        emailInput.type = 'email';
        emailInput.name = `user${userNumber}Email`;
        emailInput.value = `${userData.email}`;
        emailInput.disabled = 'readonly';

        const ageLabel = document.createElement('label');
        ageLabel.textContent = 'Age:';

        const ageInput = document.createElement('input');
        ageInput.type = 'email';
        ageInput.name = `user${userNumber}Age`;
        ageInput.value = `${userData.age}`;
        ageInput.disabled = 'readonly';

        const showMoreButton = document.createElement('button');
        showMoreButton.textContent = 'Show more';
        showMoreButton.classList.add('button');

        userHiddenFields.appendChild(emailLabel);
        userHiddenFields.appendChild(emailInput);
        userHiddenFields.appendChild(ageLabel);
        userHiddenFields.appendChild(ageInput);

        showMoreButton.addEventListener('click', () => {
            if (unlockInput.checked) {
                if (showMoreButton.textContent == 'Show more') {
                    document.querySelector(`#user${userNumber}HiddenFields`).style.display = 'block'
                    showMoreButton.textContent = 'Hide it'
                } else {
                    document.querySelector(`#user${userNumber}HiddenFields`).style.display = 'none'
                    showMoreButton.textContent = 'Show more'
                }
            }
        });

        profileDiv.appendChild(img);
        profileDiv.appendChild(lockLabel);
        profileDiv.appendChild(lockInput);
        profileDiv.appendChild(unlockLabel);
        profileDiv.appendChild(unlockInput);
        profileDiv.appendChild(usernameLabel);
        profileDiv.appendChild(usernameInput);
        profileDiv.appendChild(userHiddenFields);
        profileDiv.appendChild(showMoreButton);

        return profileDiv;
    }
}