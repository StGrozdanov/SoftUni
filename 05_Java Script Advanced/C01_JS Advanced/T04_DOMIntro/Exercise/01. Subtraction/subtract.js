function subtract() {
    const firstNumElement = document.getElementById('firstNumber');
    const secondNumElement = document.getElementById('secondNumber');
    const firstNum = Number(firstNumElement.value);
    const secondNum = Number(secondNumElement.value);
    
    const result = firstNum - secondNum;
    const divElement = document.getElementById('result');
    
    divElement.textContent = result;
}