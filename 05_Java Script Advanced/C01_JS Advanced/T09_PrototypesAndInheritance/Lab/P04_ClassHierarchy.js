function classHierarchy() {
    class Figure {
        constructor(){
            this.units = 'cm';
            this.unitMemory = [];
        }

        get area(){
            return undefined;
        }

        changeUnits(unit){
            this.unitMemory.push(this.units);
            this.units = unit;
        }

        calculateModifier(){
            let result = 1;

            if (this.unitMemory.length > 0) {
            const previousUnit = this.unitMemory[this.unitMemory.length - 1];

            switch(this.units){
                case 'mm': 
                previousUnit == 'cm' ? result = 10 : result = 1000;
                break;
                case 'cm':
                previousUnit == 'mm' ? result = 0.1 : result = 0.001;
                break;
                case 'm': 
                previousUnit == 'cm' ? result = 0.01 : result = 0.001;
                break;
            } 
        }
            return result;
        }
        toString(){
            return `Figures units: ${this.units}`;
        }
    }

    class Circle extends Figure {
        constructor(radius){
            super();
            this.radius = radius;
        }
        get area(){
            return Math.PI * this.radius * this.radius;
        }
        changeUnits(unit){
            super.changeUnits(unit);
            this.radius *= super.calculateModifier();
        }
        toString(){
            return super.toString() + ` Area: ${this.area} - radius: ${this.radius}`;
        }
    }

    class Rectangle extends Figure {
        constructor(width, height, units){
            super();
            this.width = width;
            this.height = height;
            this.changeUnits(units);
        }

        get area(){
            return this.width * this.height;
        }

        changeUnits(unit){
            super.changeUnits(unit);
            this.width *= super.calculateModifier();
            this.height *= super.calculateModifier();
        }

        toString(){
            return super.toString() + ` Area: ${this.area} - width: ${this.width}, height: ${this.height}`;
        }

    }
    return {Figure, Circle, Rectangle};
}

let classData = classHierarchy();

let Figure = classData.Figure;
let Circle = classData.Circle;
let Rectangle = classData.Rectangle;

let c = new Circle(5);
console.log(c.area); // 78.53981633974483
console.log(c.toString()); // Figures units: cm Area: 78.53981633974483 - radius: 5

let r = new Rectangle(3, 4, 'mm');
console.log(r.area); // 1200 
console.log(r.toString()); //Figures units: mm Area: 1200 - width: 30, height: 40

r.changeUnits('cm');
console.log(r.area); // 12
console.log(r.toString()); // Figures units: cm Area: 12 - width: 3, height: 4

c.changeUnits('mm');
console.log(c.area); // 7853.981633974483
console.log(c.toString()) // Figures units: mm Area: 7853.981633974483 - radius: 50