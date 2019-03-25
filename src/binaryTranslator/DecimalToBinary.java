package binaryTranslator;
import java.util.ArrayList;

/**
 *
 * @author Emma Adelmann
 * @version 1.1
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
        
        int len;
        for (len = 8; decimal > Math.pow(2, len); len++) {
            // handles numbers that take more than a byte to store
        }
        
        for (int b = len; b > 0; b--)
        {
            binary.add(decimal % 2);
            decimal /= 2;
        }
        
        for (int b = binary.size() - 1; b >= 0; b--)
            output += binary.get(b);
        
        return output;
    }
}