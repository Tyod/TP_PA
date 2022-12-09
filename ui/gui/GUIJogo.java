package jogo.ui.gui;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import jogo.logica.JogoObs;

public class GUIJogo {

    private GUIEscolheModo vistaEscolheModo;
    private GUIJogoNoDecorrer vistaJogoNoDecorrer;
    private GUIMiniJogo vistaMiniJogo;
    private GUITerminoDeJogo vistaTerminoDeJogo;

    private StackPane rootPane;

    private JogoObs jogoObs;

    public GUIJogo(JogoObs g){
        jogoObs = g;
        criarComponentes();
        disporVista();
        registarListeners();
    }

    private void criarComponentes() {
        rootPane = new StackPane();
        vistaEscolheModo = new GUIEscolheModo(jogoObs);
        vistaJogoNoDecorrer = new GUIJogoNoDecorrer(jogoObs);
        vistaMiniJogo = new GUIMiniJogo(jogoObs);
        vistaTerminoDeJogo = new GUITerminoDeJogo(jogoObs);
    }

    private void disporVista() {
        vistaEscolheModo.setVisible(true);
        vistaJogoNoDecorrer.setVisible(false);
        vistaMiniJogo.setVisible(false);
        vistaTerminoDeJogo.setVisible(false);
        rootPane.getChildren().addAll(vistaEscolheModo, vistaJogoNoDecorrer, vistaMiniJogo, vistaTerminoDeJogo);
    }

    private void registarListeners() {
        //Declared just in Case
        //No need to insert here nothing
    }

    public Parent obtemRootPane(){
        return rootPane;
    }
}
