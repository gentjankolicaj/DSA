package dsa.dining_philosophers;

import java.util.concurrent.TimeUnit;

public class Constants {

  public static final int NUMBER_OF_PHILOSOPHERS = 5;
  public static final int NUMBER_OF_CHOPSTICKS = 5;
  public static final long SIMULATION_RUNTIME = 5000;
  public static final long CHOPSTICK_TRY_LOCK_TIME = 100;
  public static final TimeUnit CHOPSTICK_TRY_LOCK_TIME_UNIT = TimeUnit.MILLISECONDS;

  private Constants() {
  }


}
