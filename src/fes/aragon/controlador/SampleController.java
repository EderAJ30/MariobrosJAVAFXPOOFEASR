package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.CargaTileMap;
import fes.aragon.modelo.Escenario;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Personaje;
import fes.aragon.modelo.PersonajeAnimacion;
import fes.aragon.modelo.Piedra;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SampleController {
	private GraphicsContext graficos;
	private Scene escena;	
	private Thread hiloFondo;
	private Fondo fondo;
	private Personaje personaje;
	private PersonajeAnimacion personajeA;
	private Escenario mapa;
	private Piedra piedra;
	
	private CargaTileMap carga=null;

    @FXML
    private Canvas canvas=new Canvas(600,600);

    @FXML
    private Pane panel;

	
	private void componentesIniciar() {
		
		//escena=panel.getScene();
		graficos = canvas.getGraphicsContext2D();
		MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
		hiloFondo = new Thread(entrada);
		hiloFondo.start();
		carga=new CargaTileMap();
		fondo=new Fondo(0, 0,"/fes/aragon/recursos/fondo2.jpg" , 1);		
		personaje=new Personaje(300, 100,"/fes/aragon/recursos/navefinal.png" , 5);
		personajeA=new PersonajeAnimacion(10, 200,"/fes/aragon/recursos/mario_caminar.png" , 2);		
		mapa=new Escenario(0, 0, "/fes/aragon/recursos/cuadros.png", 0);
		piedra=new Piedra(50, 100, "/fes/aragon/recursos/piedra.png", 3);
		
	}
	public void iniciar() {
		componentesIniciar();
		pintar();
		eventosTeclado();		
		ciclo();
	}
	public void ciclo() {
		long tiempoInicio=System.nanoTime();
		AnimationTimer tiempo=new AnimationTimer() {			
			@Override
			public void handle(long tiempoActual) {
				double t=(tiempoActual-tiempoInicio)/1000000000.0;
				personajeA.setTiempo(t);
				calculosLogica();
				pintar();				
				
			}
		};
		tiempo.start();
	}


	private void pintar() {
		this.fondo.pintar(graficos);		
		this.mapa.pintar(graficos);
		this.personaje.pintar(graficos);
		this.personajeA.pintar(graficos);
		this.piedra.pintar(graficos);
	}
	private void calculosLogica() {
		this.fondo.logicaCalculos();
		this.personaje.logicaCalculos();
		this.personajeA.logicaCalculos();
		this.piedra.logicaCalculos();
	}
	private void eventosTeclado() {
		
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				// TODO Auto-generated method stub
				fondo.teclado(arg0,true);
				personaje.teclado(arg0,true);
				personajeA.teclado(arg0,true);
			}			
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				personaje.teclado(arg0,false);
				personajeA.teclado(arg0,false);
			}
			
		});
		
	}


	public void setEscena(Scene escena) {
		this.escena = escena;		
		
	}
	public void eventosVentana() {
		Stage escenario=(Stage)escena.getWindow();
		escenario.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				hiloFondo.stop();
			}
		});
	}
	

}
