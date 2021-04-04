package backjoon.stepbystep.n11720;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int ret = 0;
        for(int i = 0; i < num; i++){
            ret += str.charAt(i)-'0';
        }
        bw.write(ret+"\n");
        bw.flush();
    }
}
