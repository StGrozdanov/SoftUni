class Bank {
    #bankName;
    constructor(bankName) {
        this.#bankName = bankName;
        this.allCustomers = [];
    }
    newCustomer(customer) {
        if (this.allCustomers.some(c => c.personalId === customer.personalId)) {
            throw new Error(`${customer.firstName} ${customer.lastName} is already our customer!`);
        }
        this.allCustomers.push(customer);
        return customer;
    }
    depositMoney(personalId, amount) {
        if (!this.allCustomers.some(c => c.personalId === personalId)) {
            throw new Error('We have no customer with this ID!')
        }
        let targetCustomer = this.allCustomers.find(c => c.personalId === personalId);
        let transactionInformation = [targetCustomer.firstName, targetCustomer.lastName, 'made deposit of', amount + '$!'];

        if (!targetCustomer.hasOwnProperty('totalMoney')) {
            targetCustomer['totalMoney'] = amount;
            targetCustomer['transactions'] = [transactionInformation];
        } else {
            targetCustomer.totalMoney += amount;
            targetCustomer.transactions.push(transactionInformation);
        }
        return targetCustomer.totalMoney + '$';
    }
    withdrawMoney(personalId, amount) {
        if (!this.allCustomers.some(c => c.personalId === personalId)) {
            throw new Error('We have no customer with this ID!')
        }
        let targetCustomer = this.allCustomers.find(c => c.personalId === personalId);
        if (!targetCustomer.hasOwnProperty('totalMoney') || targetCustomer.totalMoney < amount) {
            throw new Error(`${targetCustomer.firstName} ${targetCustomer.lastName} does not have enough money to withdraw that amount!`);
        }
        let transactionInformation = [targetCustomer.firstName, targetCustomer.lastName, 'withdrew', amount + '$!'];
        targetCustomer.totalMoney -= amount;
        targetCustomer.transactions.push(transactionInformation);

        return targetCustomer.totalMoney + '$';
    }
    customerInfo(personalId) {
        if (!this.allCustomers.some(c => c.personalId === personalId)) {
            throw new Error('We have no customer with this ID!')
        }
        let targetCustomer = this.allCustomers.find(c => c.personalId === personalId);
        let result = [`Bank name: ${this.#bankName}`, `Customer name: ${targetCustomer.firstName} ${targetCustomer.lastName}`, `Customer ID: ${personalId}`,
        `Total Money: ${targetCustomer.totalMoney}$`, `Transactions:`];
        let number = targetCustomer.transactions.length;
        targetCustomer.transactions.reverse().forEach(t => {
            result.push(`${number}. ` + t.join(' '));
            number -= 1;
        });
        return result.join('\n');
    }
}

let bank = new Bank('SoftUni Bank');

console.log(bank.newCustomer({ firstName: 'Svetlin', lastName: 'Nakov', personalId: 6233267 }));
console.log(bank.newCustomer({ firstName: 'Mihaela', lastName: 'Mileva', personalId: 4151596 }));
bank.depositMoney(6233267, 250);
console.log(bank.depositMoney(6233267, 250));
bank.depositMoney(4151596, 555);
console.log(bank.withdrawMoney(6233267, 125));
console.log(bank.customerInfo(6233267));