package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore prim;
  private TorpedoStore sec;

  @Before
  public void init(){

    prim = Mockito.mock(TorpedoStore.class);
    sec = Mockito.mock(TorpedoStore.class);

    this.ship = new GT4500(prim,sec);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(prim.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    // assertEquals(true, result);
    verify(prim,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    // assertEquals(true, result);
    verify(prim,times(1)).fire(1);
    verify(sec,times(1)).fire(1);

  }

  @Test
  public void fireTorpedo_Own_1(){
    // Arrange
    when(prim.isEmpty()).thenReturn(true);
    //when(prim.fire(1)).thenReturn(false);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Own_2(){
    // Arrange
    when(prim.fire(1)).thenReturn(true);
    //when(sec.fire(1)).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    //verify(prim,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Own_3(){
    // Arrange
    //when(prim.fire(1)).thenReturn(false);
    when(prim.isEmpty()).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    //verify(prim,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Own_4(){
    // Arrange
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    when(sec.isEmpty()).thenReturn(true);
    // Act
    boolean thirdResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, firstResult && secondResult);
    //verify(prim,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Own_5(){
    // Arrange
    when(prim.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn(true);
    // Act

    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
     assertEquals(true, firstResult && secondResult);
    //verify(prim,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Own_6(){
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Own_7(){
    // Arrange
    when(prim.isEmpty()).thenReturn(true);
    //when(prim.fire(1)).thenReturn(false);
    when(sec.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Own_8(){
    // Arrange
    when(sec.isEmpty()).thenReturn(true);
    //when(prim.fire(1)).thenReturn(false);
    when(prim.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Own_9(){
    // Arrange
    when(sec.isEmpty()).thenReturn(true);
    //when(prim.fire(1)).thenReturn(false);
    when(prim.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

}
