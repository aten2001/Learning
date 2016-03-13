package draw.learning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Skeleton extends JFrame{

	static class Surface extends JPanel{

		private void drawPoint(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.blue);

			Dimension size = getSize();
			Insets insets = getInsets();

			int w = size.width - (insets.left + insets.right);
			int h = size.height- (insets.bottom + insets.top);

			Random r = new Random();

			for(int i=0; i<1000; i++){
				int x = Math.abs(r.nextInt()) % w;
				int y = Math.abs(r.nextInt()) % h;
				g2d.drawLine(x, y, x, y);
			}
		}
		private void doDrawing(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawString("Java 2D", 50, 50);
		}

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//doDrawing(g);
			drawPoint(g);
		}
	}
	public Skeleton(){
		initUI();
	}

	private void initUI(){
		setTitle("Hello World");

		add(new Surface());

		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Skeleton sk = new Skeleton();
				sk.setVisible(true);
			}
		});
	}
}
