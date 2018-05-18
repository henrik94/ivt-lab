package hu.bme.mit.spaceship;


/**
* Interface for torpedostore
*/
public interface ITorpedoStore {

  
  public boolean fire(int numberOfTorpedos);

  public boolean isEmpty();

  public int getTorpedoCount();

}
