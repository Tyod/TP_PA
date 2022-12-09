package jogo.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jogo.logica.JogoObs;
import jogo.logica.JogoSituacao;
import jogo.logica.PropsID;
import jogo.ui.resources.cssManager;



public class GUIMiniJogo extends BorderPane {

    int n=0;
    Label lbTitulo;
    Label lbBreveResumo;
    Label lbDataTemp;
    TextField tfInputDataCalculos;
    TextField tfInputDataPalavras;
    Button btnComecar;
    Button btnIniciaJogoCalculos;
    Button btnIniciaJogoPalavras;
    Button btnResponderCalculos;
    Button btnResponderPalavras;
    HBox PainelRespostaCalculos;
    HBox PainelRespostaPalavras;
    VBox Painel;

    private JogoObs jogoObs;

    public GUIMiniJogo(JogoObs g) {
        jogoObs = g;
        criarComponentes();
        disporVista(2, 1);
        registarListeners();
        cssManager.setCSS(this, "mystyles.css");

        jogoObs.registaPropertyChangeListener(
                PropsID.PROP_ESTADO,
                (e) -> {
                    this.setVisible(jogoObs.getSituacao() == JogoSituacao.Mini_jogo);
                });
    }

    private void criarComponentes() {
        lbTitulo = new Label("MINI JOGO");
        lbTitulo.setId("my-label");
        lbBreveResumo = new Label("\nBem vindo aos MiniJogos!\n\nAqui voce terá a oportunidade de ganhar uma peça especial. Serão-lhe propostos um de dois\ntipos de MiniJogos, o jogo 'Calculos' ou jogo 'Palavras', se ganhar o mesmos irá ganhar a\npeça e manterá a sua vez de jogar, no caso de perder não irá receber a peça especial e\nnperderá a sua vez de jogar!\n\n\t\t\t\t\t\t\t\t   Boa sorte!\n\n\n");
        lbBreveResumo.setId("my-text");
        btnComecar = new Button("Começar a jogar!");
        lbDataTemp = new Label();
        lbDataTemp.setId("my-text");
        tfInputDataCalculos = new TextField();
        tfInputDataPalavras = new TextField();
        btnIniciaJogoCalculos = new Button("Iniciar Jogo");
        btnIniciaJogoPalavras = new Button("Iniciar Jogo");
        btnResponderCalculos = new Button("Responder");
        btnResponderPalavras = new Button("Responder");
        PainelRespostaCalculos = new HBox();
        PainelRespostaPalavras = new HBox();
        Painel = new VBox();
    }

    private void disporVista(int jogo, int interruptor) {

        if(interruptor == 1)
            setCenter(Painel);

        if(jogo == 2){
            lbTitulo.setPadding(new Insets(0, 0, 25, 0));
            Painel.getChildren().addAll(lbTitulo, lbBreveResumo, btnComecar);
            Painel.setAlignment(Pos.CENTER);
            Painel.setSpacing(30);
        }

        if(jogo == 1){
            this.setId("pane-Calculos");

            Painel.getChildren().remove(btnComecar);

            lbTitulo.setText("MINI JOGO CALCULOS");
            lbBreveResumo.setText("Breve Resumo: \n\nSerão propostos cálculos matemáticos simples usando os operadores básicos (+,-,/,x) e dois inteiros positivos\n" +
                    "(de 1 ou 2 dígitos cada um). Os números e os operadores a usar são sorteados. Caso o jogador acerte em 5\n" +
                    "cálculos em menos de 30 segundos ganha um peça especial; \n\n\n\n");

            btnResponderPalavras.setVisible(false);

            PainelRespostaCalculos.getChildren().addAll(lbDataTemp, tfInputDataCalculos, btnResponderCalculos);
            PainelRespostaCalculos.setVisible(false);
            PainelRespostaCalculos.setSpacing(10);
            PainelRespostaCalculos.setAlignment(Pos.CENTER);


            Painel.getChildren().addAll(lbTitulo, lbBreveResumo, btnIniciaJogoCalculos, PainelRespostaCalculos);
            Painel.setAlignment(Pos.CENTER);
        }

        if(jogo == 0){
            this.setId("pane-Palavras");

            Painel.getChildren().remove(btnComecar);

            lbTitulo.setText("MINI JOGO PALAVRAS");
            lbBreveResumo.setText("Breve Resumo: \n\nSerão apresentadas 5 palavras escolhidas aleatoriamente, cada uma de 5 ou\n" +
                    "mais letras. O jogador deve escrever essas palavras e será avaliada a sua\n" +
                    "rapidez. O jogador ganha a peça especial se digitar corretamente as palavras\n" +
                    "num período inferior ou igual ao número de segundos que corresponde a metade \n" +
                    "do número de caracteres apresentados.\n\n\n\n");



            PainelRespostaPalavras.getChildren().addAll(lbDataTemp, tfInputDataPalavras, btnResponderPalavras);
            PainelRespostaPalavras.setVisible(false);
            PainelRespostaPalavras.setSpacing(10);
            PainelRespostaPalavras.setAlignment(Pos.CENTER);

            Painel.getChildren().addAll(lbTitulo, lbBreveResumo, btnIniciaJogoPalavras, PainelRespostaPalavras);
            Painel.setAlignment(Pos.CENTER);
        }
    }

