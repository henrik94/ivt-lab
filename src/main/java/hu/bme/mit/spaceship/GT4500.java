package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  private ITorpedoStore primaryTorpedoStore;
  private ITorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500(ITorpedoStore primary, ITorpedoStore secondary) {
    this.primaryTorpedoStore = primary;
    this.secondaryTorpedoStore = secondary;
  }

  public boolean fireLaser(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedo(FiringMode firingMode) {

    boolean firingSuccess = false;

    if(firingMode == FiringMode.SINGLE){
        if (wasPrimaryFiredLast) {
          // try to fire the secondary first
          firingSuccess = fireSecondaryTorpedo();
          if(!firingSuccess){
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
          firingSuccess = firePrimaryTorpedo();
	  }
            // if both of the stores are empty, nothing can be done, return failure
          
        }
        else {
          // try to fire the primary first
         firingSuccess = firePrimaryTorpedo();
	 if(!firingSuccess){          
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
           firingSuccess = fireSecondaryTorpedo();
            // if both of the stores are empty, nothing can be done, return failure
          }
        }
   }        
   else{
        // try to fire both of the torpedo stores	
	boolean primaryFireSuccess = firePrimaryTorpedo();
	boolean secondaryFireSuccess = fireSecondaryTorpedo();
	firingSuccess = primaryFireSuccess && secondaryFireSuccess;	
   }

    return firingSuccess;
  }

  private boolean firePrimaryTorpedo(){
     boolean firingSuccess = false;
     if (!primaryTorpedoStore.isEmpty()) {
        firingSuccess = primaryTorpedoStore.fire(1);
        wasPrimaryFiredLast = true;
     }
     return firingSuccess;
  }

  private boolean fireSecondaryTorpedo(){
     boolean firingSuccess = false;
     if (!secondaryTorpedoStore.isEmpty()) {
        firingSuccess = secondaryTorpedoStore.fire(1);
        wasPrimaryFiredLast = false;
     }
     return firingSuccess;
  }

}
