package jogo.ui.resources;

import java.io.InputStream;

public class resources {
    private resources() {
    }

    public static InputStream getResourceFileAsStream(String name) {
        return resources.class.getResourceAsStream(name);
    }

    public static String getResourceFilename(String name) {
        return resources.class.getResource(name).toExternalForm();
    }
}
