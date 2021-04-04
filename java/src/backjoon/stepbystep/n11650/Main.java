package backjoon.stepbystep.n11650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Throwable{
        int line = Integer.parseInt(br.readLine());

        List<Pair> arr = new ArrayList<>();
        for(int i = 0; i < line; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Pair pair = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            arr.add(pair);
        }

        arr.sort((o1, o2) -> {
            if(o1.getX() > o2.getX()) return 1;
            else if(o1.getX() < o2.getX()) return -1;
            if(o1.getY() > o2.getY()) return 1;
            else if(o1.getY() < o2.getY()) return -1;
            return 0;
        });
        for(Pair pr : arr){
            bw.write(pr.getX()+" "+pr.getY()+"\n");
        }
        bw.flush();

    }

}

class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}