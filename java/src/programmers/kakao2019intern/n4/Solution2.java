package programmers.kakao2019intern.n4;


// 15 ~ 34
public class Solution2 {
    // 연속된 0의 길이가 k미만인 인원 중 최대 + 1

    int[] stones;
    int limit;

    public int solution(int[] sts, int k){
        init(sts, k);
        return recur(1, 200000000);
    }

    int recur(int s, int e){
        if(s > e) return -1;
        int mid = (s+e)/2;
        String str = "";
        int res = 0;
        int count = 0;
        for(int i = 0; i < stones.length; i++){
            int k = stones[i] > mid ? stones[i]-mid : 0;
            if(k == 0) count++;
            else{
                res = Math.max(res, count);
                count = 0;
            }
        }
        res = Math.max(res, count);

        if(res < limit) return Math.max(mid + 1, recur(mid + 1, e));
        return recur(s, mid-1);
    }

    private void init(int[] sts, int k) {
        stones = sts;
        limit = k;
    }
}
