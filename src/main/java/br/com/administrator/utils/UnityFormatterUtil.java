package br.com.administrator.utils;

import java.text.DecimalFormat;

public class UnityFormatterUtil {

    private static final double KILOBYTE_TO_MEGABYTE = 1024.0;
    private static final double KILOBYTE_TO_GIGABYTE = 1024.0 * 1024.0;

    public static String formatKilobytes(Long kilobytes) {
        if (kilobytes == null) {
            return null;
        }

        final DecimalFormat df = new DecimalFormat("#.##");

        if (kilobytes < KILOBYTE_TO_MEGABYTE) {
            return kilobytes + " KB";
        } else if (kilobytes < KILOBYTE_TO_GIGABYTE) {
            return df.format(kilobytes / KILOBYTE_TO_MEGABYTE) + " MB";
        } else {
            return df.format(kilobytes / KILOBYTE_TO_GIGABYTE) + " GB";
        }
    }
}