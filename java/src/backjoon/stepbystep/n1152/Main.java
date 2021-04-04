package backjoon.stepbystep.n1152;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int ret = 0;
        while(st.hasMoreTokens()){
            st.nextToken();
            ret++;
        }
        bw.write(ret+"\n");
        bw.flush();
    }

}
