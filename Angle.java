public class Angle{
	
	private float angle;
	
	public Angle(){
		angle = 0;
	}
	
	public Angle( float ang ){
		angle = wrap(ang);
	}
	
	
	//GETTERS
	
	
	
	public float get(){
		return angle;
	}
	
	
	
	//MODIFIERS
	
	
	
	public void set(float ang){
		angle = wrap(ang);
	}
	
	
	
	//OTHER
	
	
	
	public static Angle addAngles(Angle ang1, Angle ang2){
		float newAng = ang1.get() + ang2.get();
		
		return new Angle(newAng);
	}
	
	
	
	public static Angle difference( Angle ang1, Angle ang2 ){
		float newAng = ang1.get() - ang2.get();
		newAng = Math.abs(newAng);
		
		if(newAng > Math.PI)
			newAng = (float)(2 * Math.PI) - newAng;
		
		return new Angle(newAng);
	}
	
	
	
	//make a bisect funtion?
	/*public static Angle bisect( Angle ang1, Angle ang2 ){
		
		float avg = ( ang1.get() + ang2.get() ) / 2.0;
		
	}*/
	
	
	
	public static float wrap(float ang){
		while( ang < -Math.PI ){
			ang += 2 * Math.PI;
		}
		
		while( ang > Math.PI ){
			ang -= 2* Math.PI;
		}
		
		return ang;
	}
	
}