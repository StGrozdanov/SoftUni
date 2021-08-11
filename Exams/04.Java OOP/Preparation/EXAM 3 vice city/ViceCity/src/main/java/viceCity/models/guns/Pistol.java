package viceCity.models.guns;

public class Pistol extends BaseGun {

    public Pistol(String name) {
        super(name, 10, 100);
    }

    @Override
    public int fire() {
        super.setBulletsPerBarrel(super.getBulletsPerBarrel() - 1);
        if (super.getBulletsPerBarrel() <= 0) {
            if (super.getTotalBullets() != 0) {
                super.setBulletsPerBarrel(10);
                super.setTotalBullets(super.getTotalBullets() - 10);
            } else {
                return 0;
            }
        }
        return 1;
    }
}
