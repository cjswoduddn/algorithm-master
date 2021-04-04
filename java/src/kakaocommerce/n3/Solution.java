package kakaocommerce.n3;

import java.util.ArrayList;
import java.util.List;

// 28 ~
public class Solution {

    int n;
    int[] cost;     // return 값이 int이기 때문에 int범위를 넘는 오버플로우는 발생할 수 있지만 고려하지 않겠습니다
    List<List<Integer>> tree = new ArrayList<>();

    public int[] solution(int n, int[] passenger, int[][] train){
        init(n, passenger, train);
        return solve();
    }

    private int[] solve() {
        dfs(1, 0, 0);
        int num = 0;
        int value = 0;
        for(int i = 0; i < cost.length; i++){
            if(cost[i] >= value){
                num = i;
                value = cost[i];
            }
        }
        return new int[]{num, value};
    }

    private void dfs(int cur, int pre, int preCost) {
        cost[cur] += preCost;
        List<Integer> list = tree.get(cur);
        for(int i = 0; i < list.size(); i++){
            int nnod = list.get(i);
            if(nnod == pre) continue;
            dfs(nnod, cur, cost[cur]);
        }
    }

    private void init(int n, int[] passenger, int[][] train) {
        this.n = n;
        cost = new int[n+1];
        for(int i = 0; i < passenger.length; i++){
            cost[i+1] = passenger[i];
        }

        for(int i = 0; i <= n; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < train.length; i++){
            int u = train[i][0];
            int v = train[i][1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
    }
}
