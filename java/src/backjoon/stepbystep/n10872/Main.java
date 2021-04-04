package backjoon.stepbystep.n10872;

import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());

        Long ret = 1L;
        for(int i = 2; i <= k; i++){
            ret *= i;
        }


        bw.write(ret+"\n");
        bw.flush();
    }

}
