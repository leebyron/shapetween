public class PathPoint{
  public Bezier3D piece;
  public float t;
  
  public PathPoint( Bezier3D piece, float t ){
    this.piece = piece;
    this.t = t;
  }
}
