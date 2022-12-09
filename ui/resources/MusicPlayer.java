package jogo.ui.resources;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {
    static MediaPlayer mp;
    public static void playMusic(String name) {
        String path = resources.getResourceFilename("sounds/"+name);
        if (path == null)
            return;
        Media music = new Media(path);
        mp = new MediaPlayer(music);
        mp.setStartTime(Duration.ZERO);
        mp.setStopTime(music.getDuration());
        /*mp.setOnReady(() -> {
                mp.play();
        });*/
        mp.setAutoPlay(true);
    }
}
