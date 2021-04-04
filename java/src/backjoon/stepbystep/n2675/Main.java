package backjoon.stepbystep.n2675;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            String str = st.nextToken();

            for(char c : str.toCharArray()){
                for(int j = 0; j < value; j++){
                    bw.write(String.valueOf(c));
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }

}
