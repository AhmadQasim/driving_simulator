import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;


public class SpeedThread extends Simulation implements Runnable{

	JLabel speed;
	public static boolean mouse = true;
	File file = new File ("C:\\Increase.wav");
	
	public SpeedThread(Simulation gui) {
		super(gui);
		speed = gui.speed;
		mouse = true;
	}

	@Override
	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		while (mouse) {
			if (Simulation.velocity < getMaxSpeedWRTGear1()) {
			Simulation.velocity = Simulation.velocity + 1;
			speed.setText(Double.toString(Simulation.velocity));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			else if (Simulation.velocity == getMaxSpeedWRTGear() || Simulation.velocity > getMaxSpeedWRTGear()) {
				speed.setText(Double.toString(Simulation.velocity));
			}
			else if (Simulation.velocity >= getMaxSpeedWRTGear1() && Simulation.velocity < getMaxSpeedWRTGear2()) {
				Simulation.velocity = Simulation.velocity + 0.5;
				speed.setText(Double.toString(Simulation.velocity));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if (Simulation.velocity >= getMaxSpeedWRTGear2()){
				Simulation.velocity = Simulation.velocity + 0.25;
				speed.setText(Double.toString(Simulation.velocity));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			UserUI.SpeedOMeter.repaint();
			UserUI.RPMOMeter.repaint();
		}
		clip.close();
		mouse = true;
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
