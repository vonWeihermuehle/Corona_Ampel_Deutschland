package net.mbmedia.coronaampel.util;

import net.mbmedia.coronaampel.Konfiguration;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashHelper {

    public static String getHash(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(date);

        String tohash = Konfiguration.API_KEY + dateString;
        return MD5(tohash);
    }

    private static String MD5(String md5)  {
        java.security.MessageDigest md;
        try {
            md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
