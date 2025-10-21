import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private int windowX, windowY, timerDelay;
    private String listKind;
    private int starPosX, starPosY, starSize, starVx, starVy;
    private double genXProb, genYProb;
    private int bodySize, bodyVelocity;

    private final Random rng = new Random();
    private List<CelestialBody> bodies;

    public MassiveMotion(String propFile) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(propFile)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Could not read properties file, using defaults.");
        }

        timerDelay = getInt(props, "timer_delay", 75);
        listKind   = props.getProperty("list", "arraylist");

        windowX    = getInt(props, "window_size_x", 1024);
        windowY    = getInt(props, "window_size_y", 768);

        starPosX   = getInt(props, "star_position_x", windowX / 2);
        starPosY   = getInt(props, "star_position_y", windowY / 2);
        starSize   = getInt(props, "star_size", 30);
        starVx     = getInt(props, "star_velocity_x", 0);
        starVy     = getInt(props, "star_velocity_y", 0);

        genXProb   = getDouble(props, "gen_x", 0.06);
        genYProb   = getDouble(props, "gen_y", 0.06);
        bodySize   = getInt(props, "body_size", 10);
        bodyVelocity = getInt(props, "body_velocity", 3);

        bodies = ListFactory.make(listKind);

        bodies.add(new CelestialBody(starPosX, starPosY, starVx, starVy, starSize, true));

        setPreferredSize(new Dimension(windowX, windowY));
        setBackground(Color.BLACK);

        tm = new Timer(timerDelay, this);
    }

    private static int getInt(Properties p, String k, int def) {
        try { return Integer.parseInt(p.getProperty(k, String.valueOf(def)).trim()); }
        catch (Exception e) { return def; }
    }

    private static double getDouble(Properties p, String k, double def) {
        try { return Double.parseDouble(p.getProperty(k, String.valueOf(def)).trim()); }
        catch (Exception e) { return def; }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).draw(g2);
        }
        g2.dispose();
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (rng.nextDouble() < genXProb) spawnTopOrBottom();
        if (rng.nextDouble() < genYProb) spawnLeftOrRight();

        for (int i = 0; i < bodies.size(); i++) bodies.get(i).move();

        for (int i = bodies.size() - 1; i >= 0; i--) {
            if (bodies.get(i).isOutOfBounds(windowX, windowY)) {
                bodies.remove(i);
            }
        }

        repaint();
    }

    private void spawnTopOrBottom() {
        int y = rng.nextBoolean() ? 0 : windowY;
        int x = rng.nextInt(windowX + 1);
        int vx = nonZeroRand(bodyVelocity);
        int vy = (y == 0) ? nonZeroRand(bodyVelocity) : -nonZeroRand(bodyVelocity);
        bodies.add(new CelestialBody(x, y, vx, vy, bodySize, false));
    }

    private void spawnLeftOrRight() {
        int x = rng.nextBoolean() ? 0 : windowX;
        int y = rng.nextInt(windowY + 1);
        int vx = (x == 0) ? nonZeroRand(bodyVelocity) : -nonZeroRand(bodyVelocity);
        int vy = nonZeroRand(bodyVelocity);
        bodies.add(new CelestialBody(x, y, vx, vy, bodySize, false));
    }

    private int nonZeroRand(int bound) {
        if (bound <= 0) return 0;
        int v = 0;
        while (v == 0) v = rng.nextInt(bound * 2 + 1) - bound;
        return v;
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        String file = (args.length > 0) ? args[0] : "MassiveMotion.txt";
        MassiveMotion mm = new MassiveMotion(file);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(mm);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
