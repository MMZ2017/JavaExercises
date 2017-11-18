import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scanner {
	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "text.txt";
		File textFile = new File(fileName);
		Scanner in = new Scanner(textFile);
		int value = in.nextInt();
		System.out.println("read value: " + value);
		in.nextLine();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println(line);
		}
		in.close();
	}
}
