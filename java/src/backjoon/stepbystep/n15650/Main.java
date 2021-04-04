package backjoon.stepbystep.n15650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean[] ck = new boolean[8];
    static Stack<Integer> q = new Stack<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n ,m;
    public static void main(String[] args) throws Throwable {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        recur(1, 0);
        bw.flush();
    }

    static void recur(int s, int cnt) throws Throwable{
        if(cnt == m){
            for(int ptr : q){
                bw.write(ptr+" ");
            }
            bw.write("\n");
            return;
        }

        for(int i = s; i <= n; i++){
            q.push(i);
            recur(i+1, cnt+1);
            q.pop();
        }
    }

}
