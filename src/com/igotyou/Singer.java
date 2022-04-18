package com.igotyou;

public class Singer implements Runnable {

  private final Song song;

  public Singer(Song song) {
    this.song = song;
  }

  @Override
  public void run() {
    try {
      String threadName = Thread.currentThread().getName();
      while (song.singNextString(threadName));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
