import java.awt.*;

/** Represents a star (red) or comet (gray) on the canvas. */
public class CelestialBody {
    public double x, y;      // center
    public double vx, vy;    // velocity (pixels per tick)
    public int radius;       // pixels
    public boolean isStar;   // first body is star

    public CelestialBody(double x, double y, double vx, double vy, int radius, boolean isStar) {
        this.x = x; this.y = y; this.vx = vx; this.vy = vy; this.radius = radius; this.isStar = isStar;
    }

    /** Update position by velocity. */
    public void move() { x += vx; y += vy; }

    /** Draw filled circle. */
    public void draw(Graphics2D g2) {
        g2.setColor(isStar ? Color.RED : Color.LIGHT_GRAY);
        int d = radius * 2;
        g2.fillOval((int) Math.round(x - radius), (int) Math.round(y - radius), d, d);
    }

    /** True if fully outside the canvas. */
    public boolean isOutOfBounds(int width, int height) {
        return (x + radius < 0) || (x - radius > width) || (y + radius < 0) || (y - radius > height);
    }
}
