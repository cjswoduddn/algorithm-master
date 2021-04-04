package programmers.kakao2020intern.n5;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.*;

/**
 * scc를 이용해 싸이클 단위로 노드를 묶을 수 있다
 * scc를 이용해 위에서 묶은 노드를 위상정렬할 수 있다.
 */
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    static Stack<Integer> stack = new Stack<>();
    static List<ArrayList<Integer>> g = new ArrayList<>();
    static int V, E;

    static List<ArrayList<Integer>> scc = new ArrayList<>();

    static int dcnt, scnt;
    static int[] dfsn = new int[10001];
    static int[] sccid = new int[10001];

    static int dfs(int cur){
        stack.push(cur);
        int ret = dfsn[cur] = ++dcnt;
        for(int i = 0; i < g.get(cur).size(); i++){
            int nnod = g.get(cur).get(i);
            if(dfsn[nnod] == -1){
                ret = Math.min(ret, dfs(nnod));
            }else if(sccid[nnod] == -1){
                ret = Math.min(ret, dfsn[nnod]);
            }
        }

        if(ret == dfsn[cur]){
            int nod;
            ArrayList<Integer> arr = new ArrayList<>();
            do{
                nod = stack.pop();
                arr.add(nod);
                sccid[nod] = scnt;
            }while(nod != cur);
            scc.add(arr);
            scnt++;
        }
        return ret;
    }

    public static void main(String[] args) throws Throwable {
        Arrays.fill(dfsn, -1);
        Arrays.fill(sccid, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i < V; i++){
            g.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g.get(u).add(v);
        }

        for(int i = 0; i < V; i++){
            if(dfsn[i] == -1) dfs(i);
        }

        for(int i = 0; i < scnt; i++){
            scc.get(i).sort(Integer::compareTo);
        }

        scc.sort((a, b)->{
            if(a.get(0) > b.get(0)) return 1;
            return -1;
        });

        bw.write(scnt+"\n");
        for(List<Integer> arr : scc){
            for(int ptr : arr){
                bw.write(ptr+" ");
            }
            bw.write(-1+"\n");
        }

        bw.flush();
    }

}
