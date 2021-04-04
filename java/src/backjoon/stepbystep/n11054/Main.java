package backjoon.stepbystep.n11054;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static List<Integer> arr = new ArrayList<>(1000);
    static List<Integer> brr = new ArrayList<>(1000);
    static int[] buf = new int[1000];
    static int[] rbuf = new int[1000];

    public static void main(String[] args) throws Throwable {
        int k = Integer.parseInt(br.readLine());
        Arrays.fill(buf, 1);
        Arrays.fill(rbuf, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            arr.add(Integer.parseInt(st.nextToken()));
            brr.add(0);
        }

        Collections.copy(brr, arr);
        Collections.reverse(brr);

        getLIS(arr, buf);
        getLIS(brr, rbuf);

        bw.write(getAns()+"\n");

        bw.flush();
    }

    private static int getAns() {
        if(arr.size() == 1) return 1;
        int ret = 2;
        for(int i = 0; i < arr.size()-1; i++){
            int tmp = buf[i]+rbuf[arr.size()-1-i];
            ret = Math.max(ret, tmp);
        }
        return ret-1;
    }

    private static void getLIS(List<Integer> arr, int[] buf) {
        for(int i = 1; i < arr.size(); i++){
            for(int j = 0; j < i; j++){
                if(arr.get(i) > arr.get(j)){
                    buf[i] = Math.max(buf[i], buf[j]+1);
                }
            }
        }
    }
}
