package backjoon.stepbystep.n2213;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    static int[] cost;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args) throws Throwable {
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2];
        for(int i = 0; i <= n; i++){
            dp[i][0] = dp[i][1] = -1;
        }
        cost = new int[n+1];
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        bw.write(recur(getRoot(), 0, 0)+"\n");       // cur, pre, prestate 0 is noinc 1 is inc
        bw.flush();
    }


    private static int recur(int root, int pre, int state) {
        if(dp[root][state] != -1) return dp[root][state];

        int left = 0;
        for(int i = 0; i < graph.get(root).size(); i++){
            if(graph.get(root).get(i) == pre) continue;
            left += recur(graph.get(root).get(i), root, 0);
        }

        int right = 0;
        if(state == 0){
            right = cost[root];
            for(int i = 0; i < graph.get(root).size(); i++){
                if(graph.get(root).get(i) == pre) continue;
                right += recur(graph.get(root).get(i), root, 1);
            }

        }
        return dp[root][state] = Math.max(left, right);
    }

    private static int getRoot() {
        int[] topo = new int[graph.size()];
        for(int i = 1; i < graph.size(); i++){
            for(int j = 0; j < graph.get(i).size(); j++){
                topo[graph.get(i).get(j)]++;
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < topo.length; i++){
            min = Math.min(min, topo[i]);
        }
        for(int i = 1; i < topo.length; i++){
            if(topo[i] == min) return i;
        }
        return 0;
    }


}
