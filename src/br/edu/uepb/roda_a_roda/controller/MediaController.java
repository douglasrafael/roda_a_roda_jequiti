package br.edu.uepb.roda_a_roda.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * Fornece métodos para manipulação de audio
 *
 * @author Douglas Rafael
 */
public class MediaController {

    private final String PATH = "/br/edu/uepb/roda_a_roda/assets/audio/";

    private static MediaController instance;

    private AudioClip audioClip;
    private Map<String, MediaPlayer> players;
    private Map<String, AudioClip> audioClips;

    /**
     * Retorna instancia única da classe
     *
     * @return
     */
    public static MediaController getInstance() {
        if (instance == null) {
            instance = new MediaController();
        }
        return instance;
    }

    /**
     * Construtor
     */
    public MediaController() {
        if (players == null) {
            players = new HashMap<>();
        }

        if (audioClips == null) {
            audioClips = new HashMap<>();
        }
    }

    /**
     * Cria um novo MediaPlayer ou retorna caso ele já exista.
     *
     * @param mediaSource
     * @return MediaPlayer
     */
    public MediaPlayer createMediaPlayer(String mediaSource) {
        // Veriffica se a música já foi adicionada na playlist players
        if (!players.containsKey(mediaSource)) {
            String pathname = getClass().getResource(PATH + mediaSource).toString();
            final Media media = new Media(pathname);
            final MediaPlayer player = new MediaPlayer(media);

            player.setOnError(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Ocorreu um erro na MediaPlayer: " + player.getError());
                }
            });

            // add na playlist
            players.put(mediaSource, player);
        }

        return players.get(mediaSource);
    }

    /**
     * Cria um novo AudioClip ou retorna caso ele já exista.
     *
     * @param mediaSource
     * @return
     */
    public AudioClip createAudioClipPlayer(String mediaSource) {
        // Veriffica se a música já foi adicionada na playlist audioClips
        if (!audioClips.containsKey(mediaSource)) {
            String pathname = getClass().getResource(PATH + mediaSource).toString();
            final AudioClip sound = new AudioClip(pathname);

            // add na playlist
            audioClips.put(mediaSource, sound);
        }

        return audioClips.get(mediaSource);
    }

    /**
     * Toca AudioClip
     *
     * @param mediaSource
     */
    public void playAudioClip(MediaSource mediaSource) {
        audioClip = createAudioClipPlayer(mediaSource.getValue());
        audioClip.play();
    }

    /**
     * Toca música
     *
     * @param mediaSource
     */
    public void playMusic(MediaSource mediaSource) {
        final MediaPlayer mediaPlayer = createMediaPlayer(mediaSource.getValue());
        if (mediaPlayer != null) {
            mediaPlayer.play();
            setVolume(mediaSource, 1.0);
        }
    }

    /**
     * Verifica se a música está tocando
     *
     * @param mediaSource
     * @return
     */
    public boolean isPlaying(MediaSource mediaSource) {
        MediaPlayer pla = players.get(mediaSource.getValue());
        AudioClip aud = audioClips.get(mediaSource.getValue());

        if (pla == null && aud == null) {
            return false;
        } else if (pla != null) {
            Status status = pla.getStatus();
            return status == Status.PLAYING;
        } else if (aud != null) {
            return aud.isPlaying();
        }

        return false;
    }

    /**
     * Seta volume da música
     * 
     * @param mediaSource
     * @param vol 
     */
    public void setVolume(MediaSource mediaSource, double vol) {
        MediaPlayer pla = players.get(mediaSource.getValue());
        if (pla != null) {
            pla.setVolume(vol);
        }
    }

    /**
     * Para a música caso esteja tocando
     *
     * @param mediaSource
     */
    public void stop(MediaSource mediaSource) {
        MediaPlayer pla = players.get(mediaSource.getValue());
        AudioClip aud = audioClips.get(mediaSource.getValue());

        if (pla != null) {
            pla.stop();
        } else if (aud != null) {
            aud.stop();
        }
    }

    /**
     * Para todas as músicas e sons que estão tocandos
     */
    public void stopAll() {
        // Para todas as músicas
        for (Map.Entry<String, MediaPlayer> entry : players.entrySet()) {
            entry.getValue().stop();
        }
        // Para todos os audios
        for (Map.Entry<String, AudioClip> entry : audioClips.entrySet()) {
            entry.getValue().stop();
        }
    }
    
    /**
     * Seta o auto play da música
     * 
     * @param mediaSource
     * @param autoplay 
     */
    public void setAutoReplay(MediaSource mediaSource,  boolean autoplay) {
        MediaPlayer pla = players.get(mediaSource.getValue());
        pla.setAutoPlay(autoplay);
    }
}
