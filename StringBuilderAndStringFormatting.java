public class StringBuilderAndStringFormatting {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("");
		sb.append("Hellow.");
		sb.append(" ");
		sb.append("I am a skydiver.");
		System.out.println(sb.toString());
		
		StringBuilder s = new StringBuilder("");
		s.append("Hello.");
		s.append(" ");
		s.append("I am a tiger tamer.");
		System.out.println(s.toString());

//Formatting 	
		System.out.print("here.\t That was a tab.\n new line");
		System.out.println(" more text");
//%d is a formatting character 		
		System.out.printf("Total cost is %d%n", 5 );
//specify the width. Add a - at the front is left alignments		
		System.out.printf("Total cost is %10d\n", 5 );
//Formatting floating point value
		System.out.printf("%.2f\n", 5.6);
		System.out.printf("%10.2f\n", 325.66);
	}
}
