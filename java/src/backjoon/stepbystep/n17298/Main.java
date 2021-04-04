package backjoon.stepbystep.n17298;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Integer>  stk = new Stack<>();
    static List<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws Throwable {
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int[] ret = new int[k];
        Arrays.fill(ret, -1);

        for(int i = 0; i < k; i++){
            if(stk.empty()){
                stk.push(i);
            }else {
                while(arr.get(stk.peek()) < arr.get(i)){
                    ret[stk.pop()] = arr.get(i);
                }
                stk.push(i);
            }
        }

        for(int i = 0; i < k; i++){
            bw.write(ret[i]+" ");
        }
        bw.flush();
    }

}