package jogo.ui.resources;

import javafx.scene.Parent;

public class cssManager {
    private cssManager() {}

    public static void setCSS(Parent parent, String name) {
        parent.getStylesheets().add(resources.getResourceFilename("css/"+name));
    }
}
