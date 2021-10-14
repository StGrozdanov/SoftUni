function solve(data){
    let calculator = {
        memory: [],
        result: function() {
            if (this.memory.length > 1){
                return 'Error: too many operands!';
            } else if (this.memory.length === 1) {
                return this.memory[0];
            } else {
                return 'Error: not enough operands!';
            }
        },
        checkForError: function (first, second) {
            if (first == undefined || second == undefined){
                return true;
        }},
        sum: function () {
            let first = this.memory.pop(); 
            let second = this.memory.pop(); 
            if(!this.checkForError(first, second)){
                this.memory.push(first + second);
            }
        },
        subtract: function () {
            let first = this.memory.pop(); 
            let second = this.memory.pop(); 
            if(!this.checkForError(first, second)){
                this.memory.push(second - first);
            }
        },
        multiply: function () {
            let first = this.memory.pop(); 
            let second = this.memory.pop(); 
            if(!this.checkForError(first, second)){
                this.memory.push(first * second);
            }
        },
        divide: function () {
            let first = this.memory.pop(); 
            let second = this.memory.pop();
            if(!this.checkForError(first, second)){
                this.memory.push(second / first);
            }
        }
    }
    data.forEach(element => {
        if (typeof element == 'number'){
            calculator.memory.push(element);
        } else {
            switch(element) {
                case '+': 
                calculator.sum();
                break;
                case '-': 
                calculator.subtract();
                break;
                case '*': 
                calculator.multiply();
                break;
                case '/': 
                calculator.divide();
                break; 
            }
        }
    });
    console.log(calculator.result());
}

solve([3, 4, '+']);
solve([5, 3, 4, '*', '-']);
solve([15,'/']);
solve([7, 33, 8, '-']);