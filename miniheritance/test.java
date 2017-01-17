

public class B {
  public B() {
  
  }
  
  public  B( int i ) {
    // sets value for i from i
  }
  
  protected int i;
}

public class D1 extends B {
  public  D1() {
  
  }
  
  public D1( int i ) {
  
  }
  
  protected int i;
}

public class  D2 extends B {
  public  D2() {
  
  }
  
  public D2( int i ) {
  
  }
  
  protected int j;
}
public class SD2 extends D2 {
  public SD2() {
  }
  
  public SD2( int i ) {
  
  }
  
  protected int j;
}