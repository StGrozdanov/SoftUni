package T01_WorkingWithAbstraction.Lab.P02_PointInRectangle;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeft = new Point(bottomLeftX, bottomLeftY);
        this.topRight = new Point(topRightX, topRightY);
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }

    public boolean contains(Point point) {
        boolean isInHorizontal = this.bottomLeft.getX() <= point.getX() && this.topRight.getX() >= point.getX();
        boolean isInVertical = this.bottomLeft.getY() <= point.getY() && this.topRight.getY() >= point.getY();
        return isInHorizontal && isInVertical;
    }

}
