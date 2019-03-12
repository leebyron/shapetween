public class LineCurve extends Bezier3D
{
  public LineCurve( float[] a, float[] b ){
    super(
    //a
    a[0],
    a[1],
    a[2],
    
    //b
    a[0],
    a[1],
    a[2],
    
    //c
    b[0],
    b[1],
    b[2],
    
    //d
    b[0],
    b[1],
    b[2]
    );
  }
}
