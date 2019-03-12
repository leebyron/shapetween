import megamu.shapetween.*;
  
Tween[] animations;

PFont font;

void setup(){
  size(200,200);
  smooth();
  colorMode(HSB);
  
  Shapetween.register(this);
  
  textFont(createFont("Verdana",10));
  
  animations = new Tween[6];
  
  for(int i=0; i<animations.length; i++){
    animations[i] = new Tween(0.2*width,0.8*width,3);
    animations[i].repeat(Tween.FOREVER,Tween.ALTERNATE);
  }
  
  animations[0].easing(Tween.LINEAR);
  animations[1].easing(Tween.QUADRATIC);
  animations[2].easing(Tween.COSINE);
  animations[3].easing(Tween.ELASTIC);
  animations[4].easing(Tween.BACK);
  animations[5].easing(Tween.BOUNCE);
}

void draw(){
  background(255);
  
  fill(240);
  noStroke();
  rect(0,0,width*0.2,height);
  rect(width*0.8,0,width*0.2,height);
  
  for(int i=0; i<animations.length; i++){
    pushMatrix();
    translate(0, map(i, 0, animations.length-1, height*0.15, height*0.85) );
    
    fill(0);
    text(animations[i].easing().name(),width*0.2,0);
    
    fill( abs(animations[i].speed()), 255, 255 );
    ellipse( animations[i].value(), 10, 10 + animations[i].force()/width, 10 );
    
    popMatrix();
  }
  
}
