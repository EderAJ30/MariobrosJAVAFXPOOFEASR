package fes.aragon.modelo;

import java.util.HashMap;

public class CargaTileMap {
	public static final HashMap<Integer, CuadroTileMap> tileMap = new HashMap<>();
	public static final HashMap<Integer, CuadroTileMap> personajeCaminar = new HashMap<>();

	public CargaTileMap() {
		
		this.cargarEscena();
		this.cargarPersonaje();
		
	}

	private void cargarEscena() {
		//valores de la imagen cuadros.png
		int limiteX = (int) Math.ceil(893 / 52);
		int limiteY = (int) Math.ceil( 1816/ 52);
		int celda=0;
		int cordXDentroImagen=7;
		int cordYDentroImagen=5;
		int anchoDentroImagen=52;
		int altoDentroImagen=52;
		int anchoVentana=52;
		int altoVentana=52;
		
		
		for (int fila = 0; fila <limiteY; fila++) {
			for (int columna = 0; columna <limiteX; columna++) {				
				celda++;
				CuadroTileMap c=new CuadroTileMap(cordXDentroImagen,cordYDentroImagen,
						anchoDentroImagen,altoDentroImagen,anchoVentana,altoVentana);
				CargaTileMap.tileMap.put(celda, c);	
				
				cordXDentroImagen+=anchoDentroImagen;								
			}			
			cordXDentroImagen=7;
			cordYDentroImagen+=altoDentroImagen;
		}
	}
	private void cargarPersonaje() {
		//valores de la imagen mario_caminar.png
		int limiteX = (int) Math.ceil(242 / 64);
		int limiteY = (int) Math.ceil( 122/ 122);
		int celda=0;
		int cordXDentroImagen=0;
		int cordYDentroImagen=0;
		int anchoDentroImagen=60;
		int altoDentroImagen=122;
		int anchoVentana=64;
		int altoVentana=122;		
		
		for (int fila = 0; fila <limiteY; fila++) {
			for (int columna = 0; columna <=limiteX; columna++) {				
				celda++;
				CuadroTileMap c=new CuadroTileMap(cordXDentroImagen,cordYDentroImagen,
						anchoDentroImagen,altoDentroImagen,anchoVentana,altoVentana);
				CargaTileMap.personajeCaminar.put(celda, c);	
				
				cordXDentroImagen+=anchoDentroImagen;								
			}			
			cordXDentroImagen=0;
			cordYDentroImagen+=altoDentroImagen;
		}
	}

}
