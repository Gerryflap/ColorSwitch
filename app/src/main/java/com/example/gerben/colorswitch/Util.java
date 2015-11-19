package com.example.gerben.colorswitch;

/**
 * Created by gerben on 19-11-15.
 */
public class Util {
    public static short[] dimRGB(short[] rgb, short brightness) {
        short[] rgbOut = new short[rgb.length];
        for (int i = 0; i < rgb.length; i++) {
            rgbOut[i] = (short) (rgb[i] * (brightness/255d));
            System.out.println(rgbOut[i]);
        }

        return rgbOut;
    }
}
