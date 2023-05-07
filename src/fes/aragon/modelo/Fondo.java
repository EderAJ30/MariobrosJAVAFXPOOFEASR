package fes.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Fondo extends ComponentesJuego {
	private int yy = -600;
	private Image imagen;
	private Image imagenDos;

	public Fondo(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		this.imagen=new Image(imagen);
		this.imagenDos=new Image(imagen.replace('2', '3'));
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		
		graficos.drawImage(imagen, x, y);
		graficos.drawImage(imagenDos, x, yy);

	}

	@Override
	public void teclado(KeyEvent evento,boolean presiona) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		y += velocidad;
		yy += velocidad;
		if (y == 600) {
			y = -600;
		}
		if (yy == 600) {
			yy = -600;
		}

	}

}
