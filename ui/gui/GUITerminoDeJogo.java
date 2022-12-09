package jogo.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import jogo.logica.JogoObs;
import jogo.logica.JogoSituacao;
import jogo.logica.PropsID;
import jogo.ui.resources.MusicPlayer;
import jogo.ui.resources.cssManager;

import java.util.ArrayList;

public class GUITerminoDeJogo extends BorderPane {

    Label lbTitulo;
    Label lbSubtitulo;
    Circle[][] circulos;
    Button btnNovoJogo, btnColsutarHistorico, btnSair;
    Label lbJogoAConsultar;
    TextField tfjogo;
    Button btnConsultar;
    HBox PainelOpcoes;
    HBox PainelConsulta;
    VBox Painel;

    JogoObs jogoObs;

    public GUITerminoDeJogo(JogoObs g) {
        jogoObs = g;
        criarComponentes();
        disporVista();
        registarListeners();
        cssManager.setCSS(this, "mystyles.css");

        jogoObs.registaPropertyChangeListener(
                PropsID.PROP_ESTADO,
                (e) -> {
                    this.setVisible(jogoObs.getSituacao() == JogoSituacao.Termino_de_Jogo);

                    if(jogoObs.getSituacao() == JogoSituacao.Termino_de_Jogo) {
                        MusicPlayer.playMusic("Super Mario .mp3");
                        lbSubtitulo.setText(jogoObs.toStringTerminoDeJogo(circulos));
                    }
                });
    }

    private void criarComponentes() {
        lbTitulo = new Label("TERMINO DE JOGO");
        lbTitulo.setId("my-label");
        lbSubtitulo= new Label("");
        lbSubtitulo.setId("my-text");
        circulos = new Circle[6][7];
        btnNovoJogo = new Button("Novo Jogo");
        btnColsutarHistorico = new Button("Consulta Jogo Anterior");
        btnSair = new Button("Sair");
        lbJogoAConsultar = new Label("Jogo a Consultar: ");
        tfjogo = new TextField("");
        btnConsultar = new Button("Consultar");
        PainelOpcoes = new HBox();
        PainelConsulta = new HBox();
        Painel = new VBox();
        this.setId("pane-Comeco");
    }

    private void disporVista() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(580);
        grid.setMaxWidth(340);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(15, 0, 15, 0));
        grid.setId("my-grid");

        for(int i=0; i<6; i++)
        {
            for(int j=0; j < 7; j++)
            {
                circulos[i][j] = new Circle(20);
                grid.add(circulos[i][j], j, i);
            }
        }

        btnNovoJogo.setId("interativos");
        btnColsutarHistorico.setId("interativos");
        btnSair.setId("interativos");

        PainelOpcoes.setSpacing(10);
        PainelOpcoes.setAlignment(Pos.CENTER);
        PainelOpcoes.getChildren().addAll(btnNovoJogo, btnColsutarHistorico, btnSair);

        PainelConsulta.setSpacing(10);
        PainelConsulta.setAlignment(Pos.CENTER);
        PainelConsulta.getChildren().addAll(lbJogoAConsultar, tfjogo, btnConsultar);
        PainelConsulta.setVisible(false);

        Painel.setSpacing(30);
        Painel.setAlignment(Pos.CENTER);
        Painel.getChildren().addAll(lbTitulo, lbSubtitulo, grid, PainelOpcoes, PainelConsulta);
        setCenter(Painel);
    }

    private void registarListeners() {
        btnNovoJogo.setOnAction((e)->{
            jogoObs.IniciaNovoJogo();
        });

        btnColsutarHistorico.setOnAction((e)->{
            btnNovoJogo.setVisible(false);
            btnSair.setVisible(false);
            btnColsutarHistorico.setDisable(true);
            PainelConsulta.setVisible(true);

        });

        btnConsultar.setOnAction((e)->{
            PainelConsulta.setVisible(false);
            btnNovoJogo.setVisible(true);
            btnSair.setVisible(true);
            btnColsutarHistorico.setDisable(false);

            if(tfjogo.getText().matches("[1-5]") && jogoObs.VerificaIntegridadeJogoAconsultar(Integer.parseInt(tfjogo.getText()))) {
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(disporHistorico(Integer.parseInt(tfjogo.getText()), jogoObs.consultaHistoricoJogadas(Integer.parseInt(tfjogo.getText())), jogoObs.consultaHistoricoTabuleiro(Integer.parseInt(tfjogo.getText()))), 865, 650));
                stage2.setTitle("HISTÓRICO");
                stage2.show();
            }
            else{
                tfjogo.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING, "O jogo inserido ainda não foi jogado ou foi inserido um numero inválido de jogo a consultar [1-5]!");
                alert.show();
            }
        });

        btnSair.setOnAction((e)->{
            System.exit(0);
        });

        setOnKeyPressed((e)->{
            switch (e.getCode()){
                case ESCAPE -> {
                    System.exit(0);
                }
            }
        });
    }

    private BorderPane disporHistorico(int jogo, ArrayList<String> strings, ArrayList<Circle[][]> circles) {

        //Criação das Vbox
        VBox [] paineis = new VBox[strings.size()];

        //Criação das Jogadas
        Label [] jogadas = new Label[strings.size()];

        for(int s=0; s < strings.size(); s++){
            jogadas[s] = new Label(strings.get(s));
        }


        //Criação dos Tabuleiros
        GridPane [] tabuleiros = new GridPane[strings.size()];

        for(int h=0; h<strings.size(); h++){
            Circle [][] circulos = circles.get(h);
            tabuleiros[h] = new GridPane();
            tabuleiros[h].setAlignment(Pos.CENTER);
            tabuleiros[h].setMaxWidth(135);
            tabuleiros[h].setHgap(5);
            tabuleiros[h].setVgap(10);
            tabuleiros[h].setPadding(new Insets(10, 0, 10, 0));
            tabuleiros[h].setStyle("-fx-background-color: #0000b3;");

            for(int i=0; i<6; i++)
            {
                for(int j=0; j < 7; j++)
                {
                    tabuleiros[h].add(circulos[i][j], j, i);
                }
            }
        }


        //Adicionar ao Paineis os seus conteudos
        for(int u=0; u<strings.size(); u++){
            paineis[u] = new VBox();
            paineis[u].getChildren().addAll(jogadas[u], tabuleiros[u]);
            paineis[u].setAlignment(Pos.CENTER);
        }


        //Criação da Tabela Principal
        GridPane gridHistorico = new GridPane();
        gridHistorico.setAlignment(Pos.CENTER);
        gridHistorico.setHgap(30);
        gridHistorico.setVgap(15);

        //Adiconar à Tabela Principal
        int h=0, j=0;
        for(int i=0; i<strings.size(); i++){
            gridHistorico.add(paineis[i], j, h);

            if(j==0)
                j=1;
            else {
                j = 0;
                h++;
            }
        }

        Label lbTitulo = new Label("HISTÓRICO JOGO " + jogo);
        lbTitulo.setStyle("-fx-font: 24 arial;-fx-font-weight: bold;");
        VBox PainelPrincipal = new VBox();
        PainelPrincipal.setAlignment(Pos.CENTER);
        PainelPrincipal.getChildren().addAll(lbTitulo, gridHistorico);

        ScrollPane scrollPane = new ScrollPane(PainelPrincipal);
        BorderPane pane = new BorderPane(scrollPane);
        pane.setCenter(scrollPane);
        return pane;
    }
}
