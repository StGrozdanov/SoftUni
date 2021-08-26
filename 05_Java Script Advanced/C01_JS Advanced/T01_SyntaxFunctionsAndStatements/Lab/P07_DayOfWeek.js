function printDayOfTheWeekNumber(arg) {
    let result;

    switch (arg) {
        case 'Monday': 
            result = 1;           
        break;
        case 'Tuesday': 
            result = 2;
        break;
        case 'Wednesday': 
            result = 3;
        break;
        case 'Thursday': 
            result = 4;
        break;
        case 'Friday': 
            result = 5;
        break;
        case 'Saturday': 
            result = 6;
        break;
        case 'Sunday': 
            result = 7;
        break;
        default: result = 'error';
    }

    return result;
}

let result1 = printDayOfTheWeekNumber('Monday');
let result2 = printDayOfTheWeekNumber('Friday');
let result3 = printDayOfTheWeekNumber('Invalid');

console.log(result1);
console.log(result2);
console.log(result3);