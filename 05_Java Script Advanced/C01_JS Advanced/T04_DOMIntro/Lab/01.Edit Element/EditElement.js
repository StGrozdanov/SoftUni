function editElement(ref, match, replacer) {
        const content = ref.textContent;
        const regexCriteria = /%\D+%/;
        const element = content.match(regexCriteria);

        if (element == match){
            const edit = content.replace(element, replacer);
            ref.textContent = edit;
        } else {
            const edit = content.replace(replacer, match);
            ref.textContent = edit;
        }
}