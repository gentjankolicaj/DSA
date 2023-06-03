package dsa.dining_philosophers;

public interface Human {

  void eat(Meal meal) throws InterruptedException;

  void think() throws InterruptedException;
}
