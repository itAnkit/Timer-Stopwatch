package timerandstopwatch;

import java.awt.event.*;
import javax.swing.*;
class Stopwatch {
    private JLabel label;
    private long startTime;
    private Timer stopwatchTimer;

    public Stopwatch(JLabel label) {
        this.label = label;
        stopwatchTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                int hour = (int) (elapsedTime / 3600000);
                int minute = (int) ((elapsedTime % 3600000) / 60000);
                int second = (int) ((elapsedTime % 60000) / 1000);
                int millisecond = (int) (elapsedTime % 1000) / 10;
                label.setText(String.format("%02d:%02d:%02d.%02d", hour, minute, second, millisecond));
            }
        });
    }

    public void start() {
        startTime = System.currentTimeMillis();
        stopwatchTimer.start();
    }

    public void stop() {
        stopwatchTimer.stop();
    }
}