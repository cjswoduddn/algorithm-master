package programmers.kakao2021blind.n6;

import java.util.*;

public class Solution {

    static class Pair<P, K>{
        P x;
        K y;

        public Pair(P x, K y) {
            this.x = x;
            this.y = y;
        }
    }

    int size;
    int csize;
    List<List<Pair<Integer, Integer>>> cards = new ArrayList<>();
    int[][] map;
    boolean[] check;
    List<Integer> list = new ArrayList<>();
    int sx, sy;

    public int solution(int[][] bd, int x, int y){
        size = bd.length;
        csize = 0;
        sx = x; sy = y;
        map = bd;
        for(int i = 0; i <= 6; i++) cards.add(new ArrayList<>());

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(bd[i][j] == 0) continue;
                // 현재 카드의 좌표를 기억해두자 좌표 기반으로 탐색할 거임
                csize = Math.max(csize, bd[i][j]);
                cards.get(bd[i][j]).add(new Pair<>(i, j));
            }
        }

        check = new boolean[csize+1];
        Arrays.fill(check, false);
        return permutation(0);
    }

    private int permutation(int cnt) {
        if(cnt == csize){
            return nextPermutation(0, sx, sy);
        }

        int ret = Integer.MAX_VALUE;
        for(int i = 1; i <= csize; i++){
            if(check[i]) continue;
            check[i] = true;
            list.add(i);
            ret = Math.min(ret, permutation(cnt+1));
            list.remove(list.size()-1);
            check[i] = false;
        }
        return ret;
    }

    // idx에 해당하는 list를 0->1 1->0 둘 다 순회해보기
    private int nextPermutation(int idx, int x, int y) {
        if(idx == csize) return 0;

        List<Pair<Integer, Integer>> cur = cards.get(list.get(idx));
        // 0 -> 1
        int left = bfs(x, y, cur.get(0).x, cur.get(0).y);
        map[cur.get(0).x][cur.get(0).y] = 0;
        left += bfs(cur.get(0).x, cur.get(0).y, cur.get(1).x, cur.get(1).y);
        map[cur.get(1).x][cur.get(1).y] = 0;
        left += nextPermutation(idx+1, cur.get(1).x, cur.get(1).y);
        map[cur.get(0).x][cur.get(0).y] = list.get(idx);
        map[cur.get(1).x][cur.get(1).y] = list.get(idx);

        int right = bfs(x, y, cur.get(1).x, cur.get(1).y);
        map[cur.get(1).x][cur.get(1).y] = 0;
        right += bfs(cur.get(1).x, cur.get(1).y, cur.get(0).x, cur.get(0).y);
        map[cur.get(0).x][cur.get(0).y] = 0;
        right += nextPermutation(idx+1, cur.get(0).x, cur.get(0).y);
        map[cur.get(0).x][cur.get(0).y] = list.get(idx);
        map[cur.get(1).x][cur.get(1).y] = list.get(idx);
        return Math.min(left, right);
    }

    private int bfs(int sx, int sy, int tx, int ty) {
        Queue<Nod> queue = new ArrayDeque<>();
        queue.add(new Nod(sx, sy, 1));

        boolean[][] visited = new boolean[size][size];
        for(int i = 0; i < size; i++){
            Arrays.fill(visited[i], false);
        }
        visited[sx][sy] = true;

        while(!queue.isEmpty()){
            Nod cur = queue.poll();
            Pair<Integer, Integer> cpos = cur.pos;

            if(cpos.x == tx && cpos.y == ty) return cur.dist;

            Pair<Integer, Integer> tmpPos = getCtrlUp(cpos);
            if(!visited[tmpPos.x][tmpPos.y]){
                visited[tmpPos.x][tmpPos.y] = true;
                queue.add(new Nod(tmpPos.x, tmpPos.y, cur.dist+1));
            }
            tmpPos = getCtrlDown(cpos);
            if(!visited[tmpPos.x][tmpPos.y]){
                visited[tmpPos.x][tmpPos.y] = true;
                queue.add(new Nod(tmpPos.x, tmpPos.y, cur.dist+1));
            }
            tmpPos = getCtrlLeft(cpos);
            if(!visited[tmpPos.x][tmpPos.y]){
                visited[tmpPos.x][tmpPos.y] = true;
                queue.add(new Nod(tmpPos.x, tmpPos.y, cur.dist+1));
            }
            tmpPos = getCtrlRight(cpos);
            if(!visited[tmpPos.x][tmpPos.y]){
                visited[tmpPos.x][tmpPos.y] = true;
                queue.add(new Nod(tmpPos.x, tmpPos.y, cur.dist+1));
            }
            if(cpos.x-1 >= 0 && !visited[cpos.x-1][cpos.y]){
                visited[cpos.x-1][cpos.y] = true;
                queue.add(new Nod(cpos.x-1, cpos.y, cur.dist+1));
            }
            if(cpos.x+1 < size && !visited[cpos.x+1][cpos.y]){
                visited[cpos.x+1][cpos.y] = true;
                queue.add(new Nod(cpos.x+1, cpos.y, cur.dist+1));
            }
            if(cpos.y-1 >= 0 && !visited[cpos.x][cpos.y-1]){
                visited[cpos.x][cpos.y-1] = true;
                queue.add(new Nod(cpos.x, cpos.y-1, cur.dist+1));
            }
            if(cpos.y+1 < size && !visited[cpos.x][cpos.y+1]){
                visited[cpos.x][cpos.y+1] = true;
                queue.add(new Nod(cpos.x, cpos.y+1, cur.dist+1));
            }

        }
        return 0;
    }

    private Pair<Integer, Integer> getCtrlRight(Pair<Integer, Integer> cpos) {
        int x = cpos.x;
        int y = cpos.y;
        while(y+1 < size && map[x][y+1] == 0) y++;
        if(y+1 < size) y++;
        return new Pair<>(x, y);
    }

    private Pair<Integer, Integer> getCtrlLeft(Pair<Integer, Integer> cpos) {
        int x = cpos.x;
        int y = cpos.y;
        while(y-1 >= 0 && map[x][y-1] == 0) y--;
        if(y-1 >= 0) y--;
        return new Pair<>(x, y);
    }

    private Pair<Integer, Integer> getCtrlDown(Pair<Integer, Integer> cpos) {
        int x = cpos.x;
        int y = cpos.y;
        while(x+1 < size && map[x+1][y] == 0) x++;
        if(x+1 < size) x++;
        return new Pair<>(x, y);
    }

    private Pair<Integer, Integer> getCtrlUp(Pair<Integer, Integer> cpos) {
        int x = cpos.x;
        int y = cpos.y;
        while(x-1 >= 0 && map[x-1][y] == 0) x--;
        if(x-1 >= 0) x--;
        return new Pair<>(x, y);
    }

    static class Nod{
        Pair<Integer, Integer> pos;
        int dist;

        public Nod(int x, int y, int dist) {
            this.pos = new Pair<>(x, y);
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0);
        System.out.println(ret);
    }
}
