const notificationElement = document.getElementById('errorBox');

export function showNotification(msg) {
    notificationElement.style.display = 'block';
    notificationElement.querySelector('span').textContent = msg;

    setTimeout(() => notificationElement.style.display = 'none', 3000)
}

window.notify = showNotification;