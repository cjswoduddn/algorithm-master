package programmers.kakao2019intern.n5;

import java.util.*;

public class Solution {

    public int solution(int[] stones, int k){
        return recur(stones, k, 1, 200000000);
    }

    private int recur(int[] stones, int k, int s, int e) {
        if(s > e) return -1;
        int mid = (s+e)/2;

        int ret = 0;
        int cnt = 0;
        for(int i = 0; i < stones.length; i++){
            int tmp = stones[i]-mid;
            if(tmp < 0){
                cnt++;
            }else{
                ret = Math.max(ret, cnt);
                cnt = 0;
            }
        }
        ret = Math.max(ret, cnt);

        if(ret < k) {
            int tmp = recur(stones, k, mid+1, e);
            if(tmp == -1) return mid;
            return tmp;
        }
        return recur(stones, k, s, mid-1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
        System.out.println(k);
    }
}
