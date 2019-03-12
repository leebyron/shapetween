public class Bezier3D
{
  public float aX, aY, aZ, bX, bY, bZ, cX, cY, cZ, dX, dY, dZ;
  
  public Bezier3D( float[] a, float[] b, float[] c, float[] d ){
    this( a[0], a[1], a[2], b[0], b[1], b[2], c[0], c[1], c[2], d[0], d[1], d[2] );
  }

  public Bezier3D( float aX, float aY, float aZ,
                   float bX, float bY, float bZ,
                   float cX, float cY, float cZ,
                   float dX, float dY, float dZ ){
    this.aX = aX;
    this.aY = aY;
    this.aZ = aZ;

    this.bX = bX;
    this.bY = bY;
    this.bZ = bZ;

    this.cX = cX;
    this.cY = cY;
    this.cZ = cZ;
    
    this.dX = dX;
    this.dY = dY;
    this.dZ = dZ;
  }
  
  public float getLength(){
    return 1.0f;
  }
  
  public float getX( float t ){
    float ab = lerp(aX,bX,t);
    float bc = lerp(bX,cX,t);
    float cd = lerp(cX,dX,t);
    
    float abbc = lerp(ab,bc,t);
    float bccd = lerp(bc,cd,t);
    
    return lerp(abbc,bccd,t);
  }

  public float getY( float t ){
    float ab = lerp(aY,bY,t);
    float bc = lerp(bY,cY,t);
    float cd = lerp(cY,dY,t);
    
    float abbc = lerp(ab,bc,t);
    float bccd = lerp(bc,cd,t);
    
    return lerp(abbc,bccd,t);
  }

  public float getZ( float t ){
    float ab = lerp(aZ,bZ,t);
    float bc = lerp(bZ,cZ,t);
    float cd = lerp(cZ,dZ,t);
    
    float abbc = lerp(ab,bc,t);
    float bccd = lerp(bc,cd,t);
    
    return lerp(abbc,bccd,t);
  }
  
  public float angle( float t ){
    return angleZ(t);
  }
  
  public float angleX( float t ){
    float abZ = lerp(aZ,bZ,t);
    float bcZ = lerp(bZ,cZ,t);
    float cdZ = lerp(cZ,dZ,t);
    
    float abbcZ = lerp(abZ,bcZ,t);
    float bccdZ = lerp(bcZ,cdZ,t);

    float abY = lerp(aY,bY,t);
    float bcY = lerp(bY,cY,t);
    float cdY = lerp(cY,dY,t);
    
    float abbcY = lerp(abY,bcY,t);
    float bccdY = lerp(bcY,cdY,t);
    
    return (float) Math.atan2( bccdY - abbcY, bccdZ - abbcZ );
  }
  
  public float angleY( float t ){
    float abX = lerp(aX,bX,t);
    float bcX = lerp(bX,cX,t);
    float cdX = lerp(cX,dX,t);
    
    float abbcX = lerp(abX,bcX,t);
    float bccdX = lerp(bcX,cdX,t);

    float abZ = lerp(aZ,bZ,t);
    float bcZ = lerp(bZ,cZ,t);
    float cdZ = lerp(cZ,dZ,t);
    
    float abbcZ = lerp(abZ,bcZ,t);
    float bccdZ = lerp(bcZ,cdZ,t);
    
    return (float) Math.atan2( bccdZ - abbcZ, bccdX - abbcX );
  }
  
  public float angleZ( float t ){
    float abX = lerp(aX,bX,t);
    float bcX = lerp(bX,cX,t);
    float cdX = lerp(cX,dX,t);
    
    float abbcX = lerp(abX,bcX,t);
    float bccdX = lerp(bcX,cdX,t);

    float abY = lerp(aY,bY,t);
    float bcY = lerp(bY,cY,t);
    float cdY = lerp(cY,dY,t);
    
    float abbcY = lerp(abY,bcY,t);
    float bccdY = lerp(bcY,cdY,t);
    
    return (float) Math.atan2( bccdY - abbcY, bccdX - abbcX );
  }
  
  public static float lerp( float a, float b, float n ){
    return (b-a)*n + a;
  }
  
}
