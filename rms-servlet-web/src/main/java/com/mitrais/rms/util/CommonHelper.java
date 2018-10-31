package com.mitrais.rms.util;

/**
 * Common Helper
 * @author Abednego_S810
 */
public class CommonHelper {
    
    private CommonHelper() { }
    
    /**
     * Convert String to Long
     * @param number any number in String format
     * @return parsed Long from number. Any error return -1
     */
    public static long parseLong(String number) {
        long rtn;
        try {
            rtn = ((number != null && !number.isEmpty()) ? Long.parseLong(number) : 0);
        } catch (NumberFormatException nfe) { 
            rtn = -1;
        }
        return rtn;
    }
    
}
