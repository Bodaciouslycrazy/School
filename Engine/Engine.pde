//Bodie Malik

class Box{
 private float x = 0;
 private float y = 0;
 private float w = 1;
 private float h = 1;
 
 
 //This class is a box ith (x,y) and width w and height h.
 //Random functions to help box stuff.
 public Box(float a, float b, float c, float d){
  x = a;
  y = b;
  w = c;
  h = d;
 }
 
 
 //Getters and Modifiers
 
 public float x(){
   return x;
 }
 
 public float x(float a){
   x = a;
   return x;
 }
 
 public float y(){
   return y;
 }
 
 public float y(float a){
   y = a;
   return y;
 }
 
 public float w(){
   return w;
 }
 
 public float w(float a){
   w = a;
   return w;
 }
 
 public float h(){
   return h;
 }
 
 public float h(float a){
   h = a;
   return h;
 }
 
 public String toString(){
   return "("+ x + ", " + y + ") w:" + w + ", h:" + h;
 }
}


//Similar to Box, but for circles.
class Circle{
 private float x = 0;
 private float y = 0;
 private float r = 0;
 
 public Circle(float a, float b, float c){
  x = a;
  y = b;
  r = c;
 }
 
 
 //Getters and Modifiers
 
 public float x(){
   return x;
 }
 
 public float x( float a){
   x = a;
   return x;
 }
 
 public float y(){
   return y;
 }
 
 public float y( float a){
   y = a;
   return y;
 }
 
 public float r(){
   return r;
 }
 
 public float r( float a){
   r = a;
   return r;
 }
 
 public String toString(){
  return "("+ x + ", " + y + ") r:" + r; 
 }
}


//Static functions

public static boolean collide(Box box1, Box box2){
 if(box2.x() > box1.x() + box1.w() || box1.x() > box2.x() + box2.w() )
  return false;
 if( box2.y() > box1.y() + box1.h() || box1.y() > box2.y() + box2.h() )
  return false;
  
 return true;
}

public static boolean collide( Circle circ1, Circle circ2){
  if( distance( circ1, circ2 ) < circ1.r() + circ2.r() )
   return true;
   
  return false;
}

public static float distance(float x1, float y1, float x2, float y2){
 float num = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
 num = Math.sqrt(num);
 return num;
}

public static float distance( Box box1, Box box2 ){
 return distance( box1.x() + (box1.w() * 0.5), box1.y() + (box1.h() * 0.5), box2.x() + (box2.w() * 0.5), box2.y() + (box2.h() * 0.5) ); 
}

public static float distance( Circle circ1, Circle circ2){
 return distance( circ1.x(), circ1.y(), circ2.x(), circ2.y() ); 
}


public void setup(){
 Box bob = new Box(1,1,3,3);
 Box joe = new Box(1.5,1.5, 4, 4);
 System.out.println(bob);
 System.out.println(joe);
 
 System.out.println( collide( bob, joe ) );
}