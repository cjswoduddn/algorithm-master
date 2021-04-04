package programmers.kakao2020blind.n2;

import java.util.Stack;

// 20 ~
public class Solution {

    boolean isBalanced(String str){
        int left = 0;
        int right = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(') left++;
            else right++;
        }
        if(left == right) return true;
        return false;
    }

    boolean isCorrect(String str){
        if(str.length() == 0) return true;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(') stack.push(1);
            else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }

    public String solution(String p){
        if(isCorrect(p)) return p;
        int idx = 1;
        while(!isBalanced(p.substring(0, idx))) idx++;
        if(isCorrect(p.substring(0, idx))){
            return p.substring(0, idx)+solution(p.substring(idx));
        }
        return "("+solution(p.substring(idx))+")"+convertString(p.substring(1, idx-1));
    }

    private String convertString(String str) {
        String ret = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(') ret += ")";
            else ret += "(";
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ret = solution.solution("");
        System.out.println(ret);
    }
}
