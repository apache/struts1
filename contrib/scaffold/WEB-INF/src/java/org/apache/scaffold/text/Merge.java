package org.apache.scaffold.text;


import java.util.Map;


/**
 * Merge utilities.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 11:53:12 $
 */
 public class Merge {


    /**
     * The token markers for replacement variables, i.e. ${a1}
     */
    public static String TOKEN_PREFIX= "${";
    public static String TOKEN_SUFFIX = "}";
    private static int TOKEN_PREFIX_LEN = TOKEN_PREFIX.length();
    private static int TOKEN_SUFFIX_LEN = TOKEN_SUFFIX.length();


    /**
     * Merge a Map of tokens with a template in a StringBuffer.
     * The parameters are marked by ${key} where (String) Map.get(key)!=null.
     * The merged content is returned in the same StringBuffer.
     * Tokens cannot be nested. Unmatched tokens are ignored.
     * @param content The text containing merge tokens to be replaced.
     *
     * @returns int Number of replacements made
     */
    public static int keys(StringBuffer content, Map tokens) {

        String t = content.toString();
        int start = t.length();
        int count = 0;

        while ((start = t.lastIndexOf(TOKEN_PREFIX,start-1)) != -1) {

            int end = t.indexOf(TOKEN_SUFFIX,start);
            String key = t.substring(start+TOKEN_PREFIX_LEN,end);
            String value = (String) tokens.get(key);
            if (value!=null) {
                content.replace(start,end+TOKEN_SUFFIX_LEN,value);
                count++;
            }
        }
        return count;
    }
  }

  /*


  <forward name="bookmark" path = "/do/item/View?key=${key}"/>

  form implements Bookmark;

  forward = findForward("bookmark");

  if (forward!=null) {

  StringBuffer bmPath = new StringBuffer( forward.getPath() );

  merge(bmPath,describe(form));

  Bookmark bmForm = (Bookmark) form;
  bmForm.setBookmark(bookmark.toString());

  }

  ...

  if (isBookmark())
    return getBookmarkForward();
  return findForward("continue");


  */