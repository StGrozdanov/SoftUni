class SummerCamp {
    constructor(organizer, location) {
        this.organizer = organizer;
        this.location = location;
        this.priceForTheCamp = { "child": 150, "student": 300, "collegian": 500 };
        this.listOfParticipants = [];
    }
    registerParticipant(name, condition, money) {
        money = Number(money);
        if (!this.priceForTheCamp.hasOwnProperty(condition)) {
            throw new Error('Unsuccessful registration at the camp.');
        }
        if (this.listOfParticipants.some(p => p.name === name)) {
            return `The ${name} is already registered at the camp.`;
        }
        if (this.priceForTheCamp[condition] > money) {
            return `The money is not enough to pay the stay at the camp.`;
        }
        let participant = {
            name: name,
            condition: condition,
            power: 100,
            wins: 0
        }
        this.listOfParticipants.push(participant);
        return `The ${name} was successfully registered.`;
    }
    unregisterParticipant(name) {
        if (!this.listOfParticipants.some(p => p.name === name)) {
            throw new Error(`The ${name} is not registered in the camp.`);
        }
        let target = this.listOfParticipants.find(p => p.name === name);
        let index = this.listOfParticipants.indexOf(target);
        this.listOfParticipants.splice(index, 1);
        return `The ${name} removed successfully.`;
    }
    timeToPlay(typeOfGame, participant1, participant2) {
        if (typeOfGame === 'WaterBalloonFights') {
            if (!this.listOfParticipants.some(p => p.name === participant1) || !this.listOfParticipants.some(p => p.name === participant2)) {
                throw new Error(`Invalid entered name/s.`);
            }

            let player1 = this.listOfParticipants.find(p => p.name === participant1);
            let player2 = this.listOfParticipants.find(p => p.name === participant2);

            if (player1.condition !== player2.condition) {
                throw new Error(`Choose players with equal condition.`);
            }

            if (player1.power > player2.power) {
                player1.wins += 1;
                return `The ${player1.name} is winner in the game WaterBalloonFights.`;
            } else if (player2.power > player1.power) {
                player2.wins += 1;
                return `The ${player2.name} is winner in the game WaterBalloonFights.`;
            } else {
                return `There is no winner.`;
            }

        } else if (typeOfGame === 'Battleship') {
            if (!this.listOfParticipants.some(p => p.name === participant1)) {
                throw new Error(`Invalid entered name/s.`);
            }
            let player = this.listOfParticipants.find(p => p.name === participant1);
            player.power += 20;
            return `The ${participant1} successfully completed the game Battleship.`;
        }
    }
    toString() {
        let result = [`${this.organizer} will take ${this.listOfParticipants.length} participants on camping to ${this.location}`];
        this.listOfParticipants
            .sort((p1, p2) => p2.wins - p1.wins)
            .forEach(p => {
                result.push(`${p.name} - ${p.condition} - ${p.power} - ${p.wins}`);
            });
        return result.join('\n');
    }
}

const summerCamp = new SummerCamp("Jane Austen", "Pancharevo Sofia 1137, Bulgaria");
console.log(summerCamp.registerParticipant("Petar Petarson", "student", 300));
console.log(summerCamp.timeToPlay("Battleship", "Petar Petarson"));
console.log(summerCamp.registerParticipant("Sara Dickinson", "child", 200));
// console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Sara Dickinson"));
console.log(summerCamp.registerParticipant("Dimitur Kostov", "student", 300));
console.log(summerCamp.timeToPlay("WaterBalloonFights", "Petar Petarson", "Dimitur Kostov"));

console.log(summerCamp.toString());
