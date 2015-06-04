import java.awt.Color;

import javax.swing.JLabel;


public class RPMThread extends Simulation implements Runnable{

	JLabel rpm;
	public static boolean RPMOn = true;
	int x;
	
	
	public RPMThread(Simulation gui) {
		super(gui);
		rpm = gui.rpm;
	}

	@Override
	public void run() {
		while (RPMOn) {
			if (Simulation.velocity > getMinSpeedWRTGear() && Simulation.velocity < getMaxSpeedWRTGear()) {
			x = (int)getMaxSpeedWRTGear() - (int)getMinSpeedWRTGear();
			
			Simulation.rpm1 = (int) (3000 + (Simulation.velocity - getMinSpeedWRTGear())*5000/x);
			rpm.setText(Integer.toString(Simulation.rpm1));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			else if (Simulation.velocity <= getMinSpeedWRTGear()) {
				Simulation.rpm1 = 3000;
				rpm.setText(Integer.toString(Simulation.rpm1));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if (Simulation.velocity >= getMaxSpeedWRTGear()) {
				Simulation.rpm1 = 8000;
				rpm.setText(Integer.toString(Simulation.rpm1));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (Simulation.rpm1 < 5000)
				rpm.setForeground(Color.GREEN);
			else if (Simulation.rpm1 < 7000)
				rpm.setForeground(Color.yellow);
			else if (Simulation.rpm1 <= 8000)
				rpm.setForeground(Color.red);
			UserUI.SpeedOMeter.repaint();
			UserUI.RPMOMeter.repaint();
		}
		RPMOn = true;
	}
}
