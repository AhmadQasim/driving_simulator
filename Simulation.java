import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Simulation implements MouseListener{
	
	JFrame frame;
	JLabel speed;
	JLabel rpm;
	JLabel warning;
	JLabel transmission;
	JLabel distance;
	static double velocity = 0;
	static int gear;
	static int rpm1 = 0;
	static int distance1;
	int x,y;
	
	public Simulation (UserUI gui){
		frame = gui.frame;
		speed = gui.speedGui;
		rpm = gui.rpmGui;
		warning = gui.warning;
		transmission = gui.transmission;
		distance = gui.distance;
	}
	
	public Simulation (Simulation gui){
		frame = gui.frame;
		speed = gui.speed;
		rpm = gui.rpm;
		warning = gui.warning;
		transmission = gui.transmission;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getX() == 5){
			gearSound();
			if (velocity >= getMinSpeedWRTGearL(1) && velocity <= getMaxSpeedWRTGearL(1)) {
			Thread t7 = new Thread(new RPMThread(this));
			t7.start();
			Thread t11 = new Thread(new DistanceThread(this));
			t11.start();
			gear = 1;
			warning.setText("Engine State: Normal");
			}
			else if (velocity > getMaxSpeedWRTGearL (1)) {
				Thread t10 = new Thread(new EngineDamageThread(this));
				t10.start();
				gear = 1;
			}
			}
			else if (e.getComponent().getX() == 51) {
				gearSound();
				if (velocity >= getMinSpeedWRTGearL(2) && velocity <= getMaxSpeedWRTGearL(2))
				gear = 2;
				else if (velocity < getMinSpeedWRTGearL(2)) {
					RPMThread.RPMOn = false;
					rpm.setText("0");
					warning.setText("Engine State: Off");
					gear = 0;
				}
				else if (velocity > getMaxSpeedWRTGearL (2)){
					Thread t10 = new Thread(new EngineDamageThread(this));
					t10.start();
					gear = 2;
				}
			}
			else if (e.getComponent().getX() == 97) {
				gearSound();
				if (velocity >= getMinSpeedWRTGearL(3) && velocity <= getMaxSpeedWRTGearL(3))
					gear = 3;
					else if (velocity < getMinSpeedWRTGearL(3)) {
						RPMThread.RPMOn = false;
						rpm.setText("0");
						warning.setText("Engine State: Off");
						gear = 0;
					}
					else if (velocity > getMaxSpeedWRTGearL (3)){
						Thread t8 = new Thread(new EngineDamageThread(this));
						t8.start();
						gear = 3;
					}
			}
			else if (e.getComponent().getX() == 143) {
				gearSound();
				if (velocity >= getMinSpeedWRTGearL(4) && velocity <= getMaxSpeedWRTGearL(4))
					gear = 4;
					else if (velocity < getMinSpeedWRTGearL(4)) {
						RPMThread.RPMOn = false;
						rpm.setText("0");
						warning.setText("Engine State: Off");
						gear = 0;
					}
					else if (velocity > getMaxSpeedWRTGearL (4)){
						Thread t9 = new Thread(new EngineDamageThread(this));
						t9.start();
						gear = 4;
					}
			}
		transmission.setText(Integer.toString(gear));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent().getX() == 90) {
			x = e.getPoint().x;
			y = e.getPoint().y;		
			}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent().getX() == 90) {
			if (x!=0) {
			if (y - e.getPoint().y > -100 && y - e.getPoint().y < 100){
				if (x > e.getPoint().x && gear >= 3)
					gear = gear - 2;
				else if (x < e.getPoint().x && gear <= 3) {
					gear = gear + 2;
				}
			}
			else {
				System.out.println(y);
				System.out.println(e.getPoint().y);
				if (y > e.getPoint().y && gear >= 2)
					gear = gear - 1;
				else if (y < e.getPoint().y && gear <= 3)
					gear = gear +1;
			}
		}
			else
				gear = 0;
		transmission.setText(String.valueOf(gear));
	}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getComponent().getX() == 189 && gear != 0){
		IdleThread.idleRunning = false;
		Thread t1 = new Thread(new SpeedThread(this));
		t1.start();
		}
		else if (e.getComponent().getX() == 272 && gear != 0) {
		IdleThread.idleRunning = false;
		Thread t2 = new Thread(new BrakeThread(this));
		t2.start();
		}
		if (e.getComponent().getX() == 90)
			x = 0;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getComponent().getX() == 189){
			SpeedThread.mouse = false;
			Thread t3 = new Thread(new IdleThread(this));
			t3.start();
			}
		if (e.getComponent().getX() == 272){
			BrakeThread.mouse = false;
			Thread t4 = new Thread(new IdleThread(this));
			t4.start();
			}
	}
	public double getMinSpeedWRTGear (){
		if (gear == 1)
			return 0;
		else if (gear == 2)
			return 23;
		else if (gear == 3)
			return 45;
		else if (gear == 4)
			return 60;
		
	return 0;
	}
	public double getMaxSpeedWRTGear (){
		if (gear == 1)
			return 30;
		else if (gear == 2)
			return 50;
		else if (gear == 3)
			return 75;
		else if (gear == 4)
			return 120;
		return 0;
	}
	
	public double getMaxSpeedWRTGear1 (){
		if (gear == 1)
			return 15;
		else if (gear == 2)
			return 35;
		else if (gear == 3)
			return 57;
		else if (gear == 4)
			return 85;
		
	return 0;
	}
	
	public double getMaxSpeedWRTGear2 (){
		if (gear == 1)
			return 22;
		else if (gear == 2)
			return 40;
		else if (gear == 3)
			return 66;
		else if (gear == 4)
			return 105;
		
	return 0;
	}
	
	public double getMinSpeedWRTGearL (int gear){
		if (gear == 1)
			return 0;
		else if (gear == 2)
			return 23;
		else if (gear == 3)
			return 45;
		else if (gear == 4)
			return 60;
		
	return 0;
	}
	public double getMaxSpeedWRTGearL (int gear){
		if (gear == 1)
			return 30;
		else if (gear == 2)
			return 50;
		else if (gear == 3)
			return 75;
		else if (gear == 4)
			return 120;
		return 0;
	}
	public void gearSound () {
		File file = new File ("C:\\GearShift.wav");
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
			clip.loop(0);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
