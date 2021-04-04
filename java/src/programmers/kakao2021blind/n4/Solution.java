package programmers.kakao2021blind.n4;

public class Solution {

    int[][] map;

    static int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares){

        map = new int[n][n];
        s--;a--;b--;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) map[i][j] = 0;
                else map[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < fares.length; i++){
            int u = fares[i][0]-1;
            int v = fares[i][1]-1;
            int cost = fares[i][2];
            map[u][v] = map[v][u] = cost;
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(map[s][i] == Integer.MAX_VALUE || map[i][a] == Integer.MAX_VALUE || map[i][b] == Integer.MAX_VALUE)
                continue;
            int tmp = map[s][i]+map[i][a]+map[i][b];
            ret = Math.min(ret, tmp);
        }
        return ret;
    }
}
