package jogo.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jogo.logica.JogoObs;
import jogo.logica.JogoSituacao;
import jogo.logica.PropsID;
import jogo.ui.resources.cssManager;


public class GUIJogoNoDecorrer extends BorderPane {

    Label lbAjuda;
    Label lbGravacao;
    Button btnGravar, btnLer;
    Label lbTitulo;
    Circle [][] circulos;
    Label lbPlayers;
    Button btnJogarPNomal, btnJogarPEspecial, btnRetrocederJogada, btnColocarN, btnColocarE, btnAvancar;
    Label lbJogada;
    TextField tfJogada;
    Label lbOportunidadeMiniJogo;
    Button btnSminiJogo, btnNminiJogo;
    VBox Painel;
    HBox PainelGravação;

    private JogoObs jogoObs;
    
    public GUIJogoNoDecorrer(JogoObs g) {
        jogoObs = g;
        criarComponentes();
        disporVista();
        registarListeners();
        cssManager.setCSS(this, "mystyles.css");

        jogoObs.registaPropertyChangeListener(
                PropsID.PROP_ESTADO,
                (e) -> {
                    this.setVisible(jogoObs.getSituacao() == JogoSituacao.Jogo_Decorrer);

                    if(jogoObs.getSituacao() == JogoSituacao.Jogo_Decorrer){
                        if(jogoObs.VerificaMiniJogo()){
                            btnJogarPNomal.setVisible(false);
                            btnJogarPEspecial.setVisible(false);
                            btnRetrocederJogada.setVisible(false);
                            lbOportunidadeMiniJogo.setVisible(true);
                            btnSminiJogo.setVisible(true);
                            btnNminiJogo.setVisible(true);
                        }
                    }


                    if(jogoObs.getSituacao() == JogoSituacao.Termino_de_Jogo){
                        this.getChildren().clear();
                        Painel.getChildren().clear();
                        PainelGravação.getChildren().clear();

                        for(int i=0; i<6; i++)
                        {
                            for(int j=0; j < 7; j++)
                            {
                                circulos[i][j].setFill(Color.WHITE);
                            }
                        }

                        btnJogarPNomal.setVisible(true);
                        btnJogarPEspecial.setVisible(true);
                        btnRetrocederJogada.setVisible(true);

                        disporVista();
                    }

                    if(jogoObs.getSituacao() == JogoSituacao.Jogo_Decorrer) {

                        AtualizaLabel();

                        if (jogoObs.getModo() == 3) {
                            btnJogarPNomal.setVisible(false);
                            btnJogarPEspecial.setVisible(false);
                            btnRetrocederJogada.setVisible(false);
                            btnAvancar.setVisible(true);
                        }
                    }
                });
    }

    private void criarComponentes() {
        lbAjuda = new Label(" PRESSIONE 'H' PARA AJUDA\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        lbGravacao = new Label("Ferramentas de gravação: ");
        btnGravar = new Button("Gravar");
        btnLer = new Button("Ler");
        lbTitulo = new Label("JOGO QUATRO EM LINHA");
        circulos = new Circle[6][7];
        lbPlayers = new Label("");
        lbPlayers.setStyle("-fx-border-width: 6px; -fx-border-color: yellow;  -fx-border-radius: 18 18 18 18;");
        btnJogarPNomal = new Button("Jogar Peça Normal");
        btnJogarPEspecial = new Button("Jogar Peça Especial");
        btnAvancar = new Button("Executar Jogada");
        btnRetrocederJogada = new Button("Retroceder ronda");
        btnColocarN = new Button("Colocar Peça");
        btnColocarE = new Button("Colocar Peça");
        lbJogada = new Label("Coluna a jogar(1-7): ");
        tfJogada = new TextField();
        lbOportunidadeMiniJogo = new Label("Quer Jogar MiniJogo?");
        btnSminiJogo = new Button("Sim, Quero jogar!");
        btnNminiJogo = new Button("Não, Agora não!");
        Painel = new VBox();
        PainelGravação = new HBox();
    }

    private void disporVista() {

        this.setId("pane-Comeco");
        lbAjuda.setAlignment(Pos.TOP_LEFT);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(580);
        grid.setMaxWidth(330);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 0, 10, 0));
        grid.setId("my-grid");

