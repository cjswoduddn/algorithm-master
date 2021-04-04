package backjoon.stepbystep.n1427;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Throwable{
        String str = br.readLine();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String str2 = new String(chars);
        StringBuilder builder = new StringBuilder(str2);
        StringBuilder reverse = builder.reverse();
        bw.write(reverse.toString());
        bw.flush();
    }
}
