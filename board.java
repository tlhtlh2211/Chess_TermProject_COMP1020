package term_project;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class board extends JComponent{
	int width;
	int height;
	board(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0, 0, 0));
		BasicStroke stroke = new BasicStroke(3);
		g2d.setStroke(stroke);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j ++) {
				int y = 50*i;
				int x = 0;
				if (i % 2 == 0) {
					x = 50 + 100*j;
				} else {
					x = 100*j;
				}
				Rectangle2D.Double r = new Rectangle2D.Double(x, y, 50, 50);
				g2d.fill(r);
			}
			
		}
		Line2D.Double edge_vertical = new Line2D.Double(400, 0, 400, 400);
		Line2D.Double edge_horizontal = new Line2D.Double(0, 400, 400, 400);
		g2d.draw(edge_vertical);
		g2d.draw(edge_horizontal);
		
		
		
		
	}
	public static void main(String[] args) {
		int w = 500;
		int h = 500;
		JFrame f = new JFrame();
		board b = new board(w, h);
		f.setSize(w, h);
		f.setTitle("Chess board");
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
