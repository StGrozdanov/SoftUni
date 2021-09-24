function area(){
    return Math.abs(this.x * this.y);
}

function volume(){
    return Math.abs(this.x * this.y * this.z);
}

function solve(area, volume, input){
    let elements = JSON.parse(input);

    function calculate(obj) {
        const calculatedArea = area.call(obj);
        const calculatedVolume = volume.call(obj);
        
        return { area: calculatedArea, volume: calculatedVolume };
    }

    return elements.map(calculate);
}

console.log(solve(area, volume, `[{"x":"1","y":"2","z":"10"},{"x":"7","y":"7","z":"10"},{"x":"5","y":"2","z":"10"}]`));
