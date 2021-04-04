package programmers.kakao2019intern.n1;

import java.util.Arrays;
import java.util.Stack;

// 28 ~ 38
public class Solution2 {

    int[] order;

    public int solution(int[][] bd, int[] moves){
        init(bd);
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < moves.length; i++){
            moves[i]--;
        }

        int ret = 0;
        for(int i = 0; i < moves.length; i++){
            int cur = moves[i];
            if(order[cur] == bd.length) continue;
            if(stack.peek() == bd[order[cur]][cur]){
                stack.pop();
                ret += 2;
            }else{
                stack.push(bd[order[cur]][cur]);
            }
            order[cur]++;
        }

        return ret;
    }

    private void init(int[][] bd) {
        order = new int[bd.length];
        Arrays.fill(order, 0);
        for(int j = 0; j < bd.length; j++){
            int idx = 0;
            while(idx < bd.length && bd[idx][j] == 0){
                order[j] = ++idx;
            }
        }
    }
}
