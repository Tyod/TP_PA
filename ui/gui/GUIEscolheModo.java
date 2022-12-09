package jogo.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import jogo.logica.JogoObs;
import jogo.logica.JogoSituacao;
import jogo.logica.PropsID;
import jogo.ui.resources.cssManager;


public class GUIEscolheModo extends BorderPane {

    private JogoObs jogoObs;

    private Label lbTitulo;
    private Label lbInsereModo;
    private Label lbModo;
    private TextField tfModo;
    private Button btnAvanca;
    private Label lbInsereNames;
    private Label lbNameP1;
    private Label lbNameP2;
    private TextField tfPlayer1;
    private TextField tfPlayer2;
    private Button btnSubmete;
    private VBox Painel;

    public GUIEscolheModo(JogoObs g) {
        jogoObs = g;
        criarComponentes();
        disporVista();
        registarListeners();
        cssManager.setCSS(this, "mystyles.css");
        jogoObs.registaPropertyChangeListener(
                PropsID.PROP_ESTADO,
                (e) -> { this.setVisible(jogoObs.getSituacao() == JogoSituacao.Escolha_Modo);});
    }

    private void criarComponentes() {
        lbTitulo = new Label("CONFIGURAÇÃO DOS DADOS");
        lbInsereModo = new Label("INSIRA O MODO DE JOGO A JOGAR: ");
        lbModo = new Label("Modo de jogo: ");
        tfModo = new TextField();
        btnAvanca = new Button("Avançar");
        lbInsereNames = new Label("INSIRA O NOME DOS JOGADORES: ");
        lbNameP1 = new Label("Nome Jogador 1: ");
        lbNameP2 = new Label("Nome Jogador 2: ");
        tfPlayer1 = new TextField();
        tfPlayer2 = new TextField();
        btnSubmete = new Button("Submeter");
        Painel = new VBox();
        this.setId("pane-Comeco");
    }

    private void disporVista() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(lbInsereModo, 0, 0);
        lbInsereModo.setPadding(new Insets(0,0,5,0));
        grid.add(new Label("1 - Player vs Player"), 0, 1);
        grid.add(new Label("2 - Player vs Machine"), 0,2);
        grid.add(new Label("3 - Machine vs Machine"), 0, 3);
        grid.add(lbModo, 0, 4);
        grid.add(tfModo, 1, 4);
        lbModo.setPadding(new Insets(10, 0, 25 ,0));
        grid.add(btnAvanca, 2, 4);
        grid.add(lbInsereNames, 0,5);
        lbInsereNames.setPadding(new Insets(25, 0, 25,0));
        grid.add(lbNameP1, 0, 6);
        grid.add(tfPlayer1, 1,6);
        grid.add(lbNameP2, 0,7);
        grid.add(tfPlayer2, 1, 7);
        grid.add(btnSubmete, 2, 8);
        lbTitulo.setId("my-label");
        lbTitulo.setPadding(new Insets(0,0,40,0));

        lbInsereNames.setVisible(false);
        lbNameP1.setVisible(false);
        lbNameP2.setVisible(false);
        tfPlayer1.setVisible(false);
        tfPlayer2.setVisible(false);
        btnSubmete.setVisible(false);


        Painel.setAlignment(Pos.CENTER);
        Painel.getChildren().addAll(lbTitulo, grid);
        grid.setHgap(5);
        setCenter(Painel);
    }

    private void registarListeners() {
        btnAvanca.setOnAction((e)->{
            if(tfModo.getText().matches("[1-3]")) {

                btnAvanca.setDisable(true);
                tfModo.setDisable(true);
                lbInsereNames.setVisible(true);
                lbNameP1.setVisible(true);
                lbNameP2.setVisible(true);
                tfPlayer1.setVisible(true);
                tfPlayer2.setVisible(true);
                btnSubmete.setVisible(true);

                if(tfModo.getText().matches("2")){
                    tfPlayer2.setDisable(true);
                    tfPlayer2.setText("Machine1");
                }

                if(tfModo.getText().matches("3")){
                    tfPlayer1.setDisable(true);
                    tfPlayer1.setText("Machine1");
                    tfPlayer2.setDisable(true);
                    tfPlayer2.setText("Machine2");
                }
            }
            else
                tfModo.setText("");
        });

        btnSubmete.setOnAction((e)->{
            if(tfPlayer1.getText().matches("") || tfPlayer2.getText().matches("") || tfPlayer1.getText().matches(tfPlayer2.getText()) || tfPlayer1.getText().length() > 10 || tfPlayer2.getText().length() > 10) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nomes Inseridos são iguais ou não foram submetidos nomes ou são demasiado grandes!");
                alert.show();
            }
            else {
                jogoObs.indicaModo(Integer.parseInt(tfModo.getText()), tfPlayer1.getText(), tfPlayer2.getText());

                btnAvanca.setDisable(false);
                tfModo.setDisable(false);
                tfModo.setText("");
                lbInsereNames.setVisible(false);
                lbNameP1.setVisible(false);
                lbNameP2.setVisible(false);
                tfPlayer1.setVisible(false);
                tfPlayer1.setText("");
                tfPlayer1.setDisable(false);
                tfPlayer2.setVisible(false);
                tfPlayer2.setText("");
                tfPlayer2.setDisable(false);
                btnSubmete.setVisible(false);
            }
        });

        setOnKeyPressed((e)->{
            switch (e.getCode()){
                case ESCAPE -> {
                    System.exit(0);
                }
            }
        });
    }

}
