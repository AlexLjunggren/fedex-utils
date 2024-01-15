package io.ljunggren.fedexUtils.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static String readResourceFile(Class<?> clazz, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = clazz.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static String readResourceFileWithAppendedSpace(Class<?> clazz, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = clazz.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(" ");
        }
        return sb.toString();
    }

}
