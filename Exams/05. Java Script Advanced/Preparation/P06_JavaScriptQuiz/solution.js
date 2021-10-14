function solve() {
    const questionElements = document.querySelectorAll('section');
    const answerElements = document.querySelectorAll('.answer-text');
    const resultElement = document.querySelector('#results .results-inner h1');

    const correctAnswers = {
        0: 'onclick',
        1: 'JSON.stringify()',
        2: 'A programming API for HTML and XML documents',
    }

    let correctAnswerCounter = 0;
    let counter = -1;

    for (const answer of answerElements) {
        answer.addEventListener('click', answerHandler);
    }

    function answerHandler(e){
        counter++;
        if (e.target.textContent == correctAnswers[counter]){
            correctAnswerCounter++;
        }
        questionElements[counter].style.display = 'none';
        if (counter + 1 == 3){
            questionElements[questionElements.length-1].style.display = 'none';
        } else {
            questionElements[counter+1].style.display = 'block'
        }
        if (counter === questionElements.length - 1) {
            resultElement.parentNode.parentNode.style.display = 'block';
            console.log(resultElement);
            if (correctAnswerCounter < 3){
                resultElement.textContent = `You have ${correctAnswerCounter} right answers`;
            } else {
                resultElement.textContent = "You are recognized as top JavaScript fan!";
            }
        }
    }
}
