package jogo.logica.Memento;

import javafx.scene.shape.Circle;

public interface ICareTaker {
    void saveMemento();
    boolean undo(int flag, Circle[][] circulos);
}
