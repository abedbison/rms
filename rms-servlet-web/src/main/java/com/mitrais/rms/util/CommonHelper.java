package com.mitrais.rms.util;

import com.mitrais.rms.model.User;

/**
 * Common Helper
 * @author Abednego_S810
 */
public class CommonHelper {
    
    private CommonHelper() { }
    
    /**
     * Convert String to Long
     * @param number
     * @return parsed Long from number. Any error return -1
     */
    public static long parseLong(String number) {
        long rtn = 0;
        try {
            rtn = ((number != null && !number.isEmpty()) ? Long.parseLong(number) : 0);
        } catch (NumberFormatException nfe) { 
            rtn = -1;
        }
        return rtn;
    }
    
}
