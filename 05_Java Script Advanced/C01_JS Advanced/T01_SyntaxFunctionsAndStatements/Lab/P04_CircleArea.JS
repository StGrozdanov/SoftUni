function calculateCircleArea(arg){
    if (typeof arg === "number"){
        let radius = Math.PI * (arg ** 2);
        console.log(radius.toFixed(2));
    } else {
        console.log(`We can not calculate the circle area, because we receive a ${typeof arg}.`)
    }
}

calculateCircleArea(5);
calculateCircleArea('name');
