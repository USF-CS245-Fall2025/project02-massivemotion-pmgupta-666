import java.awt.*;

public class CelestialBody {
    public double x, y;
    public double vx, vy;
    public int radius;
    public boolean isStar;

    public CelestialBody(double x, double y, double vx, double vy, int radius, boolean isStar) {
        this.x = x; this.y = y; this.vx = vx; this.vy = vy; this.radius = radius; this.isStar = isStar;
    }

    public void move() { x += vx; y += vy; }

    public void draw(Graphics2D g2) {
        g2.setColor(isStar ? Color.RED : Color.LIGHT_GRAY);
        int d = radius * 2;
        g2.fillOval((int) Math.round(x - radius), (int) Math.round(y - radius), d, d);
    }

    public boolean isOutOfBounds(int width, int height) {
        return (x + radius < 0) || (x - radius > width) || (y + radius < 0) || (y - radius > height);
    }
}
