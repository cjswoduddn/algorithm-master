package kakaocommerce.n2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15 ~ 27
public class Solution {

    List<List<Integer>> needs = new ArrayList<>();

    int n, m, r;
    boolean[] check;

    public int solution(int[][] needs, int r){
        init(needs, r);
        return solve(0, 0);
    }

    private int solve(int s, int cnt) {
        if(cnt == r){
            int ret = 0;
            for(int i = 0; i < n; i++){
                List<Integer> arr = needs.get(i);
                int tmp = 0;
                for(int j = 0; j < arr.size(); j++){
                    if(check[arr.get(j)]) tmp++;
                }
                if(tmp == arr.size()) ret++;
            }
            return ret;
        }

        int ret = 0;
        for(int i = s; i < check.length; i++){
            check[i] = true;
            ret = Math.max(ret, solve(i+1, cnt+1));
            check[i] = false;
        }
        return ret;
    }

    private void init(int[][] needs, int r) {
        n = needs.length;
        m = needs[0].length;
        this.r = r;

        check = new boolean[m];
        Arrays.fill(check, false);

        for(int i = 0; i < n; i++){
            this.needs.add(new ArrayList<>());
            for(int j = 0; j < m; j++){
                if(needs[i][j] == 1)
                    this.needs.get(i).add(j);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1}}, 2);
        System.out.println(ret);
    }
}
