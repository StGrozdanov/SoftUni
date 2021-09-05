function solve(object){
    if (object.dizziness) {
        const hidrate = object.weight * 0.1 * 1;
        object.levelOfHydrated += hidrate;
    }
    
    return object;
}

console.log(solve({ weight: 80,
    experience: 1,
    levelOfHydrated: 0,
    dizziness: true }
  ));