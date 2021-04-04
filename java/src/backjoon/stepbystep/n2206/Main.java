package backjoon.stepbystep.n2206;

import static java.lang.System.in;
import static java.lang.System.out;
import java.io .*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer st;

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Throwable {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }

        Queue<Nod> queue = new ArrayDeque<>();
        queue.add(new Nod(0, 0, 1, 0));
        visited[0][0][0] = true;

        int ret = -1;
        while(!queue.isEmpty()){
            Nod cnod = queue.poll();
            int cx = cnod.x;
            int cy = cnod.y;
            int cdis = cnod.dist;
            int isUsed = cnod.crush; // 0 : 사용안함 1 : 사용함

            if(cx == N-1 && cy == M-1){
                ret = cdis;
                break;
            }
            for(int i = 0; i < 4; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(map[nx][ny] == 0 && !visited[nx][ny][isUsed]){
                    visited[nx][ny][isUsed] = true;
                    queue.add(new Nod(nx, ny, cdis+1, isUsed));
                }else if(map[nx][ny] == 1 && isUsed == 0 && !visited[nx][ny][1]){
                    visited[nx][ny][1] = true;
                    queue.add(new Nod(nx, ny, cdis+1, 1));
                }
            }
        }
        bw.write(ret+"\n");
        bw.flush();
    }

    static class Nod{
        int x, y;
        int dist;
        int crush;

        public Nod(int x, int y, int dist, int crush) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.crush = crush;
        }
    }
}
