function calculateTotalAndAverageLengthOfText(input1, input2, input3){
    let totalLength = input1.length + input2.length + input3.length;
    let avgLength = Math.floor(totalLength / 3);

    console.log(totalLength);
    console.log(avgLength);
}

calculateTotalAndAverageLengthOfText('chocolate', 'ice cream', 'cake');
calculateTotalAndAverageLengthOfText('pasta', '5', '22.3');