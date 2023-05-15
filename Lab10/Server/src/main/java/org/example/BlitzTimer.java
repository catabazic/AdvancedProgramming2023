package org.example;


import java.util.Timer;
import java.util.TimerTask;

public class BlitzTimer {
    private Timer timer;
    private int timeLeft;

    public BlitzTimer(int seconds) {
        timeLeft = seconds;
    }

    public synchronized void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeLeft--;
                if (timeLeft == 0) {
                    stopTimer();
                }
            }
        }, 1000, 1000); // Decrease time left every second
    }

    public synchronized void stopTimer() {
        timer.cancel();
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
