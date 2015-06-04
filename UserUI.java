import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserUI {

	// The window object
	JFrame frame;
	Container content;

	JPanel controls;
	JPanel gears;
	JPanel warnings;
	JPanel distances;
	JPanel display;
	static SpeedOMeter SpeedOMeter;
	static RPMOMeter RPMOMeter;
	JLabel warning;
	JLabel speedGui;
	JLabel rpmGui;
	JLabel transmission;
	JLabel distance;
	JLabel gear;
	
	String minSpeed = "0";
	String minRpm = "0";
	

	public static void main(String[] args) {
		UserUI gui = new UserUI();
		gui.setUp();
	}

	private void setUp(){
		gears = new JPanel(new FlowLayout(FlowLayout.CENTER));
		controls = new JPanel(new FlowLayout(FlowLayout.CENTER));
		warnings = new JPanel(new FlowLayout(FlowLayout.CENTER));
		distances = new JPanel(new FlowLayout(FlowLayout.CENTER));
		display = new JPanel(new FlowLayout(FlowLayout.CENTER));

		frame = new JFrame("Gear Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = frame.getContentPane();  // Get the content pane
		content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));  // Set the container layout
		
		SpeedOMeter = new SpeedOMeter();
		RPMOMeter = new RPMOMeter();

		content.add(gears);
		content.add(controls);
		content.add(warnings);
		content.add(distances);
		content.add(SpeedOMeter);
		content.add(RPMOMeter);
		content.add(display);
		
		speedGui = new JLabel(minSpeed);
		speedGui.setFont(new Font("Calibri", Font.BOLD, 65));
		controls.add(speedGui);
		JLabel label = new JLabel("     ");
		controls.add(label);
		rpmGui = new JLabel(minRpm);
		rpmGui.setFont(new Font("Calibri", Font.BOLD, 65));
		controls.add(rpmGui);
		transmission = new JLabel ("0");
		transmission .setFont(new Font("Calibri", Font.BOLD, 28));
		transmission .setForeground(Color.yellow);
		warnings.add(transmission);
		JLabel label1 = new JLabel("     ");
		warnings.add(label1);
		warning = new JLabel ("Engine State: off");
		warning.setFont(new Font("Calibri", Font.BOLD, 20));
		warning.setForeground(Color.red);
		warnings.add(warning);
		distance = new JLabel ("Distance: " + "0");
		distance.setFont(new Font("Calibri", Font.BOLD, 28));
		distance.setForeground(Color.blue);
		distances.add(distance);
		ImageIcon icon = new ImageIcon("E:\\capture.png");
		gear = new JLabel ();
		gear.setIcon(icon);
		display.add(gear);
		
		Simulation object= new Simulation(this);
		
		gear.addMouseListener(object);

		JButton first = new JButton("1");
		first.addMouseListener(object);
		gears.add(first);
		JButton second = new JButton("2");
		second.addMouseListener(object);
		gears.add(second);
		JButton third = new JButton("3");
		third.addMouseListener(object);
		gears.add(third);
		JButton fourth = new JButton("4");
		fourth.addMouseListener(object);
		gears.add(fourth);
		JButton throttle = new JButton("Throttle");
		throttle.addMouseListener(object);
		gears.add(throttle);
		JButton brake = new JButton("Brake");
		brake.addMouseListener(object);
		gears.add(brake);
				
		frame.pack();				// Size for components
		frame.setVisible(true); 	// Display the window
	}
}
