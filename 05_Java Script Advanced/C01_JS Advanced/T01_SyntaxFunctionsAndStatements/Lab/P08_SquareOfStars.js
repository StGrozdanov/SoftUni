function printStars(number = 5){
    let cols = '';
    let num = Number(number);

    for (let i = 0; i < num; i++) {
        for (let j = 0; j < num; j++) {
            cols += '* ';
        }
        console.log(cols);
        cols = '';
    }
}

printStars();