package binaryTranslator;
import java.util.ArrayList;

/**
 *
 * @author Emma Adelmann
 * @version 1.2
 */
public class DecimalToBinary
{
    public static String convert(String a)
    {
        String output = "";

        for (int i = 0; i < a.length(); i++)
            output += charConvert( a.charAt(i) ) + " ";

        return output;
    }

    public static String charConvert(char c)
    {
        int decimal = (int)c;
        return convert(decimal);
    }

    public static String convert(int decimal)
    {
        ArrayList<Integer> binary = new ArrayList<Integer>();
        String output = "";
        boolean negative = (decimal < 0);

        int len;
        for (len = 8; decimal > Math.pow(2, len); len++) {
            // handles numbers that take more than a byte to store
        }

        for (int b = len; b > 0; b--)
        {
            int remainder = decimal % 2;
            if (negative) // we have to invert the bits
            {
              if (remainder == 0)
              {
                remainder = 1;
              }
              else
              {
                remainder = 0;
              }
            }
            binary.add(remainder);
            decimal /= 2;
        }

        for (int b = binary.size() - 1; b >= 0; b--)
            output += binary.get(b);

        if (negative) // after inverting the bits, we have to add 1
        {
            // unfortunately, since we don't have binary arithmetic built-in
            // yet, we'll need to use some outside help from the Integer class.
            int newResult = Integer.parseInt(output, 2) + 1;
            output = Integer.toBinaryString(newResult);
        }

        return output;
    }
}
