public class Angle{
	
	private double angle;
	
	//Contructors
	
	public Angle(){
		angle = 0;
	}
	
	public Angle( double ang ){
		angle = wrap(ang);
	}
	
	
	//GETTERS
	
	
	
	public double get(){
		return angle;
	}
	
	
	
	//MODIFIERS
	
	
	
	public double set(double ang){
		angle = wrap(ang);
		return get();
	}
	
	
	//combined getter, modifier. (some people like this syntax instead).
	public double angle(){
		return get();
	}
	
	public double angle(double ang){
		angle = wrap(ang);
		return get();
	}
	
	
	
	//OTHER
	
	
	
	public String toString(){
		double num = (get() / ( 2 * Math.PI)) * 360;
		return num + "";
	}
	
	
	
	//STATIC FUNCTIONS
	
	
	
	//Adds the two angles together and returns a correct angle.
	public static Angle addAngles(Angle ang1, Angle ang2){
		double newAng = ang1.get() + ang2.get();
		
		return new Angle(newAng);
	}
	
	
	
	//distance
	public static Angle distance( Angle ang1, Angle ang2 ){
		double newAng = ang1.get() - ang2.get();
		newAng = Math.abs(newAng);
		
		if(newAng > Math.PI)
			newAng = (float)(2 * Math.PI) - newAng;
		
		return new Angle(newAng);
	}
	
	
	
	//Bisect
	//returns the angle between two angles
	public static Angle bisect(Angle ang1, Angle ang2){
		double avg = ( ang1.get() + ang2.get() ) * 0.5;
		
		Angle newAngle = new Angle( avg );
		
		if( distance( ang1, newAngle ).get() > (Math.PI * 0.5) )
			newAngle = new Angle( avg + Math.PI);
		
		return newAngle;
	}
	
	
	
	//wrap
	//Returns the same angle between -pi and pi
	public static double wrap(double ang){
		while( ang < -Math.PI ){
			ang += 2 * Math.PI;
		}
		
		while( ang > Math.PI ){
			ang -= 2* Math.PI;
		}
		
		return ang;
	}
	
}