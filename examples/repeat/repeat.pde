import megamu.shapetween.*;
  
Tween animation;

void setup(){
  size(200,100);
  
  Shapetween.register(this);
  
  animation = new Tween(10,width-10);
  animation.repeat(2, Tween.ALTERNATE);
}

void draw(){
  background(255);
  
  float x = animation.value();
  ellipse( x, height/2, 10, 10 );
}

void mousePressed(){
  animation.start();
}
