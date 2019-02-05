package binaryTranslator;

/**
 *
 * @author Emma Adelmann
 * @version 1.0
 */
public class BinaryToDecimal
{
    
    public static int convert(String b)
    {
        int decimal = 0;
        int pos;
        int[] bitstream = new int[b.length()];
        
        for (int i = 0; i < b.length(); i++)
            bitstream[i] = Integer.parseInt(b.substring(i, i + 1));
        
        pos = bitstream.length - 1;
        
        for (int bit : bitstream)
        {
            decimal += bit*Math.pow(2, pos);
            pos--;
        }
        
        return decimal;
    }
}