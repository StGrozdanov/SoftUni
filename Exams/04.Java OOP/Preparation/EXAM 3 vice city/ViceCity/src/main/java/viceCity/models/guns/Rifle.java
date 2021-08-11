package viceCity.models.guns;

public class Rifle extends BaseGun{

    public Rifle(String name) {
        super(name, 50, 500);
    }

    @Override
    public int fire() {
        super.setBulletsPerBarrel(super.getBulletsPerBarrel() - 5);
        if (super.getBulletsPerBarrel() <= 0) {
            if (super.getTotalBullets() != 0) {
                super.setBulletsPerBarrel(50);
                super.setTotalBullets(super.getTotalBullets() - 50);
            } else {
                return 0;
            }
        }
        return 5;
    }
}
