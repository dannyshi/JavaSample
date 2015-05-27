package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WlMatcher {
    private static WlMatcher instance;
    private static final Object lock = new Object();

    private static final int MATCH_SYMBOL = -1;
    private static final int MATCH_BEGIN = -2;
    private static final int MATCH_END = -3;

    private Map<String, int[]> patternMap;

    private WlMatcher() {

    }

    private WlMatcher(Set<String> adapterMapKeys) {
        patternMap = new HashMap<String, int[]>();
        for (String key : adapterMapKeys) {
            if (!key.contains("*")) {
                continue;
            }

            patternMap.put(key, compilePattern(key));
        }
    }

    public static WlMatcher getInstance(Map<String, String> adapterMap) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new WlMatcher(adapterMap.keySet());
                }
            }
        }
        return instance;
    }

    public String execute(Map<String, String> adapterMap, String data) {
    	String matchResult = null;
        if (adapterMap == null) {
        	System.out.println(data + " matched " + matchResult);
            return null;
        } else if (adapterMap.containsKey(data)) {
        	matchResult = adapterMap.get(data);
        	System.out.println(data + " matched " + matchResult);
            return matchResult;
        }

        String module = getModule(data);
        System.out.println("module: "+module);
        Map<String, String> matchedMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : adapterMap.entrySet()) {
            String key = entry.getKey();

            // module match
            String adapterModule = getModule(key);
            if (!module.equals(adapterModule)) {
                continue;
            }
            if (isModulePath(key)) {
                matchedMap.put(key, entry.getValue());
                continue;
            }

            if (execute(key, data.toCharArray())) {
                matchedMap.put(key, entry.getValue());
            }
        }
        matchResult = nearestOne(matchedMap);
        System.out.println(data + " matched " + matchResult);
        return matchResult;
    }

    private boolean isModulePath(String s) {
        return s.indexOf("/", 1) == s.length() - 1;
    }

    private String getModule(String data) {
        int secondSlashIndex = data.indexOf("/", 1);
        return secondSlashIndex == -1 ? data : data.substring(0, secondSlashIndex);
    }

    /**
     * url: 
     *      /shopping/order
     *      
     * priority from high to low:
     *      /shopping/order
     *      /shopping/order* (one level)
     *      /shopping/* (multi level)
     *      /shopping/
     *      
     */
    private String nearestOne(Map<String, String> matchedMap) {
    	String result = null;
        int len = 0;
        for (Map.Entry<String, String> entry : matchedMap.entrySet()) {
            int keyLen = entry.getKey().length();
            if (keyLen > len) {
                result = entry.getValue();
                len = keyLen;
            }
        }
        return result;
    }

    // ============================== refer struts2 WlidcardHelper
    private int[] compilePattern(String data) {
        // Prepare the arrays
        int[] expr = new int[data.length() + 2];
        char[] buff = data.toCharArray();

        // Prepare variables for the translation loop
        int y = 0;

        // Must start from beginning
        expr[y++] = MATCH_BEGIN;

        if (buff.length > 0) {
            // Main translation loop
            for (int x = 0; x < buff.length; x++) {
                if (buff[x] == '*') {
                    expr[y++] = MATCH_SYMBOL;
                } else {
                    expr[y++] = buff[x];
                }
            }
        }

        // Must match end at the end
        expr[y] = MATCH_END;

        return expr;
    }

    private boolean execute(String key, char[] buff) {
        if (!patternMap.containsKey(key)) {
            return false;
        }
        int[] expr = patternMap.get(key);

        // Allocate the result buffer
        char[] rslt = new char[expr.length + buff.length];

        // The previous and current position of the expression character
        int charpos = 0;

        // The position in the expression, input, translation and result arrays
        int exprpos = 0;
        int buffpos = 0;
        int rsltpos = 0;
        int offset = -1;

        // First check for MATCH_BEGIN
        boolean matchBegin = false;

        if (expr[charpos] == MATCH_BEGIN) {
            matchBegin = true;
            exprpos = ++charpos;
        }

        // Search the fist expression character (except MATCH_BEGIN - already
        // skipped)
        while (expr[charpos] >= 0) {
            charpos++;
        }

        // The expression character (MATCH_SYMBOL)
        int exprchr = expr[charpos];

        while (true) {
            // Check if the data in the expression array before the current
            // expression character matches the data in the input buffer
            if (matchBegin) {
                if (!matchArray(expr, exprpos, charpos, buff, buffpos)) {
                    return false;
                }

                matchBegin = false;
            } else {
                offset = indexOfArray(expr, exprpos, charpos, buff, buffpos);

                if (offset < 0) {
                    return false;
                }
            }

            // Check for MATCH_BEGIN
            if (matchBegin) {
                if (offset != 0) {
                    return false;
                }

                matchBegin = false;
            }

            // Advance buffpos
            buffpos += (charpos - exprpos);

            // Check for END's
            if (exprchr == MATCH_END) {
                return true;
            }

            // Search the next expression character
            exprpos = ++charpos;

            while (expr[charpos] >= 0) {
                charpos++;
            }

//            int prevchr = exprchr;

            exprchr = expr[charpos];

            offset = indexOfArray(expr, exprpos, charpos, buff, buffpos);

            if (offset < 0) {
                return (false);
            }

            // Copy the data from the source buffer into the result buffer
            // to substitute the expression character
            while (buffpos < offset) {
                rslt[rsltpos++] = buff[buffpos++];
            }

            rsltpos = 0;
        }
    }

    private boolean matchArray(int[] r, int rpos, int rend, char[] d, int dpos) {
        if ((d.length - dpos) < (rend - rpos)) {
            return (false);
        }

        for (int i = rpos; i < rend; i++) {
            if (r[i] != d[dpos++]) {
                return (false);
            }
        }

        return (true);
    }

    private int indexOfArray(int[] r, int rpos, int rend, char[] d, int dpos) {
        // Check if pos and len are legal
        if (rend < rpos) {
            throw new IllegalArgumentException("rend < rpos");
        }

        // If we need to match a zero length string return current dpos
        if (rend == rpos) {
            return (d.length); //?? dpos?
        }

        // If we need to match a 1 char length string do it simply
        if ((rend - rpos) == 1) {
            // Search for the specified character
            for (int x = dpos; x < d.length; x++) {
                if (r[rpos] == d[x]) {
                    return (x);
                }
            }
        }

        // Main string matching loop. It gets executed if the characters to
        // match are less then the characters left in the d buffer
        while (((dpos + rend) - rpos) <= d.length) {
            // Set current startpoint in d
            int y = dpos;

            // Check every character in d for equity. If the string is matched
            // return dpos
            for (int x = rpos; x <= rend; x++) {
                if (x == rend) {
                    return (dpos);
                }

                if (r[x] != d[y++]) {
                    break;
                }
            }

            // Increase dpos to search for the same string at next offset
            dpos++;
        }

        // The remaining chars in d buffer were not enough or the string
        // wasn't matched
        return (-1);
    }
    


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("/detail/getDetail", "/detail/getDetail");
//        map.put("/a/*", "/a/*");
//        map.put("/a/b/*", "/a/b/*");

        WlMatcher matcher = WlMatcher.getInstance(map);
//        matcher.execute(map, "/a/b");
//        matcher.execute(map, "/a/c");
//        matcher.execute(map, "/a/b/c");
        matcher.execute(map, "/detail/getDetail/v2");
        
    }
}
