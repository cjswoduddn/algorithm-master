package backjoon.stepbystep.n15681;

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

    static int[] arr;
    static List<ArrayList<Integer>> g = new ArrayList<>();
    public static void main(String[] args) throws Throwable {
        int n, r, q;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        Arrays.fill(arr ,1);

        for(int i = 0; i <= n; i++){
            g.add(new ArrayList<>());
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g.get(u).add(v);
            g.get(v).add(u);
        }

        dfs(r, 0);
        for(int i = 0; i < q; i++){
            int u = Integer.parseInt(br.readLine());
            bw.write(arr[u]+"\n");
        }


        bw.flush();
    }

    private static int dfs(int cur, int pre) {
        for(int i = 0; i < g.get(cur).size(); i++){
            int nnod = g.get(cur).get(i);
            if(nnod == pre) continue;
            arr[cur] += dfs(nnod, cur);
        }
        return arr[cur];
    }
}
