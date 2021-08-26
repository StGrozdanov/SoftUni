function calculateFruitPrice(fruit, grams, pricePerKg){
    const fruitWeight = grams / 1000;
    const cost = fruitWeight * pricePerKg;

    console.log(`I need $${cost.toFixed(2)} to buy ${fruitWeight.toFixed(2)} kilograms ${fruit}.`)
}

calculateFruitPrice('orange', 2500, 1.80);
calculateFruitPrice('apple', 1563, 2.35);