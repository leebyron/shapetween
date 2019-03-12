import megamu.shapetween.*;
  
Tween aniOne;
Tween aniTwo;

void setup(){
  size(200,200);
  
  Shapetween.register(this);
  
  aniOne = new Tween(10,width-10);
  aniTwo = new Tween(10,height-10);
  
  aniOne.nextTween(aniTwo);
  aniTwo.nextTween(aniOne);
  
  aniOne.onFinish("reverseIt");
  aniTwo.onFinish("reverseIt");
  
  aniOne.start();
}

void draw(){
  background(255);
  
  float x = aniOne.value();
  float y = aniTwo.value();
  ellipse( x, y, 10, 10 );
}


void reverseIt(Tween t){
  t.reverse();
}

void mousePressed(){
  aniOne.start();
  aniTwo.reset();
}
