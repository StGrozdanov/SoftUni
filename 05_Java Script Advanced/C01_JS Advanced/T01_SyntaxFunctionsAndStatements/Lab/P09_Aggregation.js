    function agrElements(elements) {
        let sum = 0;
        let inverseSum = 0;
        let concatenation = '';
    
        for (let i = 0; i < elements.length; i++) {
            sum += elements[i];
            inverseSum += 1/elements[i];
            concatenation += elements[i];
        }
    
        console.log(sum);
        console.log(inverseSum);
        console.log(concatenation);
    }

    agrElements([2, 4, 8, 16]);