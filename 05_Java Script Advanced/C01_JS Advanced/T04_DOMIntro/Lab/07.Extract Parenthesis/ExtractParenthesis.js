function extract(content) {
    const text = document.getElementById(content).textContent;
    const regex = /\(([A-Za-z]+\s*)*\)/g;
    const matchedText = text.match(regex);

    return matchedText.join('; ');
}