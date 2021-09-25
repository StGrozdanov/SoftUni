function factory(cardFace, cardSuit){
    const symbols = {
        S: '\u2660', 
        H: '\u2665', 
        D: '\u2666', 
        C: '\u2663',  
    }

    const card = {
        cardFaces: ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'],
        cardSuits: ['S', 'H', 'D', 'C'],
        toString: () => {
            if (card.cardFaces.includes(cardFace) && card.cardSuits.includes(cardSuit)){
                return `${cardFace}${symbols[cardSuit]}`;
            } else {
                throw new Error();
            }
        }
    }
    if (card.cardFaces.includes(cardFace) && card.cardSuits.includes(cardSuit)){
        return card;
    } else {
        throw new Error();
    }
}

let card1 = factory('8', 'S');
let card2 = factory('K', 'D');
let card3 = factory('A', 'H');
let card4 = factory('J', 'C');
// let card3 = factory('1', 'C');

console.log(card1.toString());
console.log(card2.toString());
console.log(card3.toString());
console.log(card4.toString());