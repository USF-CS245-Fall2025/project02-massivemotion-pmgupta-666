import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class SimulationCanvas extends JPanel implements ActionListener {

    private Properties props;
    private List<CelestialBody> bodies;
    private int width, height;
    private double genX, genY;
    private int bodySize, velocityRange;

    public SimulationCanvas(Properties props, List<CelestialBody> list) {
        this.props = props;
        this.bodies = list;

        width = Integer.parseInt(props.getProperty("window_size_x"));
        height = Integer.parseInt(props.getProperty("window_size_y"));
        genX = Double.parseDouble(props.getProperty("gen_x"));
        genY = Double.parseDouble(props.getProperty("gen_y"));
        bodySize = Integer.parseInt(props.getProperty("body_size"));
        velocityRange = Integer.parseInt(props.getProperty("body_velocity"));

        initStar();
    }

    private void initStar() {
        int sx = Integer.parseInt(props.getProperty("star_position_x"));
        int sy = Integer.parseInt(props.getProperty("star_position_y"));
        int size = Integer.parseInt(props.getProperty("star_size"));
        double vx = Double.parseDouble(props.getProperty("star_velocity_x"));
        double vy = Double.parseDouble(props.getProperty("star_velocity_y"));

        bodies.add(new CelestialBody(sx, sy, vx, vy, size, Color.RED));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < bodies.size(); i++) {
            CelestialBody b = bodies.get(i);
            g.setColor(b.color);
            g.fillOval((int)b.x, (int)b.y, b.size, b.size);
        }
    }

    public void actionPerformed(ActionEvent e) {
        maybeAddBody();
        updateBodies();
        removeOffscreen();
        repaint();
    }

    private void maybeAddBody() {
        if (Math.random() < genX) { addFromX(); }
        if (Math.random() < genY) { addFromY(); }
    }

    private void addFromX() {
        double x = Math.random() < 0.5 ? 0 : width;
        double y = Math.random() * height;
        double vx = randomVel();
        double vy = randomVel();
        bodies.add(new CelestialBody(x, y, vx, vy, bodySize, Color.BLUE));
    }

    private void addFromY() {
        double x = Math.random() * width;
        double y = Math.random() < 0.5 ? 0 : height;
        double vx = randomVel();
        double vy = randomVel();
        bodies.add(new CelestialBody(x, y, vx, vy, bodySize, Color.YELLOW));
    }

    private double randomVel() {
        int v = 0;
        while (v == 0) v = (int)(Math.random() * (2 * velocityRange + 1)) - velocityRange;
        return v;
    }

    private void updateBodies() {
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).update();
        }
    }

    private void removeOffscreen() {
        for (int i = 0; i < bodies.size(); i++) {
            CelestialBody b = bodies.get(i);
            if (b.x < -50 || b.x > width + 50 || b.y < -50 || b.y > height + 50) {
                bodies.remove(i);
                i--;
            }
        }
    }
}
