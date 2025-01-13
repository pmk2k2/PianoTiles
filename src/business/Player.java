package business;

import ddf.minim.AudioOutput;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 In der folgenden Klasse wird unsere Audiowiedergabe organisiert.
 Hier befinden sich alle Funktionen hierzu(play,pause,stop und etc.).
 **/
public class Player {
    private SimpleAudioPlayer audioPlayer;
    private final AudioOutput out;
    private final SimpleMinim minim;
    private Thread playThread;
    private boolean isPlaying;
    int position;


    public Player() {
        minim= new SimpleMinim();
        audioPlayer = minim.loadMP3File("/Users/macbook/IdeaProjects/pianotilesdemo copy/src/songs/ETA_GameView.mp3");
        out = minim.getLineOut();
        isPlaying= false;
    }
    public void play(String fileName) {
        playThread = new Thread() {
            public void run() {
                position=0;
                audioPlayer= minim.loadMP3File(fileName);
                audioPlayer.play();
                isPlaying=true;

            }
        };
        playThread.setDaemon(true);
        playThread.start();
    }
    public void pause() {
        playThread.interrupt();
        if (audioPlayer != null&&audioPlayer.isPlaying()) {
            this.position=audioPlayer.position();
            audioPlayer.pause();
            isPlaying = false;
        }
    }
    public void stopMusik() {
        audioPlayer=null;
        minim.dispose();
    }

    public void wiedergabeAngestoßen() {
        playThread = new Thread() {
            public void run() {
                audioPlayer.play(position);
                isPlaying = true;
            }
        };
        playThread.setDaemon(true);
        playThread.start();
    }

    public void volume(double value) {
        audioPlayer.setGain(0 + (-60) * (1 - (float)(value) / 100));
    }
    public float getTempo(String fileName){
        audioPlayer = minim.loadMP3File(fileName, 1024);
        return out.getTempo();
    }
    public boolean isPlaying() {
        return audioPlayer.isPlaying();
    }
}