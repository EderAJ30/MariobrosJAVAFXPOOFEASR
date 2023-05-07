package fes.aragon.modelo;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Piedra extends ComponentesJuego {
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

	public Piedra(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		this.imagen = new Image(imagen);
		rectangulo = new Rectangle(x, y, this.imagen.getWidth(), this.imagen.getHeight());
		rectDerecha = new Rectangle(x + (this.imagen.getWidth() / 2), y, this.imagen.getWidth() / 2,
				this.imagen.getHeight() / 2);
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagen, x, y);
		/*
		 * graficos.strokeRect(rectangulo.getX(), rectangulo.getY(),
		 * rectangulo.getWidth(), rectangulo.getHeight());
		 * graficos.setStroke(Color.RED); graficos.strokeRect(rectDerecha.getX(),
		 * rectDerecha.getY(), rectDerecha.getWidth(), rectDerecha.getHeight());
		 * graficos.setStroke(Color.BLACK);
		 */

	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		if (presiona) {
			switch (evento.getCode().toString()) {
			case "A":
				velocidad++;
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
		// si abajo es true -->baje
		// si abajo es false--->sube
		

		this.rectangulo.setY(y);
		this.rectDerecha.setY(y);

		if (this.rectangulo.getBoundsInLocal().intersects(Personaje.rectangulo.getBoundsInLocal())) {
			Random rd=new Random();
			if(rd.nextInt(15)<5) {
				x+=(rd.nextInt(5)+1)+velocidad;
				y+=(rd.nextInt(5)-1)+velocidad;	
			}else {
				x+=rd.nextInt(5)-1;
				y+=rd.nextInt(5)+1;
			}
			
			
			this.rectangulo.setX(x);
			this.rectDerecha.setX(x + (this.imagen.getWidth() / 2));
			
			this.rectangulo.setY(y);
			this.rectDerecha.setY(y + (this.imagen.getHeight() / 2));
			
		}

	}

}
