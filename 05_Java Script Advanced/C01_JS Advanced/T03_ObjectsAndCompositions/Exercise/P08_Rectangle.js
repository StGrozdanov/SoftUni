function rectangle(width, height, color) {
    const requiredColor = color[0].toUpperCase() + color.substring(1);
    
    return {
        width: width,
        height: height,
        color: requiredColor,
        calcArea: () => {return height * width}
    }
}

let rect = rectangle(4, 5, 'red');
console.log(rect.width);
console.log(rect.height);
console.log(rect.color);
console.log(rect.calcArea());
