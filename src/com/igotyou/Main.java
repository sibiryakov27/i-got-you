package com.igotyou;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Song song = new Song();
    Thread cher = new Thread(new Singer(song), "Cher");
    Thread sonny = new Thread(new Singer(song), "Sonny");

    cher.start();
    sonny.start();

    sonny.join();
    cher.join();

    System.out.println("\n*** APPLAUSE ***");
  }

}
