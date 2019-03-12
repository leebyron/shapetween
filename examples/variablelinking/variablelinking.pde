import megamu.shapetween.*;

float x;

Tween aniOne;
Tween aniTwo;
Tween aniThree;

void setup(){
  size(200,200);
  
  Shapetween.register(this);
  
  aniOne = new Tween(0,190,1,Tween.QUADRATIC,"x");
  
  aniTwo = new Tween();
  aniTwo.variable("x");
  aniTwo.fromVariable();
  aniTwo.to(120);
  
  aniThree = new Tween();
  aniThree.variable("x");
  aniThree.fromVariable();
  aniThree.to(160);
  
  aniOne.nextTween(aniTwo);
  aniTwo.nextTween(aniThree);
}

void draw(){
  background(255);
  ellipse( x, 100, 10, 10 );
}

void mousePressed(){
  aniOne.start();
  aniTwo.reset();
  aniThree.reset();
}
