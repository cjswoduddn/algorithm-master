package programmers.kakao2020intern.n4;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int[][] dp = new int[25][25];
    static int size;

    public int solution(int[][] board){
        size = board.length;

        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp.length; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Nod> queue = new ArrayDeque<>();
        dp[0][0] = 0;
        queue.add(new Nod(0, 0, 0, 0));

        while(!queue.isEmpty()){
            Nod cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                if(board[nx][ny] == 1) continue;
                int nc = cur.value;
                if(dx[i] != 0){
                    if(cur.pre == 2) nc += 600;
                    else nc += 100;
                }else{
                    if(cur.pre == 1) nc += 600;
                    else nc += 100;
                }

                if(dp[nx][ny] >= nc){
                    dp[nx][ny] = nc;
                    if(dx[i] != 0){
                        queue.add(new Nod(nx, ny, 1, nc));
                    }else{
                        queue.add(new Nod(nx, ny, 2, nc));
                    }
                }
            }
        }
        return dp[size-1][size-1];
    }

    static class Nod{
        int x, y, pre;
        int value;

        public Nod(int x, int y, int pre, int value) {
            this.x = x;
            this.y = y;
            this.pre = pre;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}
        }));
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.printf("%10d ", dp[i][j]);
            }
            System.out.println();
        }
    }

}
