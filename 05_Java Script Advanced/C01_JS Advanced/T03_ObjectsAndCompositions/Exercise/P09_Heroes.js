function solve(){
    return {
        mage: (name) => { 
            let hero = {
                name: name,
                health: 100,
                mana: 100,
                cast: (spellName) => {
                    hero.mana -= 1;
                    console.log(`${name} cast ${spellName}`);
                }
             };
            return hero;
        },
        fighter: (name) => {
            let hero = { 
                name: name,
                health: 100,
                stamina: 100,
                fight: () => {
                    hero.stamina -= 1;
                    console.log(`${name} slashes at the foe!`);
                }
            };
            return hero;
        }
    };
}

let create = solve();
const scorcher = create.mage("Scorcher");
scorcher.cast("fireball")
scorcher.cast("thunder")
scorcher.cast("light")

const scorcher2 = create.fighter("Scorcher 2");
scorcher2.fight()

console.log(scorcher2.stamina);
console.log(scorcher.mana);
