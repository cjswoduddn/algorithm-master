package backjoon.stepbystep.n1904;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] buf = new int[1000001];
    static int MOD = 15746;
    public static void main(String[] args) throws Throwable{
        buf[1] = 1;
        buf[2] = 2;

        for(int i = 3; i <= 1000000; i++){
            buf[i] = buf[i-2]+buf[i-1];
            buf[i] %= MOD;
        }

        int k = Integer.parseInt(br.readLine());
        bw.write(buf[k]+"\n");
        bw.flush();

    }
}
