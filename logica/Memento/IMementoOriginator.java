package jogo.logica.Memento;

import javafx.scene.shape.Circle;
import jogo.logica.dados.JogoDados;

import java.io.IOException;

public interface IMementoOriginator {
    Memento getMemento() throws IOException;
    void setMemento(Memento m, int flag, int tirar, Circle[][] circulos) throws IOException, ClassNotFoundException;
}
