package pruebajuegocolisiones;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball;
	Ball estorbo1, estorbo2, estorbo3;
	Racket racketIzq, racketDer;
	int puntuacion;

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racketIzq.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racketIzq.keyPressed(e);
			}
		});
		
		racketIzq = new Racket(0, 400, 115, 450, this);
		racketDer = new Racket(320, 400, 205, 450, this);
		ball = new Ball(this);
		estorbo1 = new Ball(this);
		estorbo2 = new Ball(this);
		estorbo3 = new Ball(this);
		
		estorbo1.setXY(90, 150, 30);
		estorbo2.setXY(150, 300, 40);
		estorbo3.setXY(200, 90, 35);
		
		puntuacion = 0;
		
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racketIzq.moveIzq();
		racketDer.moveDer();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racketIzq.paint(g2d);
		racketDer.paint(g2d);
		
		estorbo1.paint(g2d);
		estorbo2.paint(g2d);
		estorbo3.paint(g2d);
		
		g.drawString("Puntuación: " + puntuacion, 100, 15);
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(340, 570);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
