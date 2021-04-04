package backjoon.stepbystep.n10814;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Throwable {

        int line = Integer.parseInt(br.readLine());
        List<Pair<Integer, String>> arr = new ArrayList<>();

        for(int i = 0; i < line; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr.add(new Pair<Integer, String>(Integer.parseInt(st.nextToken()), st.nextToken(), i));
        }

        arr.sort((a, b)->{
            if(a.getA() > b.getA()) return 1;
            else if(a.getA() < b.getA()) return -1;
            if(a.getCnt() > b. getCnt()) return 1;
            else return -1;
        });

        for(Pair pair : arr){
            bw.write(pair.getA()+" "+pair.getB()+"\n");
        }
        bw.flush();
    }

    static class Pair<P, K>{
        P a;
        K b;
        int cnt;

        public P getA() {
            return a;
        }

        public K getB() {
            return b;
        }

        public int getCnt() {
            return cnt;
        }

        public Pair(P a, K b, int cnt) {
            this.a = a;
            this.b = b;
            this.cnt = cnt;
        }
    }
}
