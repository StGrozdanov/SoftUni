function colorize() {
    const target = document.querySelectorAll('body table tr');

    for (let i = 1; i < target.length; i++) {
        if (i % 2 !== 0){
            target[i].style.backgroundColor = 'Teal';
        }
    }
}