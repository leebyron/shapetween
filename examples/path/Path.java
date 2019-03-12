import processing.core.*;

public class Path{
  
  float[][] coords;
  Bezier3D[] pieces;
  float[] pieceLength;
  float length;
  int vertices, pieceCount;
  boolean using3D;
  
  public Path(){
    coords = new float[2][3];
    pieces = new Bezier3D[2];
    pieceLength = new float[2];
    length = 0;
    vertices = 0;
    pieceCount = 0;
    using3D = false;
  }
  
  public void vertex( float x ){
    vertex( x, 0, 0 );
  }
  
  public void vertex( float x, float y ){
    vertex( x, y, 0 );
  }
  
  public void vertex( float x, float y, float z ){
    if( vertices == coords.length )
      expand();
      
    if( z != 0 )
      using3D = true;
    
    float[] newCoord = {x,y,z};
    coords[vertices] = newCoord;
    
    /*
    If there exist no points, just add a point
    If there exists 1 point, push a line
    If there exist 2 points, pop the line, push 2 edge curves
    If there exist 3+ points, pop an edge, push a catmull, push an edge
    */
    if( vertices == 1 ){
      addPiece( 0, new LineCurve( coords[0], coords[1] ) );
    }else if( vertices == 2 ){
      addPiece( 0, new EdgeCurve( coords[0], coords[1], coords[2], EdgeCurve.IN ) );
      addPiece( 1, new EdgeCurve( coords[0], coords[1], coords[2], EdgeCurve.OUT ) );
    }else if( vertices > 2 ){
      addPiece( pieceCount-1, new CatmullRom( coords[vertices-3], coords[vertices-2], coords[vertices-1], coords[vertices] ) );
      addPiece( pieceCount, new EdgeCurve( coords[vertices-2], coords[vertices-1], coords[vertices], EdgeCurve.OUT ) );
    }
    
    vertices++;
    if( vertices > 1 )
      pieceCount++;
  }
  
  public void draw( PApplet p ){
    draw(p.g);
  }
  
  public void draw( PGraphics g ){
    for( int i=0; i<pieceCount; i++ ){
      Bezier3D b = pieces[i];
      if( using3D )
        g.bezier( b.aX, b.aY, b.aZ, b.bX, b.bY, b.bZ, b.cX, b.cY, b.cZ, b.dX, b.dY, b.dZ );
      else
        g.bezier( b.aX, b.aY, b.bX, b.bY, b.cX, b.cY, b.dX, b.dY );
    }
  }
  
  public float getX( float t ){
    if( vertices == 0 )
      return 0;
    if( vertices == 1 )
      return coords[0][0];
      
    // get the right piece
    PathPoint p = piecePosition( t );    
    return p.piece.getX( p.t );
  }
  
  public float getY( float t ){
    if( vertices == 0 )
      return 0;
    if( vertices == 1 )
      return coords[0][1];

    // get the right piece
    PathPoint p = piecePosition( t );    
    return p.piece.getY( p.t );
  }
  
  public float getZ( float t ){
    if( vertices == 0 )
      return 0;
    if( vertices == 1 )
      return coords[0][2];

    // get the right piece
    PathPoint p = piecePosition( t );    
    return p.piece.getZ( p.t );
  }
  
  public float[] get( float t ){
    float[] coord = { getX(t), getY(t), getZ(t) };
    return coord;
  }
  
  public float angle( float t ){
    return angleZ( t );
  }

  public float angleX( float t ){
    if( vertices < 2 )
      return 0;

    // get the right piece
    PathPoint p = piecePosition( t );    
    return p.piece.angleX( p.t );
  }

  public float angleY( float t ){
    if( vertices < 2 )
      return 0;

    // get the right piece
    PathPoint p = piecePosition( t );    
    return p.piece.angleY( p.t );
  }
  
  public float angleZ( float t ){
    if( vertices < 2 )
      return 0;

    // get the right piece
    PathPoint p = piecePosition( t );
    return p.piece.angleZ( p.t );
  }
  
  
  private PathPoint piecePosition( float t ){
    // init the piece to the last point
    int piece = pieceCount-1;
    float pieceT = 1;
    
    // loop through pieces to figure out where t falls
    float normLength = 0;
    for( int i=0; i<pieceCount; i++ ){
      
      float pLen = pieceLength[i]/length;
            
      // if the t is within this piece
      if( t <= normLength + pLen ){
        piece = i;
        pieceT = (t - normLength)/pLen;
        break;
      }
      
      // increment normalize length
      normLength += pLen;
    }
    
    return new PathPoint( pieces[piece], pieceT );
  }
 
  private void addPiece( int index, Bezier3D piece ){
    pieces[index] = piece;
    pieceLength[index] = piece.getLength();
    length = 0;
    int pC = Math.max( pieceCount, index+1 );
    for( int i=0; i<pC; i++ )
      length += pieceLength[i];
  } 
  
  private void expand(){
    int newSize = coords.length << 1;
    
    float tempC[][] = new float[newSize][3];
    Bezier3D tempP[] = new Bezier3D[newSize];
    float tempL[] = new float[newSize];
    
    System.arraycopy(coords, 0, tempC, 0, Math.min(newSize, coords.length));
    System.arraycopy(pieces, 0, tempP, 0, Math.min(newSize, pieces.length));
    System.arraycopy(pieceLength, 0, tempL, 0, Math.min(newSize, pieceLength.length));
    
    coords = tempC;
    pieces = tempP;
    pieceLength = tempL;
  }
  
}
