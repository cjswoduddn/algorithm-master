package backjoon.stepbystep.n11053;


import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    static List<Integer> arr = new ArrayList<>(1000);
    static int[] buf = new int[1000];

    public static void main(String[] args) throws Throwable {

        int k = Integer.parseInt(br.readLine());
        Arrays.fill(buf, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int ret = 0;
        for(int i = 1; i < k; i++){
            for(int j = 0; j < i; j++){
                if(arr.get(i) > arr.get(j)){
                    buf[i] = Math.max(buf[i], buf[j]+1);
                }
            }
            ret = Math.max(ret, buf[i]);
        }
        bw.write(ret+"\n");
        bw.flush();
    }
}
