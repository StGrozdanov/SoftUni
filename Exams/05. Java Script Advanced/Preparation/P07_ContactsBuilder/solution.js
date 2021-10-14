function solve() {
    const mainElement = document.querySelector('#main');
    mainElement.textContent = '';

    class Contact {
        constructor(firstName, lastName, phone, email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.email = email;
            this._online = false;
        }
        get online(){
            return this._online;
        }
        set online(status){
            this._online = status;
            const titleElement = document.getElementById(`#${this.email}`);
            if (status === true) {
                titleElement.classList.add('online');
            } else {
                titleElement.classList.remove('online');
            }
        }
        render(id) {
            const appendTo = document.querySelector(`#${id}`);
            const article = document.createElement('article');
            const title = document.createElement('div');
            const info = document.createElement('div');
            const button = document.createElement('button');
            const phoneSpan = document.createElement('span');
            const emailSpan = document.createElement('span');

            //construct info
            info.classList.add('info');
            const phoneIcon = '\u260E';
            const emailIcon = '✉';
            phoneSpan.textContent = `${phoneIcon} ${this.phone}`;
            emailSpan.textContent = `${emailIcon} ${this.email}`;
            info.appendChild(phoneSpan);
            info.appendChild(emailSpan);
            info.style.display = 'none';

            //construct title
            title.classList.add('title');
            button.innerHTML = '&#8505;';
            button.addEventListener('click', () => {
                if (info.style.display == 'none') {
                    info.style.display = 'block'
                } else {
                    info.style.display = 'none';
                }
            });
            title.textContent = `${this.firstName} ${this.lastName}`;
            title.id = `#${this.email}`;
            title.appendChild(button);

            article.appendChild(title);
            article.appendChild(info);
            appendTo.appendChild(article);
        }
    }

    let contacts = [
        new Contact("Ivan", "Ivanov", "0888 123 456", "i.ivanov@gmail.com"),
        new Contact("Maria", "Petrova", "0899 987 654", "mar4eto@abv.bg"),
        new Contact("Jordan", "Kirov", "0988 456 789", "jordk@gmail.com")
    ];

    let contact = new Contact('Ivan', 'Ivanov', '0888 123 456', 'i.ivanov@gmail.com');


    console.log(typeof contact.online);
    console.log(typeof contact.render);
    console.log(contact.render.length);

    contacts.forEach(c => c.render('main'));
    // After 1 second, change the online status to true
    setTimeout(() => contacts[1].online = true, 2000);
    setTimeout(() => contacts[2].online = true, 3000);
    setTimeout(() => contacts[0].online = true, 4000);
    setTimeout(() => contacts[1].online = false, 5000);
}