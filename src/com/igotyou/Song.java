package com.igotyou;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Song {

  private static int counter = 0; // Счётчик текущей строки песни
  private static Set<String> buf = new HashSet<>(); // Множество для контроля кол-ва "пропевших" строчку потоков

  private final String[][] lyrics = {
      {"Cher", "They say we're young and we don't know \nWe won't find out until we grow"},
      {"Sonny", "Well I don't know if all that's true \n'Cause you got me, and baby I got you"},
      {"Sonny", "Babe"},
      {"Sonny, Cher", "I got you babe \nI got you babe"},
      {"Cher", "They say our love won't pay the rent \nBefore it's earned, our money's all been spent"},
      {"Sonny", "I guess that's so, we don't have a pot \nBut at least I'm sure of all the things we got"},
      {"Sonny", "Babe"},
      {"Sonny, Cher", "I got you babe \nI got you babe"},
      {"Sonny", "I got flowers in the spring \nI got you to wear my ring"},
      {"Cher", "And when I'm sad, you're a clown \nAnd if I get scared, you're always around"},
      {"Cher", "So let them say your hair's too long \n'Cause I don't care, with you I can't go wrong"},
      {"Sonny", "Then put your little hand in mine \nThere ain't no hill or mountain we can't climb"},
      {"Sonny", "Babe"},
      {"Sonny, Cher", "I got you babe \nI got you babe"},
      {"Sonny", "I got you to hold my hand"},
      {"Cher", "I got you to understand"},
      {"Sonny", "I got you to walk with me"},
      {"Cher", "I got you to talk with me"},
      {"Sonny", "I got you to kiss goodnight"},
      {"Cher", "I got you to hold me tight"},
      {"Sonny", "I got you, I won't let go"},
      {"Cher", "I got you to love me so"},
      {"Sonny, Cher", "I got you babe \nI got you babe \nI got you babe \nI got you babe \nI got you babe"}
  };

  public synchronized boolean singNextString(String name) throws InterruptedException {
    String[] str = null;
    List<String> names = null;

    do {
      if (counter < lyrics.length) {
        str = lyrics[counter];
        names = Arrays.asList(str[0].split(", "));
        if (!names.contains(name)) {
          wait(); // Если строчка не наша - ждём
        }
      } else {
        break;
      }
    } while (!names.contains(name));

    buf.add(name);
    if (str != null) {
      System.out.println(name + ": " + str[1]);
    }

    if (names != null && buf.size() == names.size()) {
      counter++;
      buf.clear();
    } else {
      wait(); // Ждём, когда второй поток дойдёт до припева
    }

    notify(); // Пропели - разрешаем двигаться другому потоку дальше
    return str != null;
  }

}
