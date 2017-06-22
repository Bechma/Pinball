package pruebajuegocolisiones;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

public class Racket {
	static final int WIDTH = 60;
	static final int HEIGHT = 10;
	int xp1, xp2;
	int yp1, yp2;
	private final int INFERIOR, SUPERIOR;
	private Game game;
	private static final int mov = 4;
	private static boolean accIzq = false, accDer = false;

	public Racket(int xp1, int yp1, int xp2, int yp2, Game game) {
		this.xp1 = xp1;
		this.xp2 = xp2;
		this.yp1 = yp1;
		this.yp2 = yp2;
		this.game = game;
		INFERIOR = yp2;
		SUPERIOR = yp2-100;
	}

	
	
	public void moveIzq() {
		if(accIzq){
			if(yp2 > SUPERIOR){			// Limite superior pad
				yp2 -= mov;
			}
		}
		else if( yp2 != INFERIOR)	// Limite inferior pad
			yp2 += mov;
		
	}
	public void moveDer() {
		if(accDer){
			if(yp2 > SUPERIOR){			// Limite superior pad
				yp2 -= mov;
			}
		}
		else if( yp2 != INFERIOR)	// Limite inferior pad
			yp2 += mov;
		
	}

	public void paint(Graphics2D g) {
	//	g.fillRect(x, Y, WIDTH, HEIGHT);
		g.drawLine(xp1, yp1, xp2, yp2);
	}

	public void keyReleased(KeyEvent e) {
		accIzq = false;
		accDer = false;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)	// PAD IZQ
			accIzq = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)	// PAD DER
			accDer = true;
	}

	// tiene que ser Line
	public Line2D getBounds(){
		return new Line2D.Float(xp1, yp1, xp2, yp2);
	}

	// tonteria
	public int getTopY() {
		return (yp1 > yp2) ? yp2 : yp1;
	}

}
