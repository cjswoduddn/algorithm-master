package programmers.kakao2019intern.n1;

import java.util.Stack;

public class Solution {

    static Stack<Integer> stack;
    static int[] height;
    static int size;

    static void init(int[][] board, int[] moves){
        size = board.length;
        stack = new Stack<>();
        height = new int[size];

        for(int i = 0; i < size; i++){
            int idx = 0;
            while(idx < size && board[idx][i] == 0) idx++;
            height[i] = idx;
        }
    }

    public int solution(int[][] board, int[] moves){
        init(board, moves);
        int ans = 0;

        for(int y : moves){
            y--;
            if(height[y] == size) continue;
            int item = board[height[y]++][y];
            if(stack.isEmpty()) stack.push(item);
            else{
                if(stack.peek() == item){
                    ans += 2;
                    stack.pop();
                }else{
                    stack.push(item);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = new int[]{1,5,3,5,1,2,1,4};
        Solution solution = new Solution();
        solution.solution(board, moves);
    }
}
