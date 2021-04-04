package backjoon.stepbystep.n14888;


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

    static List<Integer> nums = new ArrayList<>();
    static List<Integer> opers = new ArrayList<>();
    static List<Integer> pems = new ArrayList<>();
    static boolean[] check;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Throwable {
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < t; i++){
            nums.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++){
                opers.add(i);
            }
        }

        check = new boolean[opers.size()];
        Arrays.fill(check, false);

        permutation(0);

        bw.write(max+"\n"+min+"\n");
        bw.flush();
    }

    private static void permutation(int cnt) {
        if(cnt == opers.size()){
            calculate();
            return;
        }

        for(int i = 0; i < opers.size(); i++){
            if(check[i]) continue;
            check[i] = true;
            pems.add(opers.get(i));
            permutation(cnt+1);
            check[i] = false;
            pems.remove(pems.size()-1);
        }
    }

    private static void calculate() {
        int num = nums.get(0);
        for(int i = 0; i < pems.size(); i++){
            num = innerCalc(num, nums.get(i+1), pems.get(i));
        }

        max = Math.max(max, num);
        min = Math.min(min, num);
    }

    private static int innerCalc(int n1, int n2, int oper){
        switch (oper){
            case 0:
                n1 = n1+n2;
                break;
            case 1:
                n1 = n1-n2;
                break;
            case 2:
                n1 = n1*n2;
                break;
            case 3:
                n1 = n1/n2;
                break;
        }
        return n1;
    }
}
