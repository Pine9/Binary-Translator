package binaryTranslator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emma Adelmann
 * @version 1.2
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

public class MainClass
{
    static Scanner sc = new Scanner(System.in);

    public static int[] parseCommand(String b)
    {
        if (b.startsWith("#"))
        {
            int openingIdx = b.indexOf("#");
            int closingIdx = b.substring(openingIdx + 1).indexOf("#");
            // error check here for no closing brace
            if (closingIdx == -1)
            {
                System.out.println("Parsing error: Please completely enclose the override command.");
                System.exit(1);
            }
            return new int[] {openingIdx, closingIdx};
        }
        return new int[] {0, 0};
    }

    public static int getFormat(String b)
    {
        int[] cmdIdxs = parseCommand(b);
        if (!(b.substring(cmdIdxs[0], cmdIdxs[1]).equals("")))
        {
            String cmd = b.substring(cmdIdxs[0] + 1, cmdIdxs[1] + 1);
            switch (cmd)
            {
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
                    System.out.println("Invalid command.");
                    System.exit(1);
            }
        }

        if (b.matches(".*[a-zA-Z]+.*"))
            return 3; //case 3: contains characters

        for (int i = 0; i < b.length(); i++)
        {
            String test = b.substring(i, i+1);
            if ( !(test.equals("0") || test.equals("1") || test.equals(" ")) )
                return 2; //case 2: contains nonbinary numbers
        }
        return 1; //case 1: is binary
    }

    public static String decide(String b, int format)
    {
      // for use in spaceHandler
        if (format <= 1)
            return BinaryToDecimal.convert(b, format) + "";
        else
            return DecimalToBinary.convert(Integer.parseInt(b));
    }

    public static String spaceHandler(String b, int format, ArrayList<Character> unicode)
    {
        // returns binary or decimal output and modifies unicode
        ArrayList<Integer> spaces = new ArrayList<Integer>();
        spaces.add(0);

        for (int i = 1; i < b.length(); i++) //find indices of spaces
        {
            if (b.substring(i, i+1).equals(" "))
                spaces.add(i);
        }

        String output = "";

        for (int j = 0; j < spaces.size(); j++)
        {
            String currentblock;

            if (j == 0)
                currentblock = b.substring(0, spaces.get(1));
            else if (j == spaces.size() - 1)
                currentblock = b.substring(spaces.get(j) + 1);
            else
                currentblock = b.substring(spaces.get(j) + 1, spaces.get(j + 1));

            output += " " + decide(currentblock, format);

            if (format == 1 || format == 0) // if binary
                unicode.add((char)BinaryToDecimal.convert(currentblock, format));
            else // if decimal
                unicode.add((char)Integer.parseInt(currentblock));
        }

        return output;
    }

    public static void main(String[] args)
    {
        while (true) {
            System.out.print("Enter your text here: ");
            String b = sc.nextLine();

            int format = getFormat(b);
            if (b.startsWith("#"))
                b = b.substring(parseCommand(b)[1] + 3);

            ArrayList<Character> chardest = new ArrayList<Character>();

          if (format <= 1) {
                if (b.indexOf(" ") > 0)
                {
                    System.out.println("Decimal: " + spaceHandler(b, format, chardest));
                    System.out.print("Unicode: ");
                    for (Character chara : chardest)
                        System.out.print(chara);
                    System.out.println();
                }
                else
                {
                    int conversion = BinaryToDecimal.convert(b, format);
                    System.out.println("Decimal : " + conversion);
                    System.out.println("Unicode: " + (char)conversion);
                    // default output for single stream of binary without spaces
                }
          }

          else if (format == 2) {
                if (b.indexOf(" ") > 0)
                {
                    System.out.println("Binary:" + spaceHandler(b, format, chardest));
                    System.out.print("Unicode:");
                    for (Character chara : chardest)
                        System.out.print(" " + chara);
                    System.out.println();
                }
                else
                {
                    System.out.println("Binary: " + DecimalToBinary.convert( Integer.parseInt(b)) );
                    System.out.println("Unicode: " + (char)Integer.parseInt(b));
                }
           }

           else if (format == 3) { // no spacehandler needed: space is a char
                    System.out.println("Binary: " + DecimalToBinary.convert(b));
                    System.out.print("Decimal:");
                    for (int i = 0; i < b.length(); i++)
                        System.out.print(" " + (int)b.charAt(i));
                    System.out.println();
            }
            System.out.println();
          }
    }
}
