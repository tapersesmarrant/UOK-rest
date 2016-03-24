package fr.iutinfo.skeleton.utils;


import com.google.common.base.Strings;

/**
 * Created by nicbe on 23/03/2016.
 */
public class StrUtils {

    public static boolean newValue( String in, String out){
        if (Strings.nullToEmpty(in).equals(Strings.nullToEmpty(out))){
            return false;
        } else if (out == null){
            return false;
        } else if (!Strings.nullToEmpty(in).equals(Strings.nullToEmpty(out))){
            return true;
        } else {
            return false;
        }
    }
}
