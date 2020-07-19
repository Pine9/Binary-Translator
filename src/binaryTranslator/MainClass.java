package binaryTranslator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emma Adelmann
 */
/*
This translator automatically detects what format its input is in, but there may be times
when you want to specify the input. For example, input consisting of only 1s and 0s will
be detected as binary, but you may want to interpret a value such as 100 as decimal input.
The below commands will override this detection system, allowing you to specify the format of your input.
Be sure the substitute input for your actual argument.
Override Commands:
#decimal# input - forces the translator to interpret the input as being in the decimal format
#binary# input - forces the translator to interpret the input as being in the binary format
#signed# input - forces the translator to interpret input as being in the two's complement signed binary format
#unicode# input - forces the translator to interpret the input as being in the unicode format
#exit# - lets you end the program
*/

public class MainClass {

    static Scanner sc = new Scanner(System.in);

    /*
      Consumes user input as a String.
      Returns a list of two ints representing the indices of the String such
      that userInput.substring(openingIndex, closingIdx) returns the user's command.
    */
    public static int[] parseCommand(String b) {
        if (b.startsWith("#")) {
            int openingIdx = b.indexOf("#");
            int closingIdx = b.substring(openingIdx + 1).indexOf("#");

            // error check here for no closing brace
            if (closingIdx == -1) {
                System.out.println("Parsing error: Please completely enclose the override command.");
                System.exit(1);
            }
            return new int[] {openingIdx, closingIdx};
        }
        return new int[] {0, 0};
    }

    /*
      Consumes user input as a String.
      Returns an int representing the format of the user input.
    */
    public static int getFormat(String b) {
        int[] cmdIdxs = parseCommand(b);
        if (!(b.substring(cmdIdxs[0], cmdIdxs[1]).equals(""))) {
            String cmd = b.substring(cmdIdxs[0] + 1, cmdIdxs[1] + 1);
            switch (cmd) {
                case "decimal":
                    return 2;
                case "binary":
                    return 1;
                case "signed":
                    return 0;
                case "unicode":
                    return 3;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("\nInvalid command.\n");
                    translate();
            }
        }

        if (b.matches(".*[a-zA-Z]+.*")) {
          // case 3: contains characters
          return 3;
        }

        for (int i = 0; i < b.length(); i++) {
            String test = b.substring(i, i+1);
            if ( !(test.equals("0") || test.equals("1") || test.equals(" "))) {
              // case 2: contains nonbinary numbers
              return 2;
            }
        }
        // case 1: is binary
        return 1;
    }

    /*
      Consumes an ArrayList of Characters and String to append them to.
      Returns the resulting String.
    */
    public static String appendChars(String str, ArrayList<Character> chars) {
        str += "\nUnicode: ";

        for (Character ch : chars) {
            str += ch;
        }

        return str + "\n";
    }

    /*
      The main logic of the program. Prompts the user for input and handles
      the corresponding output.
    */
    public static void translate() {
        System.out.print("Enter your text here: ");
        String b = sc.nextLine();

        int format = getFormat(b);
        if (b.startsWith("#")) {
            b = b.substring(parseCommand(b)[1] + 3);
        }

        ArrayList<Character> chardest = new ArrayList<Character>();

      if (format <= 1) {
            // Expected Binary
            if (!b.matches("^[0-1| ]+$")) {
                System.out.println("\nIncompatible format.\n");
                translate();
            }
            String[] inputs = b.split(" ");
            String toPrint = "";
            System.out.print("Decimal: ");

            for (int i = 0; i < inputs.length; i++) {
                int conversion = BinaryToDecimal.convert(inputs[i], format);
                toPrint += conversion;
                chardest.add((char)conversion);

                if (i < inputs.length - 1) {
                    toPrint += " ";
                }
            }

            toPrint = appendChars(toPrint, chardest);
            System.out.println(toPrint);

       } else if (format == 2) {
            // Expected Decimal
            if (!b.matches("^[0-9| |-]+$")) {
                System.out.println("\nIncompatible format.\n");
                translate();
            }
            String[] inputs = b.split(" ");
            int[] numbers = new int[inputs.length];
            String toPrint = "";

            System.out.print("Binary: ");

            for (int i = 0; i < inputs.length; i++) {
                int num = Integer.parseInt(inputs[i]);
                toPrint += DecimalToBinary.convert(num);
                chardest.add((char)num);

                if (i < inputs.length - 1) {
                    toPrint += " ";
                }
            }

            toPrint = appendChars(toPrint, chardest);
            System.out.println(toPrint);

       } else if (format == 3) {
            // Expected Char
            System.out.println("Binary: " + DecimalToBinary.convert(b));
            System.out.print("Decimal:");
            for (int i = 0; i < b.length(); i++) {
                System.out.print(" " + (int)b.charAt(i));
            }
            System.out.println("\n");
        }

        translate();
    }

    public static void main(String[] args) {
        translate();
    }
}
