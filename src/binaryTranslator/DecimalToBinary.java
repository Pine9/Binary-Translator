package binaryTranslator;
import java.util.ArrayList;

/**
 *
 * @author Emma Adelmann
 */
public class DecimalToBinary {

    /*
      Consumes a unicode String to convert.
      Returns the binary conversion as a String.
    */
    public static String convert(String a) {
        String output = "";

        for (int i = 0; i < a.length(); i++) {
            output += charConvert( a.charAt(i) ) + " ";
        }

        return output;
    }

    /*
      Consumes a char to convert.
      Returns the binary conversion as a String.
    */
    public static String charConvert(char c) {
        int decimal = (int)c;
        return convert(decimal);
    }

    /*
      Consumes a decimal (base 10) int to convert.
      Returns the binary conversion as a String.
    */
    public static String convert(int decimal) {
        ArrayList<Integer> binary = new ArrayList<Integer>();
        String output = "";
        boolean negative = (decimal < 0);
        decimal = Math.abs(decimal);

        int len;
        for (len = 8; decimal > Math.pow(2, len); len++) {
            // handles numbers that take more than a byte to store
        }

        for (int b = len; b > 0; b--) {
            int remainder = decimal % 2;

            if (negative) {
              // we have to invert the bits
              if (remainder == 0) {
                remainder = 1;
              } else {
                remainder = 0;
              }
            }

            binary.add(remainder);
            decimal /= 2;
        }

        for (int b = binary.size() - 1; b >= 0; b--) {
            // convert to String
            output += binary.get(b);
        }

        if (negative) {
            // make sure there's a leading 1
            if (!output.substring(0, 1).equals("1")) {
                output = "1" + output;
            }

            // after inverting the bits, we have to add 1
            int newResult = Integer.parseInt(output, 2) + 1;
            output = Integer.toBinaryString(newResult);
        }

        return output;
    }
}
