import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;


public class IdleThread extends Simulation implements Runnable{

	JLabel speed;
	public static boolean idleRunning = true;
	File file = new File ("C:\\Idle.wav");
	
	public IdleThread(Simulation gui) {
		super(gui);
		speed = gui.speed;
		idleRunning = true;
	}
	@Override
	public void run() {
		File file = new File ("C:\\Idle.wav");
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		while (idleRunning) {
			if (Simulation.gear == 0 && Simulation.velocity <= 0)
				idleRunning = false;
			if (Simulation.velocity > 0)  {
			Simulation.velocity = Simulation.velocity - 0.5;
			speed.setText(Double.toString(Simulation.velocity));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			else 
				speed.setText("0");
		}
		clip.close();
		idleRunning = true;
		UserUI.SpeedOMeter.repaint();
		UserUI.RPMOMeter.repaint();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}
	}
}
