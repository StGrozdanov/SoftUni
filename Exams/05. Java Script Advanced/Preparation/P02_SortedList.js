function createSortedList(){
    return {
        list: [],
        size: 0,
        add: function (num) {
            this.list.push(num);
            this.size++;
            this.list.sort((a, b) => a-b);
        },
        get: function (index) {
            if (index >= 0 && index < this.list.length){
                return this.list[index];   
            } else {
                throw new Error('Index out of bounds!');
            }
        },
        remove: function (index){
            if (index >= 0 && index < this.list.length){
            this.list.splice(index, 1);
            this.size--;
            if (this.size < 0){
                this.size = 0;
            }
            this.list.sort((a, b) => a-b);
        } else {
            throw new Error('Index out of bounds!');
        }
        }
    }
}

let list = createSortedList();
list.add(5);
list.add(6);
list.add(7);
console.log(list.size);
console.log(list.get(1)); 
list.remove(1);
console.log(list.size);
console.log(list.get(1));