import processing.core.*; import megamu.shapetween.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class Tween_Path extends PApplet {

Tween ani;
Path path;

public void setup(){
  size( 200, 200 );
  smooth();
  
  ani = new Tween( this, 3, Tween.SECONDS, Tween.REPEAT );
  ani.start();
  
  path = new Path();  
}

public void draw(){
  background( 255 );
  noFill();
  path.draw( this );
  
  translate( path.getX( ani.position() ), path.getY( ani.position() ) );
  rotate( path.angle( ani.position() ) );
  rect( -3, 0, 6, 6 );
}

public void mousePressed(){
  path.vertex( mouseX, mouseY );
}

public void keyPressed(){
  path = new Path();
}
  static public void main(String args[]) {     PApplet.main(new String[] { "Tween_Path" });  }}