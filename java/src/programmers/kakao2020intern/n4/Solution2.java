package programmers.kakao2020intern.n4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// 50 ~ 65
public class Solution2 {

    static class Nod{
        int x, y, state, cost;

        public Nod(int x, int y, int state, int cost) {
            this.x = x;
            this.y = y;
            this.state = state;
            this.cost = cost;
        }
    };

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    int[][] map;
    int size;

    public int solution(int[][] bd){
        size = bd.length;
        map = new int[size][size];
        for(int i = 0; i < size; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        Queue<Nod> queue = new ArrayDeque<>();
        queue.add(new Nod(0, 0, 0, 0));

        while(!queue.isEmpty()){
            Nod cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                if(bd[nx][ny] == 1) continue;
                int ncost = cur.cost;
                int nstate = 0;
                if(i == 0 || i == 1){
                    nstate = 2;
                    if(cur.state == 1) ncost += 600;
                    else ncost += 100;
                }else if(i == 2 || i == 3){
                    nstate = 1;
                    if(cur.state == 2) ncost += 600;
                    else ncost += 100;
                }
                if(ncost < map[nx][ny]){
                    map[nx][ny] = ncost;
                    queue.add(new Nod(nx, ny, nstate, ncost));
                }
            }
        }
        return map[size-1][size-1];
    }
}
