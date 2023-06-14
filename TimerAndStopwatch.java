package timerandstopwatch;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;

public class TimerAndStopwatch {
    private JFrame frame;
    private JLabel timerLabel;
    private JLabel stopwatchLabel;
    private Timer timer;
    private Stopwatch stopwatch;
    private int seconds;

    public TimerAndStopwatch() {
        frame = new JFrame("Timer and Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        // Timer Panel
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BorderLayout());
        timerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        timerPanel.setBackground(new Color(240, 240, 240));

        JLabel timerTitleLabel = new JLabel("Timer");
        timerTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        timerTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        timerPanel.add(timerTitleLabel, BorderLayout.NORTH);

        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerPanel.add(timerLabel, BorderLayout.CENTER);

        // Timer Buttons
        JPanel timerButtonsPanel = new JPanel();
        timerButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton timerStartButton = new JButton("Start");
        JButton timerStopButton = new JButton("Stop");
        JButton timerResetButton = new JButton("Reset");

        timerButtonsPanel.add(timerStartButton);
        timerButtonsPanel.add(timerStopButton);
        timerButtonsPanel.add(timerResetButton);

        timerPanel.add(timerButtonsPanel, BorderLayout.SOUTH);

        // Stopwatch Panel
        JPanel stopwatchPanel = new JPanel();
        stopwatchPanel.setLayout(new BorderLayout());
        stopwatchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        stopwatchPanel.setBackground(new Color(240, 240, 240));

        JLabel stopwatchTitleLabel = new JLabel("Stopwatch");
        stopwatchTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        stopwatchTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        stopwatchPanel.add(stopwatchTitleLabel, BorderLayout.NORTH);

        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        stopwatchLabel.setHorizontalAlignment(JLabel.CENTER);
        stopwatchPanel.add(stopwatchLabel, BorderLayout.CENTER);

        // Stopwatch Buttons
        JPanel stopwatchButtonsPanel = new JPanel();
        stopwatchButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton stopwatchStartButton = new JButton("Start");
        JButton stopwatchStopButton = new JButton("Stop");
        JButton stopwatchResetButton = new JButton("Reset");

        stopwatchButtonsPanel.add(stopwatchStartButton);
        stopwatchButtonsPanel.add(stopwatchStopButton);
        stopwatchButtonsPanel.add(stopwatchResetButton);

        stopwatchPanel.add(stopwatchButtonsPanel, BorderLayout.SOUTH);

        // Add Panels to Frame
        frame.add(timerPanel, BorderLayout.NORTH);
        frame.add(stopwatchPanel, BorderLayout.SOUTH);

        // Create Timer
        int delay = 1000; // 1 second
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if (seconds >= 0) {
                    int hour = seconds / 3600;
                    int minute = (seconds % 3600) / 60;
                    int second = (seconds % 3600) % 60;
                    timerLabel.setText(String.format("%02d:%02d:%02d", hour, minute, second));
                } else {
                    timer.stop();
                    timerLabel.setText("00:00:00");
                    playBeep();
                    JOptionPane.showMessageDialog(frame, "Timer has ended!", "Timer", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        timer = new Timer(delay, timerListener);

        // Create Stopwatch
        stopwatch = new Stopwatch(stopwatchLabel);

        // Timer Button Listeners
        timerStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the time in seconds:", "Timer", JOptionPane.PLAIN_MESSAGE);
                if (input != null) {
                    try {
                        int time = Integer.parseInt(input);
                        if (time >= 0) {
                            seconds = time;
                            int hour = seconds / 3600;
                            int minute = (seconds % 3600) / 60;
                            int second = (seconds % 3600) % 60;
                            timerLabel.setText(String.format("%02d:%02d:%02d", hour, minute, second));
                            timer.start();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a positive integer.", "Timer", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid number.", "Timer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        timerStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        timerResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timerLabel.setText("00:00:00");
            }
        });

        // Stopwatch Button Listeners
        stopwatchStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.start();
            }
        });

        stopwatchStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.stop();
            }
        });

        stopwatchResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.stop();
                stopwatchLabel.setText("00:00:00");
            }
        });

        frame.setVisible(true);
    }

    private void playBeep() {
        try {
            Toolkit.getDefaultToolkit().beep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerAndStopwatch();
            }
        });
    }
}
