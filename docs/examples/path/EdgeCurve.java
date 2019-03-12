public class EdgeCurve extends Bezier3D
{  
  public static final boolean IN = true;
  public static final boolean OUT = false;
  
  public EdgeCurve( float[] a, float[] b, float[] c, boolean in ){
    super(
    //a
    ( in? a[0] : b[0] ),
    ( in? a[1] : b[1] ),
    ( in? a[2] : b[2] ),
    
    //b
    ( in? a[0] : b[0] ) + 0.175f*( ( in? b[0] : c[0] ) - a[0] ),
    ( in? a[1] : b[1] ) + 0.175f*( ( in? b[1] : c[1] ) - a[1] ),
    ( in? a[2] : b[2] ) + 0.175f*( ( in? b[2] : c[2] ) - a[2] ),

    //c
    ( in? b[0] : c[0] ) - 0.175f*( c[0] - ( in? a[0] : b[0] ) ),
    ( in? b[1] : c[1] ) - 0.175f*( c[1] - ( in? a[1] : b[1] ) ),
    ( in? b[2] : c[2] ) - 0.175f*( c[2] - ( in? a[2] : b[2] ) ),
    
    //d
    ( in? b[0] : c[0] ),
    ( in? b[1] : c[1] ),
    ( in? b[2] : c[2] )
    );
  }
}
