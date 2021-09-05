function sortAndNomerateElements(arr){
    arr.sort().forEach((e, i) => console.log(i + 1 + '.' + e));
}

sortAndNomerateElements(["John", "Bob", "Christina", "Ema"]);