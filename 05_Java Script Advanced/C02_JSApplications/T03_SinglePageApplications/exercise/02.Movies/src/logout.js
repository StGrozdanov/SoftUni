import { appReload } from "../app.js";
import { logout } from "./registry.js";

const logoutButton = document.getElementById('logout');
logoutButton.addEventListener('click', (e) => e.preventDefault);

async function preview() {
    await logout();
    appReload();
}

function close() {

}

export default {
    preview,
    close
}