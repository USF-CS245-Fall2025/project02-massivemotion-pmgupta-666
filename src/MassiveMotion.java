import javax.swing.*;
import java.util.Properties;
import java.io.FileInputStream;

public class MassiveMotion {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java MassiveMotion <propertyfile>");
            return;
        }

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(args[0]);
        props.load(fis);

        int width = Integer.parseInt(props.getProperty("window_size_x", "1024"));
        int height = Integer.parseInt(props.getProperty("window_size_y", "768"));
        int delay = Integer.parseInt(props.getProperty("timer_delay", "75"));

        String listType = props.getProperty("list", "arraylist");

        List<CelestialBody> bodyList = ListFactory.make(listType);

        SimulationCanvas canvas = new SimulationCanvas(props, bodyList);

        JFrame frame = new JFrame("Massive Movement");
        frame.setSize(width, height);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Timer(delay, canvas).start();
    }
}
