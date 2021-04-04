package programmers.kakao2018blind.n1;

// 35 ~ 43
public class Solution2 {

    int n;
    int[] arr;
    int[] brr;

    public String[] solution(int n, int[] arr, int[] brr){
        init(n, arr, brr);
        return solve();
    }

    private String[] solve() {
        String[] strs = new String[n];
        for(int i = 0; i < n; i++){
            strs[i] = getString(i);
        }
        return strs;
    }

    private String getString(int k) {
        String ret = "";
        int num = arr[k]|brr[k];
        for(int i = n-1; i >= 0; i--){
            int bit = 1<<i;
            if((num&bit) > 0) ret += '#';
            else ret += ' ';
        }
        return ret;
    }

    private void init(int n, int[] arr, int[] brr) {
        this.n = n;
        this.arr = arr;
        this.brr = brr;
    }
}
