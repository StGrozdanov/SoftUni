function createComputerHierarchy() {
    class Product {
        constructor(manufacturer){
            this.manufacturer = manufacturer;
            if (this.constructor == Product) {
                throw new Error("Abstract classes can't be instantiated.");
              }
        }
    }

    class Keyboard extends Product {
        constructor(manufacturer, responseTime){
            super(manufacturer);
            this.responseTime = responseTime;
        }
    }

    class Monitor extends Product {
        constructor(manufacturer, width, height){
            super(manufacturer);
            this.width = width;
            this.height = height;
        }
    }

    class Battery extends Product {
        constructor(manufacturer, expectedLife){
            super(manufacturer);
            this.expectedLife = expectedLife;
        }
    }

    class Computer extends Product {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace){
            super(manufacturer);
            this.processorSpeed = processorSpeed;
            this.ram = ram;
            this.hardDiskSpace = hardDiskSpace;
            if (this.constructor == Computer) {
                throw new Error("Abstract classes can't be instantiated.");
              }
        }
    }

    class Laptop extends Computer {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace, weight, color, battery){
            super(manufacturer, processorSpeed, ram, hardDiskSpace);
            this.weight = weight;
            this.color = color;
            this.battery = battery;
        }
        get battery(){
            return this._battery;
        }
        set battery(value){
            if (!(value instanceof Battery)){
                throw new TypeError(`${value} is not an instance of the Battery class!`);
            }
            this._battery = value;
        }
    }

    class Desktop extends Computer {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace, keyboard, monitor){
            super(manufacturer, processorSpeed, ram, hardDiskSpace);
            this.keyboard = keyboard;
            this.monitor = monitor;
        }
        get keyboard(){
            return this._keyboard;
        }
        set keyboard(board){
            if (!(board instanceof Keyboard)){
                throw new TypeError(`${board} is not an instance of the Keyboard class!`);
            }
            this._keyboard = board;
        }
        get monitor(){
            return this._monitor;
        }
        set monitor(monitor){
            if (!(monitor instanceof Monitor)){
                throw new TypeError(`${monitor} is not an instance of the Monitor class!`);
            }
            this._monitor = monitor;
        }
    }
    return {
        Battery,
        Keyboard,
        Monitor,
        Computer,
        Laptop,
        Desktop
    }
}

let classes = createComputerHierarchy();
let Computer = classes.Computer;
let Laptop = classes.Laptop;
let Desktop = classes.Desktop;
let Monitor = classes.Monitor;
let Battery = classes.Battery;
let Keyboard = classes.Keyboard;

let battery = new Battery('Energy', 3);
console.log(battery);

let laptop = new Laptop("Hewlett Packard", 2.4, 4, 0.5, 3.12, "Silver", battery);
console.log(laptop);