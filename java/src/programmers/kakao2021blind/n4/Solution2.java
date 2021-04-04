package programmers.kakao2021blind.n4;

import java.util.Arrays;

public class Solution2 {

    static int INF = Integer.MAX_VALUE;
    int size;
    int snod;
    int ta, tb;
    int[][] map;

    public int solution(int n, int s, int a, int b, int[][] fares){
        init(n, s, a, b, fares);
        return solve();
    }

    private int solve() {
        doPloide(map);
        int ret = INF;
        for(int i = 1; i <= size; i++){
            if(map[snod][i] == INF || map[i][ta] == INF || map[i][tb] == INF) continue;
            ret = Math.min(ret, map[snod][i]+map[i][ta]+map[i][tb]);
        }
        return ret;
    }

    private void doPloide(int[][] map) {
        for(int k = 1; k < size; k++){
            for(int i = 1; i <= size; i++){
                for(int j = 1; j <= size; j++){
                    if(map[i][k] == INF || map[k][j] == INF) continue;
                    map[i][j] = Math.min(map[i][k]+map[k][j], map[i][j]);
                }
            }
        }
    }

    private void init(int n, int s, int a, int b, int[][] fares) {
        size = n;
        snod = s;
        ta = a; tb = b;
        map = new int[size+1][size+1];
        for(int i = 1; i <= size; i++){
            Arrays.fill(map[i], INF);
        }
        for(int i = 1; i <= size; i++){
            map[i][i] = 0;
        }

        for(int i = 0; i < fares.length; i++){
            int x = fares[i][0];
            int y = fares[i][1];
            map[x][y] = map[y][x] = fares[i][2];
        }
    }
}