    private void registarListeners() {
        btnComecar.setOnAction((e)->{
            Painel.getChildren().clear();
            disporVista(jogoObs.SorteiaMiniJogo(), 0);
        });

        btnIniciaJogoPalavras.setOnAction((e)->{
            btnIniciaJogoPalavras.setDisable(true);
            btnResponderCalculos.setVisible(false);
            btnResponderPalavras.setVisible(true);
            PainelRespostaPalavras.setVisible(true);
            jogoObs.IniciaTimerMiniJogoPalavras();
            jogoObs.GeraPalavras();
            lbDataTemp.setText(jogoObs.toStringMiniJogoPalavras(n));
        });

        btnIniciaJogoCalculos.setOnAction((e)->{
            btnIniciaJogoCalculos.setDisable(true);
            btnResponderCalculos.setVisible(true);
            btnResponderPalavras.setVisible(false);
            PainelRespostaCalculos.setVisible(true);
            jogoObs.IniciaTimerMiniJogoCalculos();
            jogoObs.GeraCalculos();
            lbDataTemp.setText(jogoObs.toStringMiniJogoCalculos());
        });

        btnResponderCalculos.setOnAction((e)->{

                if (!jogoObs.AvaliaResposta(Integer.parseInt(tfInputDataCalculos.getText()))) {
                    jogoObs.GeraCalculos();
                    lbDataTemp.setText(jogoObs.toStringMiniJogoCalculos());
                    tfInputDataCalculos.setText("");
                } else {
                    jogoObs.VerificaVitoriaMiniJogoCalculos();
                    Painel.getChildren().clear();
                    PainelRespostaCalculos.getChildren().clear();
                    btnIniciaJogoCalculos.setDisable(false);
                    disporVista(2, 0);
                    this.setId("empty");
                }

        });

        btnResponderPalavras.setOnAction((e)->{

                if(jogoObs.VerificaResposta(tfInputDataPalavras.getText(), n)){
                    n++;

                    if(n == 5) {
                        n=0;
                        jogoObs.VerificaVitoriaMiniJogoPalavras();
                        Painel.getChildren().clear();
                        PainelRespostaPalavras.getChildren().clear();
                        btnIniciaJogoPalavras.setDisable(false);
                        disporVista(2, 0);
                        this.setId("empty");
                    }

                    lbDataTemp.setText(jogoObs.toStringMiniJogoPalavras(n));
                }

            tfInputDataPalavras.setText("");
        });

        setOnKeyPressed((e)->{
            switch (e.getCode()) {
                case ENTER -> {
                    if(btnResponderPalavras.isVisible()) {
                        if(jogoObs.VerificaResposta(tfInputDataPalavras.getText(), n)){
                            n++;

                            if(n == 5) {
                                n=0;
                                jogoObs.VerificaVitoriaMiniJogoPalavras();
                                Painel.getChildren().clear();
                                PainelRespostaPalavras.getChildren().clear();
                                btnIniciaJogoPalavras.setDisable(false);
                                disporVista(2, 0);
                                this.setId("empty");
                            }

                            lbDataTemp.setText(jogoObs.toStringMiniJogoPalavras(n));
                        }

                        tfInputDataPalavras.setText("");
                    }
                    else{
                        if(!jogoObs.AvaliaResposta(Integer.parseInt(tfInputDataCalculos.getText()))) {
                            jogoObs.GeraCalculos();
                            lbDataTemp.setText(jogoObs.toStringMiniJogoCalculos());
                            tfInputDataCalculos.setText("");
                        }
                        else {
                            jogoObs.VerificaVitoriaMiniJogoCalculos();
                            Painel.getChildren().clear();
                            PainelRespostaCalculos.getChildren().clear();
                            btnIniciaJogoCalculos.setDisable(false);
                            disporVista(2, 0);
                            this.setId("empty");
                        }
                    }
                }

                case ESCAPE -> {
                    System.exit(0);
                }

            }
        });
    }
}