        for(int i=0; i<6; i++)
        {
            for(int j=0; j < 7; j++)
            {
                circulos[i][j] = new Circle(20, Color.WHITE);
                grid.add(circulos[i][j], j, i);
            }
        }

        //circulos[3][5].setFill(Color.BLACK);
        PainelGravação.getChildren().addAll(lbAjuda, lbGravacao, btnGravar, btnLer);
        PainelGravação.setAlignment(Pos.TOP_RIGHT);
        PainelGravação.setSpacing(3);
        PainelGravação.setPadding(new Insets(10, 10,0,0));



        lbTitulo.setId("my-label");
        lbTitulo.setPadding(new Insets(0,0, 0, 0));

        GridPane gridJogar = new GridPane();
        gridJogar.setAlignment(Pos.CENTER);
        gridJogar.setPadding(new Insets(0, 0, 0, 0));
        gridJogar.setHgap(10);
        gridJogar.setVgap(20);

        lbPlayers.setPadding(new Insets(-60, 10 , -5, 10));

        gridJogar.add(btnJogarPNomal, 0, 0);
        gridJogar.add(btnJogarPEspecial, 1, 0);
        gridJogar.add(btnAvancar, 1,0);
        gridJogar.add(btnRetrocederJogada, 2, 0);
        gridJogar.add(lbJogada, 0, 1);
        gridJogar.add(tfJogada, 1,1);
        tfJogada.setPrefSize(1, 20);
        gridJogar.add(btnColocarN, 2, 1);
        gridJogar.add(btnColocarE, 2,1);
        gridJogar.add(lbOportunidadeMiniJogo, 1,0);
        gridJogar.add(btnNminiJogo, 2,1);
        gridJogar.add(btnSminiJogo, 0, 1);

        btnJogarPNomal.setId("interativos");
        btnJogarPEspecial.setId("interativos");
        btnRetrocederJogada.setId("interativos");

        lbJogada.setVisible(false);
        tfJogada.setVisible(false);
        btnColocarN.setVisible(false);
        btnColocarE.setVisible(false);
        btnAvancar.setVisible(false);
        lbOportunidadeMiniJogo.setVisible(false);
        btnSminiJogo.setVisible(false);
        btnNminiJogo.setVisible(false);

        Painel.getChildren().addAll(lbTitulo, grid, lbPlayers, gridJogar);
        Painel.setAlignment(Pos.CENTER);
        Painel.setSpacing(20);

