package dsa.dining_philosophers;

import org.junit.jupiter.api.Test;

class AppTest {


  @Test
  void modulus() {
    for (int i = 0, len = 5; i < 5; i++) {
      int modulus = (i + 1) % len;
      System.out.println("i " + i + " mod " + modulus);
    }
  }

}