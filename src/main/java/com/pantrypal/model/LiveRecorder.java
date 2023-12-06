package com.pantrypal.model;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class LiveRecorder {
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private double silenceThreshold = 0.05; // You can adjust this threshold as needed

    public LiveRecorder() {
        audioFormat = getAudioFormat();
    }

    private AudioFormat getAudioFormat() {
        // the number of samples of audio per second.
        // 44100 represents the typical sample rate for CD-quality audio.
        float sampleRate = 44100;

        // the number of bits in each sample of a sound that has been digitized.
        int sampleSizeInBits = 16;

        // the number of audio channels in this format (1 for mono, 2 for stereo).
        // int channels = 2;
        int channels = 1; // Tomek need 1 to run the program.
        // todo
        // whether the data is signed or unsigned.
        boolean signed = true;

        // whether the audio data is stored in big-endian or little-endian order.
        boolean bigEndian = false;
        // todo
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    public void startRecording() {
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // the format of the TargetDataLine
                            DataLine.Info dataLineInfo = new DataLine.Info(
                                    TargetDataLine.class,
                                    audioFormat);
                            // the TargetDataLine used to capture audio data from the microphone
                            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                            targetDataLine.open(audioFormat);
                            targetDataLine.start();
                            // recordingLabel.setVisible(true);

                            // the AudioInputStream that will be used to write the audio data to a file
                            AudioInputStream audioInputStream = new AudioInputStream(
                                    targetDataLine);

                            // the file that will contain the audio data
                            File audioFile = new File("recording.wav");
                            AudioSystem.write(
                                    audioInputStream,
                                    AudioFileFormat.Type.WAVE,
                                    audioFile);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        t.start();
    }

    public void stopRecording() {
        targetDataLine.stop(); // Stops the data line to cease audio capture
        targetDataLine.close(); // Closes the data line to release system resources
    }

    /**
     * Calculates the Root Mean Square (RMS) level of audio data.
     * RMS is a measure of the average power of an audio signal.
     *
     * @param audioData The byte array containing audio data.
     * @return The calculated RMS level of the audio data.
     */
    private double calculateRMSLevel(byte[] audioData) {
        long lSum = 0;
        // Summing up all the audio data bytes
        for (byte anAudioData : audioData) {
            lSum = lSum + anAudioData;
        }

        // Calculating the average of the audio data
        double dAvg = lSum / (double) audioData.length;

        double sumMeanSquare = 0d;
        // Calculating the sum of the squares of the differences from the average
        for (byte anAudioData : audioData) {
            sumMeanSquare = sumMeanSquare + Math.pow(anAudioData - dAvg, 2d);
        }

        // Calculating the average of the mean square values
        double averageMeanSquare = sumMeanSquare / audioData.length;
        // Returning the square root of the average mean square, which is the RMS
        return Math.sqrt(averageMeanSquare);
    }
}