        setTop(PainelGravação);
        setCenter(Painel);
    }

    private void registarListeners() {
        btnJogarPNomal.setOnAction((e)->{
            btnJogarPNomal.setDisable(true);
            btnJogarPEspecial.setVisible(false);
            btnRetrocederJogada.setVisible(false);
            lbJogada.setVisible(true);
            tfJogada.setVisible(true);
            btnColocarN.setVisible(true);

            if(contaPecas()%2 ==0)
                jogoObs.SaveMemento();

        });

        btnJogarPEspecial.setOnAction((e)->{
            btnJogarPEspecial.setDisable(true);
            btnJogarPNomal.setVisible(false);
            btnRetrocederJogada.setVisible(false);
            lbJogada.setVisible(true);
            tfJogada.setVisible(true);
            btnColocarE.setVisible(true);
        });

        btnColocarN.setOnAction((e)->{

            btnJogarPNomal.setVisible(true);
            btnJogarPNomal.setDisable(false);
            btnJogarPEspecial.setVisible(true);
            btnJogarPEspecial.setDisable(false);
            btnRetrocederJogada.setVisible(true);
            lbJogada.setVisible(false);
            tfJogada.setVisible(false);
            btnColocarN.setVisible(false);

            if(tfJogada.getText().matches("[1-7]")){
                jogoObs.ColocaPeça(Integer.parseInt(tfJogada.getText()), circulos);
                AtualizaLabel();
                jogoObs.VerificaEmpate();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Foi inserido um numero inválido correspondente à coluna a jogar ou não foi inserido uma coluna a jogar!");
                alert.show();
            }

            if(jogoObs.VerificaMiniJogo()){
                btnJogarPNomal.setVisible(false);
                btnJogarPEspecial.setVisible(false);
                btnRetrocederJogada.setVisible(false);
                lbOportunidadeMiniJogo.setVisible(true);
                btnSminiJogo.setVisible(true);
                btnNminiJogo.setVisible(true);
            }
        });

        btnColocarE.setOnAction((e)->{
            btnJogarPNomal.setVisible(true);
            btnJogarPNomal.setDisable(false);
            btnJogarPEspecial.setVisible(true);
            btnJogarPEspecial.setDisable(false);
            btnRetrocederJogada.setVisible(true);
            lbJogada.setVisible(false);
            tfJogada.setVisible(false);
            btnColocarE.setVisible(false);

            if(tfJogada.getText().matches("[1-7]")){
                if(!jogoObs.ColocaPeçaEspecial(Integer.parseInt(tfJogada.getText()), circulos)){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Não possui peças especiais para jogar!");
                    alert.show();
                }
                AtualizaLabel();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Foi inserido um numero inválido correspondente à coluna a jogar ou não foi inserido uma coluna a jogar!");
                alert.show();
            }

            if(jogoObs.VerificaMiniJogo()){
                btnJogarPNomal.setVisible(false);
                btnJogarPEspecial.setVisible(false);
                btnRetrocederJogada.setVisible(false);
                lbOportunidadeMiniJogo.setVisible(true);
                btnSminiJogo.setVisible(true);
                btnNminiJogo.setVisible(true);
            }
        });

        btnAvancar.setOnAction((e)->{
            jogoObs.ColocaPeça(0, circulos);
            jogoObs.VerificaEmpate();
            AtualizaLabel();
        });

        btnGravar.setOnAction((e)->{
            jogoObs.GuardaJogo();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "O JOGO FOI GRAVADO!");
            alert.show();
        });

        btnLer.setOnAction((e)->{
            if(!jogoObs.CarregaJogo(circulos)){
                Alert alert = new Alert(Alert.AlertType.WARNING, "NÃO FOI POSSÍVEL CARREGAR O JOGO!");
                alert.show();
            }
            else {
                lbPlayers.setText("\n\n" + jogoObs.toStringPlayers());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "O JOGO CARREGADO!");
                alert.show();
            }
        });


        btnRetrocederJogada.setOnAction((e)->{
            if(!jogoObs.Undo(contaPecas(), circulos)){
                System.out.println("PECAS: " + contaPecas());
                Alert alert = new Alert(Alert.AlertType.WARNING, "Não há mais jogadas para retroceder, não há peças suficences em jogo que seja util retroceder, ou o jogador já não possui mais fichas para retroceder!");
                alert.show();
            }
            else {
                AtualizaLabel();
            }
        });

        btnSminiJogo.setOnAction((e)->{
            jogoObs.executaOpcao(1);

            btnJogarPNomal.setVisible(true);
            btnJogarPEspecial.setVisible(true);
            btnRetrocederJogada.setVisible(true);
            lbOportunidadeMiniJogo.setVisible(false);
            btnSminiJogo.setVisible(false);
            btnNminiJogo.setVisible(false);
        });

        btnNminiJogo.setOnAction((e)->{

            jogoObs.executaOpcao(0);
            AtualizaLabel();

            btnJogarPNomal.setVisible(true);
            btnJogarPEspecial.setVisible(true);
            btnRetrocederJogada.setVisible(true);
            lbOportunidadeMiniJogo.setVisible(false);
            btnSminiJogo.setVisible(false);
            btnNminiJogo.setVisible(false);
        });

        setOnKeyPressed((e)->{
            switch (e.getCode()){
                case G -> {
                    jogoObs.GuardaJogo();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "O JOGO FOI GRAVADO!");
                    alert.show();
                }

                case ESCAPE -> {
                    System.exit(0);
                }

                case HOME -> {
                    RegistaListenrsDebug();
                }

                case H ->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "COMANDOS:\n\nTecla G - Gravar Jogo\nTecla Esq - Sair");
                    alert.setHeaderText("ATALHOS");
                    alert.show();
                }
            }
        });
    }

    private void RegistaListenrsDebug(){
        setOnKeyPressed((e)->{
            switch (e.getCode()){
                case DIGIT1 -> {
                    jogoObs.ColocaPeça(1, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT2 -> {
                    jogoObs.ColocaPeça(2, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT3 -> {
                    jogoObs.ColocaPeça(3, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT4 -> {
                    jogoObs.ColocaPeça(4, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT5 -> {
                    jogoObs.ColocaPeça(5, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT6 -> {
                    jogoObs.ColocaPeça(6, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case DIGIT7 -> {
                    jogoObs.ColocaPeça(7, circulos);
                    AtualizaLabel();
                    jogoObs.VerificaEmpate();

                    if(jogoObs.VerificaMiniJogo()){
                        btnJogarPNomal.setVisible(false);
                        btnJogarPEspecial.setVisible(false);
                        btnRetrocederJogada.setVisible(false);
                        lbOportunidadeMiniJogo.setVisible(true);
                        btnSminiJogo.setVisible(true);
                        btnNminiJogo.setVisible(true);
                    }
                }

                case G -> {
                    jogoObs.GuardaJogo();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "O JOGO FOI GRAVADO!");
                    alert.show();
                }

                case L -> {
                    if(!jogoObs.CarregaJogo(circulos)){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "NÃO FOI POSSÍVEL CARREGAR O JOGO!");
                        alert.show();
                    }
                    else {
                        lbPlayers.setText("\n\n" + jogoObs.toStringPlayers());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "O JOGO CARREGADO!");
                        alert.show();
                    }
                }



                case ESCAPE -> {
                    System.exit(0);
                }


                case H ->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "COMANDOS:\n\nTecla 1 - Jogar Peça Normal na coluna 1\nTecla 2 - Jogar Peça Normal na coluna 2\nTecla 3 - Jogar Peça Normal no coluna 3\nTecla 4 - Jogar Peça Normal no coluna 4\nTecla 5 - Jogar Peça Normal no coluna 5\nTecla 6 - Jogar Peça Normal no coluna 6\nTecla 7 - Jogar Peça Normal no coluna 7\nTecla G - Gravar Jogo\nTecla L - Ler Jogo Gravado\nTecla Esq - Sair");
                    alert.setHeaderText("ATALHOS");
                    alert.show();
                }
            }
        });
    }

    private void AtualizaLabel(){
        lbPlayers.setText("\n\n" + jogoObs.toStringPlayers());

        if(jogoObs.getJogadorAtivo()==0)
            lbPlayers.setStyle("-fx-border-width: 6px; -fx-border-color: yellow;  -fx-border-radius: 18 18 18 18;");
        else
            lbPlayers.setStyle("-fx-border-width: 6px; -fx-border-color: red;  -fx-border-radius: 18 18 18 18;");
    }

    private int contaPecas(){
        int i, j, pecas=0;

        for(i=0; i<6; i++)
        {
            for(j=0; j < 7; j++)
            {
                if(circulos[i][j].getFill() == Color.YELLOW)
                    pecas++;
                if(circulos[i][j].getFill() == Color.RED)
                    pecas++;
            }
        }

        return pecas;
    }
}
