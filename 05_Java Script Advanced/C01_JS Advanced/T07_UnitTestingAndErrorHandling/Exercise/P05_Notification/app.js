function notify(message) {
  const msgElement = document.getElementById('notification');
  msgElement.textContent = message;
  msgElement.style.display = 'block';
  msgElement.addEventListener('click', () => {
    msgElement.style.display = 'none';
  })
}