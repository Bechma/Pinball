package pruebajuegocolisiones;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ball {
	private int diameter = 20;
	private int x;
	private int y = 0;
	private static final int mov = 3;
	private int xa = mov;
	private int ya = mov;
	private Game game;
	private int rebotes1[] = new int[4];
	private int rebotes2[] = new int[4];
	private int rebotes3[] = new int[4];

	public Ball(Game game) {
		this.game= game;
		this.x = (int) (Math.random() * 330);
	}
	
	public void setXY(int x, int y, int dm){
		this.x = x;
		this.y = y;
		this.diameter = dm;
	}

	void move() {
		if (x + xa < 0){
			xa = mov;
			game.puntuacion++;
		}
		else if (x + xa > game.getWidth() - diameter){
			xa = -mov;
			game.puntuacion++;
		}
		if (y + ya < 0){
			ya = mov;
			game.puntuacion++;
		}
		else if (y + ya > game.getHeight() - diameter){
			game.gameOver();
			game.puntuacion++;
		}
		if (collisionPalas()){
			ya = -mov;
		}
		else if(collisionEstorbos()){
			game.puntuacion+=2;
		}
		
		x = x + xa;
		y = y + ya;
	}

	private boolean collisionPalas() {
		return game.racketIzq.getBounds().intersects(getBounds())
				|| game.racketDer.getBounds().intersects(getBounds());
	}
	
	private boolean collisionEstorbos(){
		/*
		boolean estorbo1 = distanciaPuntos(game.estorbo1.x, game.estorbo1.y, game.estorbo1.diameter);
		boolean estorbo2 = distanciaPuntos(game.estorbo2.x, game.estorbo2.y, game.estorbo2.diameter);
		boolean estorbo3 = distanciaPuntos(game.estorbo3.x, game.estorbo3.y, game.estorbo3.diameter);
		return estorbo1 || estorbo2 || estorbo3;
		*/
		if(game.estorbo1.getBounds().intersects(getBounds())){
			asignarVelocidad(game.estorbo1, rebotes1);
			return true;
		}
		if(game.estorbo2.getBounds().intersects(getBounds())){
			asignarVelocidad(game.estorbo2, rebotes2);
			return true;
		}
		if(game.estorbo3.getBounds().intersects(getBounds())){
			asignarVelocidad(game.estorbo3, rebotes3);
			return true;
		}
		return false;
		/*
		return game.estorbo1.getBounds().intersects(getBounds())
				|| game.estorbo2.getBounds().intersects(getBounds())
				|| game.estorbo3.getBounds().intersects(getBounds());
		*/
	}
	
	
	
	private void asignarVelocidad(Ball b, int[] contadores){
		if(this.x <= b.x && this.y <= b.y){
			xa = -mov;
			ya = -mov;
			contadores[0]++;
			if(contadores[0] > 2)
				do{
					xa = (0.5 > Math.random()) ? mov : -mov;
					ya = (0.5 > Math.random()) ? mov : -mov;
				}while(xa == mov && ya == mov);	// No puede ser lo contrario de arriba
			contadores[1] = contadores[2] = contadores[3] = 0;
		}
		else if(this.x <= b.x && this.y >= b.y){
			xa = -mov;
			ya = mov;
			contadores[1]++;
			if(contadores[1] > 2)
				do{
					xa = (0.5 > Math.random()) ? mov : -mov;
					ya = (0.5 > Math.random()) ? mov : -mov;
				}while(xa == mov && ya == -mov);
			contadores[0] = contadores[2] = contadores[3] = 0;
		}
		else if(this.x >= b.x && this.y >= b.y){
			xa = mov;
			ya = mov;
			contadores[2]++;
			if(contadores[2] > 2)
				do{
					xa = (0.5 > Math.random()) ? mov : -mov;
					ya = (0.5 > Math.random()) ? mov : -mov;
				}while(xa == -mov && ya == -mov);
			contadores[0] = contadores[1] = contadores[3] = 0;
		}
		else{
			xa = mov;
			ya = -mov;
			contadores[3]++;
			if(contadores[3] > 2)
				do{
					xa = (0.5 > Math.random()) ? mov : -mov;
					ya = (0.5 > Math.random()) ? mov : -mov;
				}while(xa == -mov && ya == mov);
			contadores[0] = contadores[1] = contadores[2] = 0;
		}
	}
	
	/* 
	*	Colisiona si la distancia entre dos puntos es igual o menor que di+this.diametro
	*	True -> si colisiona
	*	False -> si no colisiona
	*/
	private boolean distanciaPuntos(int x, int y, int di){
		double distancia =  Math.sqrt((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y));
		return distancia <= (di+this.diameter);
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, diameter, diameter);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, diameter, diameter);
	}

}