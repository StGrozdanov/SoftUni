    class List {
        data = [];
        size = 0;

        add(number) {
            this.data.push(number);
            this.size++;
            this._sortList();
        }
        remove(index) {
            this._validateIndex(index);
            if (this.size === 0) {
                throw new Error('Cannot remove element from empty array!');
            }
            this.data.splice(index, 1);
            this.size--;
            if (this.size < 0){
                this.size = 0;
            }
            this._sortList();
        }
        get(index) {
            this._validateIndex(index);
            return this.data.find(e => this.data.indexOf(e) === index);
        }
        _validateIndex(index){
            if (index < 0 || index >= this.data.length){
                throw new Error(`Index out of bounds for length ${this.data.lenght}!`);
            }
        }
        _sortList(){
            this.data.sort((a, b) => a - b);
        }
    }

    let list = new List();
    list.add(5);
    list.add(6);
    list.add(7);
    console.log(list.get(1)); 
    list.remove(1);
    console.log(list.get(1));
    console.log(list.size);
    list.add(5);
    console.log(list.get(0));
    list.add(3);
    console.log(list.get(0));
