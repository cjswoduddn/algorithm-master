package backjoon.stepbystep.n2941;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String regex = "(c=|c-|dz=|d-|lj|nj|s=|z=)";

        String src = br.readLine();
        String str = src.replaceAll(regex, "A");
        bw.write(str.length()+"\n");
        bw.flush();
    }
}
