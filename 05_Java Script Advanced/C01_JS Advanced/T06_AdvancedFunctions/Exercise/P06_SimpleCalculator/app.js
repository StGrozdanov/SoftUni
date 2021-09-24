function calculator() {
    let num1;
    let num2;
    let result;

    return {
        init: (selector1, selector2, resultSelec) => {
            num1 = document.querySelector(selector1);
            num2 = document.querySelector(selector2);
            result = resultSelec.querySelector(resultSelec);
        },
        add: () => result.value = num1.value + num2.value,
        substract: () => result.value = num1.value - num2.value,
    }
}

function calculator() {
    const html = { s1: "", s2: "", output: "" }

    function calc(a, b, sign) {
        const signs = { "+": (a, b) => a + b, "-": (a, b) => a - b }

        return signs[sign](Number(a), Number(b))
    }

    return {
        init: (a, b, c) => {
            html.s1 = document.querySelector(a)
            html.s2 = document.querySelector(b)
            html.output = document.querySelector(c)
        },
        add: () =>
            (html.output.value = calc(html.s1.value, html.s2.value, "+")),
        subtract: () =>
            (html.output.value = calc(html.s1.value, html.s2.value, "-")),
    }
}


