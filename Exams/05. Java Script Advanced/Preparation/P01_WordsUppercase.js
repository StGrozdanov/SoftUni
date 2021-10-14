function solve(text){
    let words = text.split(/\W+/).map(w => w.toUpperCase()).join(', ');
    let output;
    words[words.length-2] == ',' ? output = words.substring(0, words.length - 2) : output = words;
    return output;
}

console.log(solve('Hi, Hello! There is the coffee, you ordered today. I am very pleased with your results!'));
console.log(solve('Hi, Hello! There is the coffee, you ordered today. I am very pleased with your results'));