package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Converter {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Throwable{
        StringBuilder sb = new StringBuilder(br.readLine());

        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == '[') sb.setCharAt(i, '{');
            if(sb.charAt(i) == ']') sb.setCharAt(i, '}');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
