public class CatmullRom extends Bezier3D
{
  public CatmullRom( float[] a, float[] b, float[] c, float[] d ){
    super(
    //a
    b[0],
    b[1],
    b[2],
    
    //b
    b[0] + 0.175f*( c[0] - a[0] ),
    b[1] + 0.175f*( c[1] - a[1] ),
    b[2] + 0.175f*( c[2] - a[2] ),

    //c
    c[0] - 0.175f*( d[0] - b[0] ),
    c[1] - 0.175f*( d[1] - b[1] ),
    c[2] - 0.175f*( d[2] - b[2] ),
    
    //d
    c[0],
    c[1],
    c[2]
    );
  }
}
