function extractText() {
    const extractedText = document.querySelectorAll('#items li');
    const initialState = document.getElementById('result');
    let result = '';

    for (const item of extractedText) {
       result += item.textContent + '\n';
    }
    initialState.textContent = result.trim();
}