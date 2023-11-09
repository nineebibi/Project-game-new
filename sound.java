import java.io.File;
import javax.sound.sampled.*;

class Sound {
    private Clip clip;

    public void playSound(String soundFilePath, float volume) {
        try {
            // Load sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath));

            // Create Clip
            clip = AudioSystem.getClip();

            // Play sound when Clip is ready
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.START) {
                        clip.start();
                    }
                }
            });

            clip.open(audioInputStream);

            // Adjust volume level
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();   // Stop the clip
            clip.close();  // Close the clip
        }
    }
}
