package programmers.kakao2019blind.n6;

//50 ~
public class Solution {

    long[] ftimes;
    long K;

    public int solution(int[] ft, long k){
        int ans = 0;
        K = k;

        ftimes = new long[ft.length];
        for(int i = 0; i < ft.length; i++){
            ftimes[i] = ft[i];
        }

        long max = recur(0, k);
        System.out.println(max);
        for(int i = 0; i < ft.length; i++){
            long tmp = max > ftimes[i] ? ftimes[i] : max;
            ftimes[i] -= tmp;
            K -= tmp;
        }

        while(K > 0 && ans < ftimes.length){
            if(ftimes[ans++] != 0) K--;
        }
        while(ans < ftimes.length && ftimes[ans] == 0) ans++;
        if(ans == ftimes.length) return -1;
        return ans+1;
    }

    long recur(long s, long e){
        if(s > e) return -1;

        long mid = (s+e)/2;
        long times = 0;
        for(int i = 0; i < ftimes.length; i++){
            times += ftimes[i] > mid ? mid : ftimes[i];
        }

        if(times > K) return recur(s, mid-1);
        long tmp = recur(mid+1, e);
        return tmp != -1 ? tmp : mid;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(new int[]{4,2,3,6,7,1,5,8}, 16);
        System.out.println(ret);

    }
}
