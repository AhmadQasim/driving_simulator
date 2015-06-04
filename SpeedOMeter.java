import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class SpeedOMeter extends JPanel {
	

	private static final long serialVersionUID = 1L;

	public SpeedOMeter() {
       
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(14,14,150,150);
        g.setColor(Color.red);
        g.fillOval(5, 5, 170, 170);
        g.setColor(Color.BLACK);
        g.fillOval(14,14,150,150);
        g.setColor(Color.white);
        g.drawString("0", 24, 120);
        g.drawString("30", 24, 80);
        g.drawString("60", 40, 45);
        g.drawString("90", 80, 28);
        g.drawString("120", 120, 45);
        g.setColor(Color.RED);
        if (Simulation.velocity <= 30)
        g.drawLine(24, (int) (120 - (Simulation.velocity*1.33)), 85, 85);
        else if (Simulation.velocity <= 60)
        g.drawLine((int) (24 + ((Simulation.velocity - 30)*0.53)), (int) ((80 - (Simulation.velocity - 30)*1.16)), 85, 85);
        else if (Simulation.velocity <= 90)
        g.drawLine((int) (40 + ((Simulation.velocity - 60)*1.33)), (int) ((45 - (Simulation.velocity - 60)*0.56)), 85, 85);
        else if (Simulation.velocity <=120)
        g.drawLine((int) (80 + ((Simulation.velocity - 90)*1.33)), (int) ((28 + (Simulation.velocity - 90)*0.56)), 85, 85);
        g.setColor(Color.white);
        g.fillOval(75, 75, 20, 20);
    }
}
