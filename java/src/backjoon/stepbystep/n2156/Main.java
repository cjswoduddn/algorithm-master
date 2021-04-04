package backjoon.stepbystep.n2156;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static List<Integer> arr = new ArrayList<>(10000);
    static int[][] buf = new int[3][10000];

    public static void main(String[] args) throws Throwable{
        for(int[] tmp : buf){
            Arrays.fill(tmp, -1);
        }

        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        bw.write(recur(0, 0)+"\n");
        bw.flush();
    }

    static int recur(int state, int step){
        if(step >= arr.size()) return 0;
        if(buf[state][step] != -1) return buf[state][step];

        int noDrink = recur(0, step+1);
        int drink = 0;
        if(state < 2){
            drink = recur(state+1, step+1)+arr.get(step);
        }

        return buf[state][step] = Math.max(noDrink, drink);
    }
}
