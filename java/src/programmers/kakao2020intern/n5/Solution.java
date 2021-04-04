package programmers.kakao2020intern.n5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution {

    static Stack<Integer> stack = new Stack<>();
    static int[] dfsn;
    static int[] sccid;

    static int dcnt, scnt;
    static ArrayList<ArrayList<Integer>> yg;
    static ArrayList<ArrayList<Integer>> g;
    static ArrayList<ArrayList<Integer>> scc;

    public boolean solution(int n, int[][] path, int[][] order){
        dfsn = new int[n];
        sccid = new int[n];
        scnt = dcnt = 0;
        Arrays.fill(dfsn, -1);
        Arrays.fill(sccid, -1);
        yg = new ArrayList<>();
        g = new ArrayList<>();
        scc = new ArrayList<>();

        for(int i = 0; i < n; i++){
            yg.add(new ArrayList<>());
            g.add(new ArrayList<>());
        }

        for(int[] pt : path){
            yg.get(pt[0]).add(pt[1]);
            yg.get(pt[1]).add(pt[0]);
        }

        dfs2(0, -1);

        for(int[] od : order){
            g.get(od[0]).add(od[1]);
        }

        for(int i = 0; i < n; i++){
            if(dfsn[i] == -1) dfs(i);
        }

        for(int i = 0; i < scnt; i++){
            if(scc.get(i).size() > 1) return false;
        }
        return true;
    }

    private void dfs2(int cur, int pre) {
        for(int i = 0; i < yg.get(cur).size(); i++){
            int nnod = yg.get(cur).get(i);
            if(nnod == pre) continue;
            g.get(cur).add(nnod);
            dfs2(nnod, cur);
        }
    }

    static int dfs(int cur){
        int ret = dfsn[cur] = ++dcnt;
        stack.push(cur);
        for(int i = 0; i < g.get(cur).size(); i++){
            int nnod = g.get(cur).get(i);
            if(dfsn[nnod] == -1){
                ret = Math.min(ret, dfs(nnod));
            }else if(sccid[nnod] == -1){
                ret = Math.min(ret, dfsn[nnod]);
            }
        }

        if(ret == dfsn[cur]){
            int k = 0;
            ArrayList<Integer> arr = new ArrayList<>();
            do{
                k = stack.pop();
                sccid[k] = scnt;
                arr.add(k);
            }while(k != cur);
            scnt++;
            scc.add(arr);
        }
        return ret;
    }


}
