package backjoon.stepbystep.n1654;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer st;

    static long k;
    static int size;
    static List<Long> arr = new ArrayList<>();

    public static void main(String[] args) throws Throwable {
        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        for(int i = 0; i < size; i++){
            arr.add(Long.parseLong(br.readLine()));
        }

        bw.write(recur(1, Integer.MAX_VALUE)+"\n");
        bw.flush();
    }

    private static Long recur(long s, long e) {
        if(s > e) return -1L;
        long mid = (s+e)/2;
        long value = 0;
        for(int i = 0; i < size; i++){
            value += arr.get(i)/mid;
        }

        if(k <= value){
            return Math.max(mid, recur(mid+1, e));
        }
        return recur(s, mid-1);
    }
}
