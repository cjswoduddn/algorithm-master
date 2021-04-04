package programmers.kakao2018blind.n2;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 45 ~ 59
public class Solution2 {

    String res;
    Pattern pattern;
    Matcher matcher;
    public int solution(String res){
        init(res);
        return solve();
    }

    private int solve() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while(matcher.find()){
            int num = Integer.parseInt(matcher.group(1));
            char point = matcher.group(2).charAt(0);
            num = getPoint(num, point);
            if(matcher.group(3).equals("*")){
                stack.push(stack.pop()*2);
                num *= 2;
            }else if(matcher.group(3).equals("#")){
                num *= -1;
            }
            stack.push(num);
        }
        int ret = 0;
        while(!stack.isEmpty()) ret += stack.pop();
        return ret;
    }

    private int getPoint(int num, char point) {
        switch (point){
            case 'D':
                num = num*num;
                break;
            case 'T':
                num = (int)Math.pow(num, 3);
        }
        return num;
    }

    private void init(String res) {
        pattern = Pattern.compile("(\\d+)([SDT])([\\*#]?)");
        matcher = pattern.matcher(res);
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution("1D2S#10S");
    }
}
