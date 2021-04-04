package programmers.kakao2020blind.n4;

import java.util.Arrays;

// 5 ~
public class Solution {

    int[][] map;
    int size;

    public int[][] solution(int n, int[][] frames){
        size = n+1;
        map = new int[size][size];
        for(int i = 0; i < size; i++){
            Arrays.fill(map[i], -1);
        }

        for(int i = 0; i < frames.length; i++){
            int y = frames[i][0];
            int x = frames[i][1];
            int gibo = frames[i][2];
            int inde = frames[i][3];

            if(inde == 1 && canInsert(x, y, gibo)){
                if(map[x][y] == -1) map[x][y] = gibo;
                else map[x][y] = 2;
            }
        }
        return null;

    }

    private boolean canInsert(int x, int y, int gibo) {
        if(map[x][y] == gibo || map[x][y] == 2) return false;
        if(gibo == 0) return canGiInsert(x, y);
        return canBoInsert(x, y);
    }

    private boolean canBoInsert(int x, int y) {
        if(x == 0) return false;
        if(map[x-1][y] >= 0) return true;
        return false;
    }

    private boolean canGiInsert(int x, int y) {
        if(x == 0 || map[x-1][y] == 0) return true;
        if(map[x][y] >= 1 || (y-1 >= 0 && map[x][y-1] >= 1)) return true;
        return false;
    }


    private boolean canGiDelete(int x, int y) {
        if(x+1 == size || map[x+1][y] == -1) return true;
        return false;
    }
}
