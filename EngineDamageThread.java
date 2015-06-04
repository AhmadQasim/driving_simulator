import javax.swing.JLabel;


public class EngineDamageThread  extends Simulation implements Runnable{

	JLabel warning;
	private boolean ThreadOn = true; 
	
	public EngineDamageThread(Simulation gui) {
		super(gui);
		warning = gui.warning;
	}

	@Override
	public void run() {
		while (ThreadOn)
		{
			System.out.println(123);
			warning.setText("Engine State: Critical");
			Simulation.velocity = Simulation.velocity - 0.25;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Simulation.velocity <= getMaxSpeedWRTGear()) {
				ThreadOn = false;
				warning.setText("Engine State: Normal");
			}
			UserUI.SpeedOMeter.repaint();
			UserUI.RPMOMeter.repaint();
		}
		ThreadOn = true;
	}
}
