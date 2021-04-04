package programmers.kakao2020blind.n6;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// 5 ~
public class Solution {

    static class Nod{
        int state;      // 0 : leftright, 1 : updown
        int x1, y1;     // left or up
        int x2, y2;     // right or down
        int dist;

        public Nod(int state, int x1, int y1, int x2, int y2, int dist) {
            this.state = state;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dist = dist;
        }
    }

    public int solution(int[][] bd){
        int size = bd.length;
        int ans = 0;
        Set<String> set = new HashSet<>();
        Queue<Nod> queue = new ArrayDeque<>();
        queue.add(new Nod(0, 0, 0, 0, 1, 0));

        while(!queue.isEmpty()){
            Nod cur = queue.poll();
            if(cur.x1 == size-1 && cur.y1 == size-1) return cur.dist;
            if(cur.x2 == size-1 && cur.y2 == size-1) return cur.dist;

            if(cur.state == 0){
                if(cur.x1+1 < size && bd[cur.x1+1][cur.y1] == 0 && bd[cur.x1+1][cur.y1+1] == 0
                    && !set.contains(convertString(cur.x2, cur.y2, cur.x1+1, cur.y1+1))){
                    set.add(convertString(cur.x2, cur.y2, cur.x1+1, cur.y1+1));
                    queue.add(new Nod(1, cur.x2, cur.y2, cur.x1+1, cur.y1+1, cur.dist+1));
                }
                if(cur.x1-1 >= 0 && bd[cur.x1-1][cur.y1] == 0 && bd[cur.x1-1][cur.y1+1] == 0
                        && !set.contains(convertString(cur.x1-1, cur.y1+1, cur.x2, cur.y2))){
                    set.add(convertString(cur.x1-1, cur.y1+1, cur.x2, cur.y2));
                    queue.add(new Nod(1, cur.x1-1, cur.y1+1, cur.x2, cur.y2, cur.dist+1));
                }
                if (cur.x2 + 1 < size && bd[cur.x2 + 1][cur.y2] == 0 && bd[cur.x2 + 1][cur.y2 - 1] == 0
                        && !set.contains(convertString(cur.x1, cur.y1, cur.x2 - 1, cur.y2 - 1))) {
                    set.add(convertString((cur.x1), cur.y1, cur.x2 - 1, cur.y2 - 1));
                    queue.add(new Nod(1, cur.x1, cur.y1, cur.x2 - 1, cur.y2 - 1, cur.dist+1));
                }
                if (cur.x2 - 1 >= 0 && bd[cur.x2 - 1][cur.y2] == 0 && bd[cur.x2 - 1][cur.y2 - 1] == 0
                        && !set.contains(convertString(cur.x2-1, cur.y2-1, cur.x1, cur.y1))) {
                    set.add(convertString(cur.x2-1, cur.y2-1, cur.x1, cur.y1));
                    queue.add(new Nod(1, cur.x2-1, cur.y2-1, cur.x1, cur.y1, cur.dist+1));
                }
            }else if(cur.state == 1){
                if(cur.y1-1 >= 0 && bd[cur.x1][cur.y1-1] == 0 && bd[cur.x1+1][cur.y1-1] == 0
                    && !set.contains(convertString(cur.x1+1, cur.y1-1, cur.x2, cur.y2))){
                    set.add(convertString(cur.x1+1, cur.y1-1, cur.x2, cur.y2));
                    queue.add(new Nod(0, cur.x1 + 1, cur.y1 - 1, cur.x2, cur.y2, cur.dist+1));
                }
                if(cur.y1+1 < size && bd[cur.x1][cur.y1+1] == 0 && bd[cur.x1+1][cur.y1+1] == 0
                && !set.contains(convertString(cur.x2, cur.y2, cur.x1+1, cur.y1+1))){
                    set.add(convertString(cur.x2, cur.y2, cur.x1+1, cur.y1+1));
                    queue.add(new Nod(0, cur.x2, cur.y2, cur.x1+1, cur.y1+1, cur.dist+1));
                }
                if (cur.y2 - 1 >= 0 && bd[cur.x2][cur.y2 - 1] == 0 && bd[cur.x2 - 1][cur.y2 - 1] == 0
                        && !set.contains((convertString(cur.x2 - 1, cur.y2 - 1, cur.x1, cur.y1)))) {
                    set.add((convertString(cur.x2 - 1, cur.y2 - 1, cur.x1, cur.y1)));
                    queue.add(new Nod(0, cur.x2 - 1, cur.y2 - 1, cur.x1, cur.y1, cur.dist+1));
                }
                if (cur.y2 + 1 < size && bd[cur.x2][cur.y2 + 1] == 0 && bd[cur.x2 - 1][cur.y2 + 1] == 0
                        && !set.contains((convertString(cur.x1, cur.y1, cur.x2 - 1, cur.y2 + 1)))) {
                    set.add((convertString(cur.x1, cur.y1, cur.x2 - 1, cur.y2 + 1)));
                    queue.add(new Nod(0, cur.x1, cur.y1, cur.x2 - 1, cur.y2 + 1, cur.dist+1));
                }
            }
        }
        throw new RuntimeException();
    }

    private String convertString(int a, int b, int c, int d){
        return
            String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d);
    }


}
