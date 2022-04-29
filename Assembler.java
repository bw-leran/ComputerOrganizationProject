import java.util.*;
import java.io.*;

public class Assembler {

	private static boolean constantNext = false;
	private static boolean dataNext = false;

	public static boolean DEBUG = true;

	private static Map<String, Integer> opcodes = new Hashtable<String, Integer>();

	static {
		opcodes.put("halt", 0);
		opcodes.put("load", 1);
		opcodes.put("loadc", 2);
		opcodes.put("store", 3);
		opcodes.put("add", 4);
		opcodes.put("mul", 5);
		opcodes.put("sub", 6);
		opcodes.put("div", 7);
		opcodes.put("and", 8);
		opcodes.put("or", 9);
		opcodes.put("not", 10);
		opcodes.put("lshift", 11);
		opcodes.put("rshift", 12);
		opcodes.put("bwc", 13);
		opcodes.put("bwd", 14);
		opcodes.put("if", 15);
	}


	public static String translate(String asmInstruction) {
		if (constantNext) {
			constantNext = false;
			return asmInstruction + '\n';
		}
		if (dataNext) {
			return asmInstruction + '\n';
		}
		if (asmInstruction.equals("")) {
			return asmInstruction;
		}

		Scanner lineScanner = new Scanner(asmInstruction);
		String operation = lineScanner.next();

        if (operation.equals("//")) return "";

		if (operation.equals("halt")) {
			dataNext = true;
			return "00000000\n";
		}

		int arg1 = lineScanner.nextInt();
		int opcode = opcodes.get(operation);



		if (operation.equals("loadc")) {
			constantNext = true;
			int macInstruction = opcode << 8;
		    macInstruction += (arg1 << 4);
			return Integer.toHexString(macInstruction) + '\n';
		}


		int arg2 = lineScanner.nextInt();
		int macInstruction = opcode << 8;
		macInstruction += (arg1 << 4);
		macInstruction += arg2;

		return Integer.toHexString(macInstruction) + '\n';

	}

	public static void main(String[] args) {
		File asmCode = new File(args[0]);
		PrintWriter pw = null;;
		try {
		   Scanner scanner = new Scanner(asmCode);
		   pw = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
		   while(scanner.hasNext()) {
			   String asmInstruction = scanner.nextLine();
			   if (DEBUG) System.out.println("asm instruction = " + asmInstruction);
			   String macInstruction = translate(asmInstruction);
			   if (DEBUG) System.out.println("mac instruction = " + macInstruction);
			   pw.write(macInstruction);
		   }
		   pw.close();
		} catch(IOException e) {
		   System.err.println(e.getMessage());
		}
		System.out.println("Done");
	}

}
