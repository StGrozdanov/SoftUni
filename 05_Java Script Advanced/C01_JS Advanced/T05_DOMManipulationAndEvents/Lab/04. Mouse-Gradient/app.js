function attachGradientEvents() {
    const gradientBoxElement = document.querySelector('#gradient');
    const resultElement = document.querySelector('#result');
    gradientBoxElement.addEventListener('mousemove', (e) => {
        console.log(e);
        let positioning = Math.floor(e.offsetX / gradientBoxElement.clientWidth * 100);
        resultElement.textContent = positioning + '%';
    })
}