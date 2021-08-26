function calculate(number1, number2, operator){
    let result;

    switch(operator){
        case "+": 
        result = number1 + number2;    
        break;
        case "-": 
        result = number1 - number2;
        break;
        case "*": 
        result = number1 * number2;
        break;
        case "/": 
        result = number1 / number2;
        break;
        case "%": 
        result = number1 % number2;
        break;
        case "**": 
        result = number1 ** number2;
        break;
    }
    console.log(result);   
}

calculate(5, 6, '+');
calculate(3, 5.5, '*');