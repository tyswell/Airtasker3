package tys.com.airtasker3.util;

/**
 * Created by chokechaic on 4/28/2016.
 */
public class StringUtil {

    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() > 0 ? true : false;
    }
}
