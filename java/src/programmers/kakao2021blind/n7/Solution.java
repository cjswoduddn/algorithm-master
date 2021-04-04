package programmers.kakao2021blind.n7;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int INF = Integer.MAX_VALUE;
    int[] sales;
    List<ArrayList<Integer>> graph = new ArrayList<>();
    int size;
    int[][] dp;

    public int solution(int[] sale, int[][] links){
        init(sale, links);
        return Math.min(recur(1, 0), recur(1, 1));
    }

    private int recur(int cur, int state) {
        if(dp[cur][state] != INF) return dp[cur][state];
        List<Integer> list = graph.get(cur);

        int ret = INF;
        if(state == 0){ // 나를 포함하지 않은 상태 애새끼들 중 하나는 반드시 포함 할 것
            for(int i = 0; i < list.size(); i++){
                int must = list.get(i);
                int tmp = 0;
                for(int j = 0; j < list.size(); j++){
                    int nnod = list.get(j);
                    if(must == nnod) tmp += recur(nnod, 1);
                    else tmp += Math.min(recur(nnod, 0), recur(nnod, 1));
                }
                ret = Math.min(ret, tmp);
            }
            if(list.size() == 0) ret = 0;
        }else{
            ret = sales[cur];
            for(int i = 0; i < list.size(); i++){
                int nnod = list.get(i);
                ret += Math.min(recur(nnod, 0), recur(nnod, 1));
            }
        }
//        System.out.println(cur+" "+state+" "+ret);
        return dp[cur][state] = ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{5, 6, 5, 1, 4}, new int[][]{{2, 3}, {1, 4}, {2, 5}, {1, 2}});
    }

    private void init(int[] sale, int[][] links) {
        size = sale.length;
        sales = new int[size+1];
        dp = new int[size+1][2];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = dp[i][1] = INF;
        }
        for(int i = 0; i < sale.length; i++){
            sales[i+1] = sale[i];
        }
        for(int i = 0; i <= size; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < links.length; i++){
            graph.get(links[i][0]).add(links[i][1]);
        }

    }
}
