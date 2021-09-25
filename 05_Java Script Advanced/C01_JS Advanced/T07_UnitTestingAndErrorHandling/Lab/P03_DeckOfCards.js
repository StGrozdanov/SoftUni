function printDeckOfCards(cardsArr){
    function factory(cardFace, cardSuit){
        const symbols = {S: '\u2660', H: '\u2665', D: '\u2666', C: '\u2663'};
        const card = {
            cardFaces: ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'],
            cardSuits: ['S', 'H', 'D', 'C'],
            toString: () => `${cardFace}${symbols[cardSuit]}`
        };

        if (card.cardFaces.includes(cardFace) && card.cardSuits.includes(cardSuit)){
            return card;
        } else {
            console.log(`Invalid card: ${cardFace}${cardSuit}`);
        }
    }
    let result = '';

    cardsArr.forEach(c => {
        let cardFace;
        let cardSuit;
        const tokens = c.split('');
        if (c.length === 2){
            cardFace = tokens[0];
            cardSuit = tokens[1];
        } else {
            cardFace = tokens[0] + tokens[1];
            cardSuit = tokens[2];
        }
        const card = factory(cardFace, cardSuit);
        if (card != undefined){
            result += card.toString() + ' ';
        }
        });
    
    console.log(result.trim());
}

printDeckOfCards(['AS', '10D', 'KH', '2C']);
printDeckOfCards(['5S', '3D', 'QD', '1C']);