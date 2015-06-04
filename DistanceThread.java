import javax.swing.JLabel;


public class DistanceThread extends Simulation implements Runnable{
	
	public boolean distanceOn = true;
	JLabel distance;
	
	public DistanceThread(Simulation gui) {
		super(gui);
		distance = gui.distance;
	}

	@Override
	public void run() {
		while (distanceOn) {
			if (Simulation.velocity >= 0)
		Simulation.distance1 = (int) (Simulation.distance1 + (Simulation.velocity * 1000)/3600);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		distance.setText("Distance: " + Integer.toString(Simulation.distance1) + "m");
		UserUI.SpeedOMeter.repaint();
		UserUI.RPMOMeter.repaint();
		}
	}
}
