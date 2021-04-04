package programmers.kakao2018blind.n9;

public class Solution {

    public String bitString(int bit, int k){
        StringBuilder sb = new StringBuilder();
        while(k > 0){
            int mod = k%bit;
            k /= bit;
            if(mod >= 10) mod = mod-10+'A';
            else mod += '0';
            sb.append((char)mod);
        }
        return sb.reverse().toString();
    }

    public String solution(int n, int t, int m, int p){
        String str = "0";
        int idx = 1;
        while(str.length() < 1000000){
            str = str+bitString(n, idx++);
        }
        System.out.println(str);

        String ret = "";
        for(int i = 0; i < t; i++){
            ret += str.charAt(i*m+(p-1));
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ret = solution.solution(16, 16, 2, 1);
        System.out.println(ret);
    }
}
