package programmers.kakao2018blind.n2;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public int solution(String res){
        Pattern pattern = Pattern.compile("(\\d+)([SDT])([#\\*]?)");
        Matcher matcher = pattern.matcher(res);

        Stack<Integer> stack = new Stack<>();

        while(matcher.find()){
            int num = Integer.parseInt(matcher.group(1));
            char c = matcher.group(2).charAt(0);
            num = process(num, c);
            if(matcher.group(3).equals("#")){
                num *= -1;
            }else if(matcher.group(3).equals("*")){
                if(!stack.isEmpty()){
                    stack.push(stack.pop()*2);
                }
                num *= 2;
            }
            stack.push(num);
        }

        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }

    private int process(int num, char c) {
        switch (c){
            case 'D':
                num = num*num;
                break;
            case 'T':
                num = num*num*num;
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution("1S2D*3T");
        System.out.println(ret);
    }
}
