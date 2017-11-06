package Practice;

public class Circle implements IShape {
	double radius = 3;

	@Override
	public void getArea() {
		System.out.println(radius*radius*Math.PI);
	}
	
	@Override
	public void getPerimeter() {
		System.out.println(2*radius*Math.PI);
	}
		
	public void random() {
		
	}
}
