class Parking {
    constructor(capacity) {
        this.capacity = capacity;
        this.vehicles = [];
    }
    set capacity(value) {
        if (typeof value == 'number' && value != NaN && value > -1) {
            this._capacity = value;
        } else {
            throw new Error('capacity must be an number!');
        }
    }
    get capacity() {
        return this._capacity;
    }
    set vehicles(arr) {
        this._vehicles = arr;
    }
    get vehicles() {
        return this._vehicles;
    }
    addCar(carModel, carNumber) {
        if (this.vehicles.length + 1 > this.capacity) {
            throw new Error("Not enough parking space.");
        }
        let newCar = {
            carModel: carModel,
            carNumber: carNumber,
            payed: false,
        }
        this.vehicles.push(newCar);
        return `The ${carModel}, with a registration number ${carNumber}, parked.`;
    }
    removeCar(carNumber) {
        let targetCar = this.vehicles.find(car => car.carNumber === carNumber);
        if (!targetCar) {
            throw new Error("The car, you're looking for, is not found.");
        }
        if (targetCar.payed === false) {
            throw new Error(`${carNumber} needs to pay before leaving the parking lot.`);
        }
        this.vehicles = this.vehicles.filter(car => car.Number !== carNumber);
        return `${carNumber} left the parking lot.`
    }
    pay(carNumber) {
        let targetCar = this.vehicles.find(car => car.carNumber === carNumber);
        if (!targetCar) {
            throw new Error(`${carNumber} is not in the parking lot.`);
        }
        if (targetCar.payed) {
            throw new Error(`${carNumber}'s driver has already payed his ticket.`);
        }
        targetCar.payed = true;
        this.vehicles = this.vehicles.filter(car => car.Number !== carNumber);
        this.vehicles.push(targetCar);
        return `${carNumber}'s driver successfully payed for his stay.`;
    }
    getStatistics(carNumber) {
        let result = [];
        if (!carNumber) {
            result.push(`The Parking Lot has ${this.capacity - this.vehicles.length} empty spots left.`);
            this.vehicles
                .sort((a, b) => a.carModel.localeCompare(b.carModel))
                .forEach(car => {
                    result.push(`${car.carModel} == ${car.carNumber} - ${car.payed ? 'Has payed' : 'Not payed'}`);
                });
        } else {
            let car = this.vehicles.find(car => car.carNumber === carNumber);
            result.push(`${car.carModel} == ${car.carNumber} - ${car.payed ? 'Has payed' : 'Not payed'}`);
        }
        return result.join('\n');
    }
}

const parking = new Parking(12);

console.log(parking.addCar("Volvo t600", "TX3691CA"));
console.log(parking.getStatistics());

console.log(parking.pay("TX3691CA"));
console.log(parking.removeCar("TX3691CA"));
// Corresponding output
// The Volvo t600, with a registration number TX3691CA, parked.
// The Parking Lot has 11 empty spots left.
// Volvo t600 == TX3691CA - Not payed
// TX3691CA's driver successfully payed for his stay.
// TX3691CA left the parking lot.

