import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class RPMOMeter extends JPanel{

	private static final long serialVersionUID = 1L;
	public RPMOMeter() {
	       
	    }
	
	
	public Dimension getPreferredSize() {
	        return new Dimension(250,200);
	    }
	 public void paintComponent(Graphics h) {
	        super.paintComponent(h);
	        h.drawOval(14,14,150,150);
	        h.setColor(Color.red);
	        h.fillOval(5, 5, 170, 170);
	        h.setColor(Color.BLACK);
	        h.fillOval(14,14,150,150);
	        h.setColor(Color.white);
	        h.drawString("0", 24, 120);
	        h.drawString("2000", 24, 80);
	        h.drawString("4000", 40, 45);
	        h.drawString("6000", 80, 28);
	        h.drawString("8000", 120, 45);
	        h.setColor(Color.RED);
	        if (Simulation.rpm1 <= 2000)
	        h.drawLine(24, (int) (120 - (Simulation.rpm1*0.02)), 85, 85);
	        else if (Simulation.rpm1 <= 4000)
	        h.drawLine((int) (24 + ((Simulation.rpm1 - 2000)*0.008)), (int) ((80 - (Simulation.rpm1 - 2000)*0.0175)), 85, 85);
	        else if (Simulation.rpm1 <= 6000)
	        h.drawLine((int) (40 + ((Simulation.rpm1 - 4000)*0.02)), (int) ((45 - (Simulation.rpm1 - 4000)*0.0085)), 85, 85);
	        else if (Simulation.rpm1 <= 8000)
	        h.drawLine((int) (80 + ((Simulation.rpm1 - 6000)*0.02)), (int) ((28 + (Simulation.rpm1 - 6000)*0.0085)), 85, 85);
	        h.setColor(Color.white);
	        h.fillOval(75, 75, 20, 20);
	    }

}
