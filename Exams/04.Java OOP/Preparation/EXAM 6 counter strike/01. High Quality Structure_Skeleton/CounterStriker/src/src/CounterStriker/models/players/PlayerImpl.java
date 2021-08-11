package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setAlive(true);
        this.setGun(gun);
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {
        if (this.getArmor() - points > 0){
            this.setArmor(this.getArmor() - points);
        } else {
            int excessDamage = points - getArmor();
            this.setArmor(0);
            if (this.getHealth() - excessDamage <= 0){
                this.setHealth(0);
                this.setAlive(false);
            } else {
                this.setHealth(this.getHealth() - excessDamage);
            }
        }
    }

    private void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n--Health: %d%n--Armor: %d%n--Gun: %s",
                this.getClass().getSimpleName(), this.username, this.health, this.armor, this.gun.getName());
    }
}
