package io.kloudwork.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostParser {
    public static Map<String, String> parse(String postBody) {
        Map<String, String> postParams = new HashMap<>();

        Pattern pattern = Pattern.compile("\"(.+)\"\r\n\r\n(.+)\r\n");
        Matcher matcher = pattern.matcher(postBody);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            postParams.put(key,value);
        }
        return postParams;
    }
}
