package org.apache.scaffold.text;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import java.sql.Timestamp;


/**
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
 */
public class ConvertUtils {

/*
    protected Locale[] availableLocales = null;
    public static Locale[] getAvailableLocales() {
        return availableLocales;
    }
    public static void setAvailableLocales(Locale[] _availableLocales) {
        availableLocales = _availableLocales;
    }

*/

    /**
     * An empty string.
     */
    public static String EMPTY_STRING = "";


    /**
     * Returns true if null or trims to an empty string.
     */
    public static boolean isBlank(String s) {
        return ((s==null) || (s.trim().equals(EMPTY_STRING)));
    }


    /**
     * Return String with of digits only (0..9).
     * http://java.sun.com/j2se/1.4/docs/api/java/lang/Character.html
     */
    public static String getDigits(String s) {
        if (s==null) return null;
        int n = s.length();
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) sb.append(c);
        }
        return (sb.toString());
    }

    /**
     */
    public static String getNumber(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getInstance().format(value));
       return (NumberFormat.getInstance(locale).format(value));
    }

    /**
     */
    public static String getDecimal(Number value, Locale locale) {
        if (locale==null)
            return (DecimalFormat.getInstance().format(value));
        return (DecimalFormat.getInstance().format(value));
    }

    /**
     */
    public static String getPercent(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getPercentInstance().format(value));
        return (NumberFormat.getPercentInstance(locale).format(value));
    }


    /*
    public static String getInteger(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getIntegerInstance().format(value));
        return (NumberFormat.getIntegerInstance(locale).format(value));
    }
    */


    /**
     */
    public static DecimalFormatSymbols getGenericDecimal(Locale locale) {
        DecimalFormatSymbols symbols =
            new DecimalFormatSymbols(locale);
        symbols.setGroupingSeparator('`'); // :FIXME: Want apostrophe here
        return symbols;
    }

    /**
     * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
     */
    public static String DECIMAL_PATTERN ="###,###.###";

    /**
     * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
     */
    public static String CURRENCY_PATTERN ="$" + DECIMAL_PATTERN;


    /**
     */
    public static String getDecimal(Number value, Locale locale, String pattern) {
        NumberFormat formatter;
        if (locale==null)
            formatter = new java.text.DecimalFormat(pattern);
        else {
            formatter = NumberFormat.getNumberInstance(locale);
            DecimalFormat df = (DecimalFormat) formatter;
            df.applyPattern(pattern);
            return df.format(value);
        }
        return (formatter.format(value));
    }


    /**
     */
    public static String getDecimal(Number value) {
        return getDecimal(value,(Locale) null,DECIMAL_PATTERN);
    }


    /**
     */
    public static String getCurrency(Number value) {
        return getDecimal(value,null,CURRENCY_PATTERN);
    }


    /**
     * Convert String to Date using given format.
     * Returns null if conversion fails.
     * Set lenient=false to disallow dates like 2001-9-32.
     * http://java.sun.com/j2se/1.4/docs/api/java/text/SimpleDateFormat.html
     * @author Hal Deadman
     */
    public static Date getDate(String dateText,
            String format, boolean lenient) {
        if (dateText == null) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat(format);
                // setLenient avoids allowing dates like 9/32/2001
                // which would otherwise parse to 10/2/2001
            df.setLenient(false);
            return df.parse(dateText);
        }
        catch(ParseException e) {
            return null;
        }
    }


    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;


    /**
     * Convert String to Date using given format.
     * Returns null if conversion fails.
     * Uses "strict" coNversion (lenient=false).
     * @author Hal Deadman
     */
    public static Date getDate(String dateString, String format) {
        return getDate(dateString,format,LENIENT_DATE);
    }


    /**
     * A "default" date format.
     */
    public static String DEFAULT_DATE_PATTERN = "d-MMM-yy";


    /**
     * Convert String to Date using a medium (weekday day month year) format.
     * Returns null if conversion fails.
     * Uses "strict" coNversion (lenient=false).
     * @author Hal Deadman
     */
    public static Date getDate(String dateString) {
        return getDate(dateString,DEFAULT_DATE_PATTERN,LENIENT_DATE);
    }


    /**
     * Default style for dates and times.
     */
    public static int DEFAULT = DateFormat.DEFAULT;

    /**
     * Short style for dates and times.
     */
    public static int SHORT = DateFormat.SHORT;

    /**
     * Medium style for dates and times.
     */
    public static int MEDIUM = DateFormat.MEDIUM;

    /**
     * Long style for dates and times.
     */
    public static int LONG = DateFormat.LONG;

    /**
     * Full style for dates and times.
     */
    public static int FULL = DateFormat.FULL;

/*
          dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT,
                                                     currentLocale);
          today = new Date();
          dateOut = dateFormatter.format(today);
*/

    /**
     * Convert date to String for given locale in given style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date, Locale locale, int style) {
        if (locale==null)
            return (DateFormat.getDateInstance(style).format(date));
        return (DateFormat.getDateInstance(style,locale).format(date));
   }


    /**
     * Convert date to String for default locale in given style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date, int style) {
       return getDate(date,(Locale) null,style);
    }

    /**
     * Convert date to String for default locale in DEFAULT style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date) {
       return getDate(date,(Locale) null,DEFAULT);
   }

    /**
     * Convert timestamp to String for given locale in given style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp, Locale locale, int style) {
        Date date = (Date) timestamp;
        if (locale==null)
            return (DateFormat.getDateTimeInstance(style,style).format(date));
        return (DateFormat.getDateTimeInstance(style,style,locale).format(date));
   }

    /**
     * Convert date to String for default locale in given style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp, int style) {
       return getTimestamp(timestamp,(Locale) null,style);
    }

    /**
     * Convert date to String for default locale in DEFAULT style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp) {
       return getTimestamp(timestamp,(Locale) null,DEFAULT);
   }


    /**
     * Return Timestamp value using a String.
     * Null or conversion error returns null.
     * @param String representing Timestamp
     */
    public static Timestamp toTimestamp(String string) {
        if (string==null)
            return null;
        else try {
            return Timestamp.valueOf(string);
        } catch (Throwable t) {
            return null;
        }
    }


    /**
     * Return String value representing Timestamp.
     * Null returns null.
     * @param Timestamp
     */
    public static String toString(Timestamp timestamp) {
        if (timestamp==null)
            return null;
        else
            return timestamp.toString();
    }


    /**
     * Return Date value using a String.
     * Null or conversion error returns null.
     * @param String representing Date
     */
    public static Date toDate(String string) {
        if (string==null)
            return null;
        else try {
            return getDate(string);
        } catch (Throwable t) {
            return null;
        }
    }


    /**
     * Return String value representing Date.
     * Null returns null.
     * @param Date
     */
    public static String toString(Date date) {
        if (date==null)
            return null;
        else
            return getDate(date);
    }

}