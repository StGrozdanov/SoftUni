class Vacationer {
    constructor(fullName, creditCard = {}) {
        this.fullName = fullName;
        this.creditCard = creditCard;
        this.wishList = [];
        this.idNumber = this.generateIDNumber();
    }
    set fullName(names) {
        let criteria = /^[A-Z][a-z]+$/;
        if (names.length !== 3) {
            throw new Error('Name must include first name, middle name and last name');
        }
        names.forEach(name => {
            let match = name.match(criteria);
            if (!match) {
                throw new Error('Invalid full name');
            }
        });
        this._fullName = { firstName: names[0], middleName: names[1], lastName: names[2] };
    }
    get fullName() {
        return this._fullName;
    }
    get creditCard() {
        return this._creditCard;
    }
    set creditCard(card) {
        if (Object.keys(card).length === 0) {
            this._creditCard = {
                cardNumber: Number(1111),
                expirationDate: '',
                securityNumber: Number(111)
            }
        } else {
            if (Object.keys(card).length !== 3) {
                throw new Error('Missing credit card information');
            }
            if (card[0] === NaN || typeof card[0] != 'number' || card[2] === NaN || typeof card[2] != 'number') {
                throw new Error('Invalid credit card details');
            }
            this._creditCard = {
                cardNumber: card[0],
                expirationDate: card[1],
                securityNumber: card[2]
            }
        }
    }
    generateIDNumber() {
        const vowels = ['a', 'e', 'o', 'i', 'u'];
        let id = (231 * this.fullName.firstName.charCodeAt(0)) + (139 * this.fullName.middleName.length);
        let lastName = this.fullName.lastName;
        let bonusCode = vowels.includes(lastName[lastName.length - 1]) ? 8 : 7;

        return id += `${bonusCode}`;
    }
    addCreditCardInfo(input) {
        if (input.length !== 3) {
            throw new Error('Missing credit card information');
        }
        if (input[0] === NaN || typeof input[0] != 'number' || input[2] === NaN || typeof input[2] != 'number') {
            throw new Error('Invalid credit card details');
        }
        this.creditCard.cardNumber = input[0];
        this.creditCard.expirationDate = input[1];
        this.creditCard.securityNumber = input[2];
    }
    addDestinationToWishList(destination) {
        if (!this.wishList.includes(destination)) {
            this.wishList.push(destination);
        } else {
            throw new Error('Destination already exists in wishlist');
        }
        this.wishList.sort(function (a, b) {
            return a.length - b.length || a.localeCompare(b);
        });
    }
    getVacationerInfo() {
        let wishes = '';
        if (this.wishList.length === 0) {
            wishes = 'empty';
        } else {
            wishes = this.wishList.join(', ');
        }
        let result = [];
        result.push(`Name: ${this.fullName.firstName} ${this.fullName.middleName} ${this.fullName.lastName}`);
        result.push(`ID Number: ${this.idNumber}`);
        result.push(`Wishlist:`);
        result.push(`${wishes}`);
        result.push(`Credit Card:`);
        result.push(`Card Number: ${this.creditCard.cardNumber}`);
        result.push(`Expiration Date: ${this.creditCard.expirationDate}`);
        result.push(`Security Number: ${this.creditCard.securityNumber}`);

        return result.join('\n');
    }
}

let vacationer1 = new Vacationer(["Vania", "Ivanova", "Zhivkova"]);
let vacationer2 = new Vacationer(["Tania", "Ivanova", "Zhivkova"], [123456789, "10/01/2018", 777]);

// Should throw an error (Invalid full name)
try {
    let vacationer3 = new Vacationer(["Tania", "Ivanova", "Zhivkova"], ['123456789', "10/01/2018", 777]);
} catch (err) {
    console.log("Error: " + err.message);
}

// Should throw an error (Missing credit card information)
try {
    let vacationer3 = new Vacationer(["Zdravko", "Georgiev", "Petrov"]);
    vacationer3.addCreditCardInfo([123456789, "20/10/2018"]);
} catch (err) {
    console.log("Error: " + err.message);
}

vacationer1.addDestinationToWishList('Spain');
vacationer1.addDestinationToWishList('Germany');
vacationer1.addDestinationToWishList('Bali');

// Return information about the vacationers
console.log(vacationer1.getVacationerInfo());
console.log(vacationer2.getVacationerInfo());