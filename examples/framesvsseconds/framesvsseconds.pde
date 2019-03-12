import megamu.shapetween.*;

Tween animation;
boolean doFrames;

void setup(){
  size(200,200);
  Shapetween.register(this);
  
  animation = new Tween(10,190,3);
  animation.repeat(Tween.FOREVER, Tween.ALTERNATE);
}

void draw(){
  background(255);
  ellipse( animation.value(), 100, 10, 10 );
}

void mouseMoved(){
  frameRate((float)mouseX/5);
}

void mousePressed(){
  doFrames = !doFrames;
  if( doFrames )
    Tween.animationMode(Tween.FRAMES);
  else
    Tween.animationMode(Tween.SECONDS);
}
