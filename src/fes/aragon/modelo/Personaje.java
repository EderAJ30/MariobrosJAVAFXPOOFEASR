package fes.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Personaje extends ComponentesJuego {
	private int gravedad = 2;
	private boolean desciende = true;
	private boolean salto = false;
	public static Rectangle rectangulo;
	public static Rectangle rectDerecha;
	private Image imagen;
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean arriba = false;
	private boolean abajo = false;

	public Personaje(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		this.imagen = new Image(imagen);
		rectangulo = new Rectangle(x, y, this.imagen.getWidth(), this.imagen.getHeight());
		rectDerecha = new Rectangle(x+(this.imagen.getWidth()/2), y, this.imagen.getWidth()/2, this.imagen.getHeight()/2);
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagen, x, y);
		graficos.strokeRect(rectangulo.getX(), rectangulo.getY(), rectangulo.getWidth(), rectangulo.getHeight());
		graficos.setStroke(Color.RED);
		graficos.strokeRect(rectDerecha.getX(), rectDerecha.getY(), rectDerecha.getWidth(), rectDerecha.getHeight());
		graficos.setStroke(Color.BLACK);

	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		if (presiona) {
			switch (evento.getCode().toString()) {
			case "RIGHT":
				derecha = true;
				break;
			case "LEFT":
				izquierda = true;
				break;
			case "UP":
				arriba = true;
				break;
			case "DOWN":
				abajo = true;
				break;
			case "SPACE":
				y += velocidad * -15;
				x += 10;
				this.salto = true;
				break;

			}
		} else {
			switch (evento.getCode().toString()) {
			case "RIGHT":
				derecha = false;
				break;
			case "LEFT":
				izquierda = false;
				break;
			case "UP":
				arriba = false;
				break;
			case "DOWN":
				abajo = false;
				break;

			}
		}

	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		if (derecha) {
			x += velocidad;
		}
		if (izquierda) {
			x -= velocidad;
		}
		if (arriba) {
			y -= velocidad;
		}
		if (abajo) {
			y += velocidad;
		}
		this.rectangulo.setX(x);
		this.rectangulo.setY(y);
		this.rectDerecha.setX(x+this.imagen.getWidth()/2);
		this.rectDerecha.setY(y);

		//colision general
		for (CuadroTileMap cuadro : Escenario.cuadros) {
			if (this.rectangulo.getBoundsInLocal().intersects((cuadro.getRectangulo().getBoundsInLocal()))) {
				this.desciende = false;
				this.salto = false;
				break;
			} else {
				this.desciende = true;
			}
		}
		//colisión cuadro rojo
		for (CuadroTileMap cuadro : Escenario.cuadros) {
			if (this.rectDerecha.getBoundsInLocal().intersects((cuadro.getRectangulo().getBoundsInLocal()))) {				
				x-=velocidad;
				this.rectangulo.setX(x);				
				this.rectDerecha.setX(x+this.imagen.getWidth()/2);				
				break;
			} 
		}

		if (desciende == true || salto == true) {
			y += gravedad;
			this.rectangulo.setX(x);
			this.rectangulo.setY(y);
		}

	}

}
