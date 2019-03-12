import megamu.shapetween.*;

Tween ani;
Path path;

void setup(){
  size( 200, 200 );
  smooth();
  
  ani = new Tween( this, 3, Tween.SECONDS, Tween.REPEAT );
  ani.start();
  
  path = new Path();  
}

void draw(){
  background( 255 );
  noFill();
  path.draw( this );
  
  translate( path.getX( ani.position() ), path.getY( ani.position() ) );
  rotate( path.angle( ani.position() ) );
  rect( -3, 0, 6, 6 );
}

void mousePressed(){
  path.vertex( mouseX, mouseY );
}

void keyPressed(){
  path = new Path();
}
