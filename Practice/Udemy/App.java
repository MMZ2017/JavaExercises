package Practice;

public class App {
	public static void main(String[] args) {
		IShape circle1 = new Circle();
		circle1.getArea();
		circle1.getPerimeter();
		((Circle) circle1).random();
		
		IShape square1 = new Square();
		square1.getArea();
		square1.getPerimeter();
		((IPolygon)square1).getNumOfSide();
	}
}
