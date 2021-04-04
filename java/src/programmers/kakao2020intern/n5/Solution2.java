package programmers.kakao2020intern.n5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
// 07 ~
public class Solution2 {

    List<ArrayList<Integer>> graph = new ArrayList<>();
    List<ArrayList<Integer>> scc = new ArrayList<>();

    Stack<Integer> stack = new Stack<>();
    int scnt = 0;
    int dcnt = 0;

    int[] dfsn;
    int[] sccid;
    int[] parent;

    public boolean solution(int n, int[][] path, int[][] order){
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        dfsn = new int[n];
        sccid = new int[n];
        parent = new int[n];
        Arrays.fill(dfsn, -1);
        Arrays.fill(sccid, -1);

        for(int i = 0; i < path.length; i++){
            graph.get(path[i][0]).add(path[i][1]);
            graph.get(path[i][1]).add(path[i][0]);
        }

        getParent(0, -1);
        for(int i = 0; i < order.length; i++){
            graph.get(order[i][0]).add(order[i][1]);
        }

        for(int i = 0; i < n; i++){
            if(dfsn[i] == -1) dfs(i);
        }


        for(int i = 0; i < scnt; i++){
            if(scc.get(i).size() > 1) return false;
        }
        return true;
    }

    private void getParent(int cur, int pre) {
        List<Integer> list = graph.get(cur);
        for(int i = 0; i < list.size(); i++){
            int nnod = list.get(i);
            if(nnod == pre) continue;
            parent[nnod] = cur;
            getParent(nnod, cur);
        }
    }

    private int dfs(int cur) {
        stack.push(cur);
        int ret = dfsn[cur] = dcnt++;
        List<Integer> list = graph.get(cur);
        for(int i = 0; i < list.size(); i++){
            int nnod = list.get(i);
            if(nnod == parent[cur]) continue;
            if(dfsn[nnod] == -1){
                ret = Math.min(ret, dfs(nnod));
            }else if(sccid[nnod] == -1){
                ret = Math.min(ret, dfsn[nnod]);
            }
        }

        if(ret == dfsn[cur]){
            int u = 0;
            ArrayList<Integer> tmp = new ArrayList<>();
            do{
                u = stack.pop();
                sccid[u] = scnt;
                tmp.add(u);
            }while(u != cur);
            scc.add(tmp);
            scnt++;
        }
        return ret;
    }
}
