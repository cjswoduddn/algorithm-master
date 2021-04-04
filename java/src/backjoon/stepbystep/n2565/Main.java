package backjoon.stepbystep.n2565;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    static int k;
    static int[] list;
    static int[] buf;
    public static void main(String[] args) throws Throwable {
        k = Integer.parseInt(br.readLine());
        list = new int[500];
        buf = new int[500];
        Arrays.fill(list, -1);
        Arrays.fill(buf, 1);

        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken())-1;

            list[left] = right;
        }

        bw.write(getLIS(list,buf)+"\n");
        bw.flush();
    }

    static int getLIS(int[] list, int[] buf){
        int ret = 1;
        for(int i = 1; i < 100; i++){
            if(list[i] == -1) continue;
            for(int j = 0; j < i; j++){
                if(list[j] == -1) continue;
                if(list[i] > list[j]){
                    buf[i] = Math.max(buf[i], buf[j]+1);
                }
            }
            ret = Math.max(ret, buf[i]);
        }
        return k-ret;
    }
}
