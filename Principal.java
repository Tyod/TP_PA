package jogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.logica.Jogo;
import jogo.logica.JogoObs;
import jogo.ui.gui.GUIJogo;
import jogo.ui.texto.VistaJogo;

public class Principal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        JogoObs jogoObs = new JogoObs();
        GUIJogo GUI = new GUIJogo(jogoObs);
        stage.setTitle("QUATRO EM LINHA");
        stage.setScene(new Scene(GUI.obtemRootPane(), 900, 650));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        //Jogo jogo = new Jogo();
        //VistaJogo UIjogo = new VistaJogo(jogo);
        //UIjogo.Run();
        launch(args);
    }
}
