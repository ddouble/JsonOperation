/**
 * Output org.json JSONObject or JSONArray to console
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class JSONPrinter {

    int level = 0;
    private final String indent = "    ";
    private boolean isEndOfList = true;

    /**
     * Print formated json to console
     * for org.json.* library
     *
     * @param json
     */
    public void print(Object json) {

        if (json instanceof JSONObject jsonObject) {
            System.out.printf("%s{%n", indent.repeat(level));
            level++;

            List<String> keys = new ArrayList<>(jsonObject.keySet().stream().toList());
            Collections.reverse(keys);
            Iterator<String> it = keys.iterator();
            boolean preIsEndOfList = isEndOfList;
            while (it.hasNext()) {
                String key = it.next();
                Object v = jsonObject.get(key);

                isEndOfList = !it.hasNext();
                if (v instanceof JSONObject || v instanceof JSONArray) {
                    System.out.printf("%s%s: %n", indent.repeat(level), key);
                    print(v);
                } else {
                    System.out.printf("%s%s: ", indent.repeat(level), key);
                    print(v);
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            System.out.printf("%s}%s%n", indent.repeat(level), isEndOfList ? "" : ",");
        } else if (json instanceof JSONArray jsonArray) {
            System.out.printf("%s[%n", indent.repeat(level));
            level++;
            boolean preIsEndOfList = isEndOfList;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object v = jsonArray.get(i);
                isEndOfList = (i == (jsonArray.length() - 1));
                if (v instanceof JSONObject || v instanceof JSONArray) {
                    print(v);
                } else {
                    System.out.printf("%s", indent.repeat(level));
                    print(v);
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            System.out.printf("%s]%s%n", indent.repeat(level), isEndOfList ? "" : ",");
        } else if (json instanceof String) {
            System.out.printf("\"%s\"%s%n", json.toString(), isEndOfList ? "" : ",");
        } else {
            System.out.printf("%s%s%n", json.toString(), isEndOfList ? "" : ",");
        }

    }

    /**
     * Serialize json object to string
     * for org.json.* library
     *
     * @param json
     * @return
     */
    public String serialize(Object json) {
        StringBuilder sb = new StringBuilder();

        if (json instanceof JSONObject jsonObject) {
            sb.append(String.format("%s{\n", indent.repeat(level)));
            level++;

            List<String> keys = new ArrayList<>(jsonObject.keySet().stream().toList());
            Collections.reverse(keys);
            Iterator<String> it = keys.iterator();
            boolean preIsEndOfList = isEndOfList;
            while (it.hasNext()) {
                String key = it.next();
                Object v = jsonObject.get(key);

                isEndOfList = !it.hasNext();
                if (v instanceof JSONObject || v instanceof JSONArray) {
                    sb.append(String.format("%s\"%s\": \n", indent.repeat(level), key));
                    sb.append(serialize(v));
                } else {
                    sb.append(String.format("%s\"%s\": ", indent.repeat(level), key));
                    sb.append(serialize(v));
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            sb.append(String.format("%s}%s\n", indent.repeat(level), isEndOfList ? "" : ","));
        } else if (json instanceof JSONArray jsonArray) {
            sb.append(String.format("%s[\n", indent.repeat(level)));
            level++;
            boolean preIsEndOfList = isEndOfList;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object v = jsonArray.get(i);
                isEndOfList = (i == (jsonArray.length() - 1));
                if (v instanceof JSONObject || v instanceof JSONArray) {
                    sb.append(serialize(v));
                } else {
                    sb.append(String.format("%s", indent.repeat(level)));
                    sb.append(serialize(v));
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            sb.append(String.format("%s]%s\n", indent.repeat(level), isEndOfList ? "" : ","));
        } else if (json instanceof String) {
            sb.append(String.format("\"%s\"%s\n", json.toString(), isEndOfList ? "" : ","));
        } else {
            sb.append(String.format("%s%s\n", json.toString(), isEndOfList ? "" : ","));
        }

        return sb.toString();
    }


    /**
     * Print formated json to console
     * for org.json.simple.* library
     *
     * @param json
     */
    public void print2(Object json) {

        if (json instanceof org.json.simple.JSONObject jsonObject) {
            System.out.printf("%s{%n", indent.repeat(level));
            level++;

            ArrayList<String> keys = new ArrayList<>(jsonObject.keySet().stream().toList());
            Collections.reverse(keys);
            Iterator<String> it = keys.iterator();
            boolean preIsEndOfList = isEndOfList;
            while (it.hasNext()) {
                String key = it.next();
                Object v = jsonObject.get(key);

                isEndOfList = !it.hasNext();
                if (v instanceof org.json.simple.JSONObject || v instanceof org.json.simple.JSONArray) {
                    System.out.printf("%s%s: %n", indent.repeat(level), key);
                    print2(v);
                } else {
                    System.out.printf("%s%s: ", indent.repeat(level), key);
                    print2(v);
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            System.out.printf("%s}%s%n", indent.repeat(level), isEndOfList ? "" : ",");
        } else if (json instanceof org.json.simple.JSONArray jsonArray) {
            System.out.printf("%s[%n", indent.repeat(level));
            level++;
            boolean preIsEndOfList = isEndOfList;
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                Object v = jsonArray.get(i);
                isEndOfList = (i == (len - 1));
                if (v instanceof org.json.simple.JSONObject || v instanceof org.json.simple.JSONArray) {
                    print2(v);
                } else {
                    System.out.printf("%s", indent.repeat(level));
                    print2(v);
                }
            }
            isEndOfList = preIsEndOfList;
            level--;
            System.out.printf("%s]%s%n", indent.repeat(level), isEndOfList ? "" : ",");
        } else if (json instanceof String) {
            System.out.printf("\"%s\"%s%n", json.toString(), isEndOfList ? "" : ",");
        } else {
            System.out.printf("%s%s%n", json.toString(), isEndOfList ? "" : ",");
        }

    }
}