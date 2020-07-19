package binaryTranslator;

/**
 *
 * @author Emma Adelmann
 */
public class BinaryToDecimal {

    /*
      Consumes a binary String to convert, and whether it is positive
      (format == 0) or negative (format == 1).
      Returns the decimal (base 10) conversion as an int.
    */
    public static int convert(String b, int mode) {
        int decimal = 0;
        int start = 0;
        int[] bitstream = new int[b.length()];
        int pos = bitstream.length - 1;

        if (mode == 0 && b.startsWith("1")) {
          // signed negative number
          decimal = -(int)Math.pow(2, pos);
          start = 1;
        }

        for (; start < b.length(); start++) {
            bitstream[start] = Integer.parseInt(b.substring(start, start + 1));
        }

        for (int bit : bitstream) {
            decimal += bit*Math.pow(2, pos);
            pos--;
        }

        return decimal;
    }
}
