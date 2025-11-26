import java.awt.*;

public class CelestialBody {
    public double x, y;
    public double vx, vy;
    public int size;
    public Color color;

    public CelestialBody(double x, double y, double vx, double vy, int size, Color c) {
        this.x = x; this.y = y;
        this.vx = vx; this.vy = vy;
        this.size = size;
        this.color = c;
    }

    public void update() {
        x += vx;
        y += vy;
    }
}
