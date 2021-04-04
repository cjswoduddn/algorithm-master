package programmers.kakao2018blind.n1;

public class Solution {

    static int n;

    public String[] solution(int n, int[] m1, int[] m2){
        this.n = n;
        String[] ans = {};
        for(int i = 0; i < n; i++){
            String str = converter(m1[i], m2[i]);
            System.out.println(str);
        }
        return ans;
    }

    private String converter(int n1, int n2) {
        int num = n1|n2;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int bit = 1<<i;
            if((num&bit) > 0) sb.append('#');
            else sb.append(' ');
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
    }
}
