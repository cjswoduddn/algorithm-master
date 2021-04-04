package backjoon.stepbystep.n15649;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static boolean[] ck = new boolean[8];
    static Stack<Integer> q = new Stack<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n ,m;

    {
        Arrays.fill(ck, false);
    }
    public static void main(String[] args) throws Throwable {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        recur(0);
        bw.flush();
    }

    static void recur(int cur) throws Throwable{
        if(cur == m){
            for(int ptr : q){
                bw.write(ptr+" ");
            }
            bw.write("\n");
            return;
        }

        for(int i = 0; i < n; i++){
            if(ck[i] == true) continue;
            ck[i] = true;
            q.add(i+1);
            recur(cur+1);
            q.pop();
            ck[i] = false;
        }
    }
}
