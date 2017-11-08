import java.util.ArrayList;

class Machine{
	@Override
	public String toString() {
		return "I am a machine";
	}
	public void start() {
		System.out.println("Machine starting.");
	}
}

class Camera extends Machine{
	@Override
	public String toString() {
		return "I am a Camera";
	}
}

public class Wildcard {
	public static void main(String[] args) {
		ArrayList<Machine> list1 = new ArrayList<Machine>();
		list1.add(new Machine());
		
		ArrayList<Camera> list2 = new ArrayList<Camera>();
		list2.add(new Camera());
		
		showList(list1);
		showList(list2);
		show(list1);
		show(list2);
	}
	
	public static void showList(ArrayList<?> list) {
		for(Object value: list) {
			System.out.println(value);
		}
	}
	public static void show(ArrayList<? extends Machine> list) {
		for(Machine value: list) {
			value.start();
		}
	}
}
