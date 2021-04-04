package backjoon.stepbystep.n2750;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Throwable {
        int k = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();

        for(int i = 0; i < k; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        arr.sort((o1, o2) -> {
            if(o1 < o2) return 1;
            else if(o1 > o2) return -1;
            return 0;
        });

        for(Integer ptr : arr){
            bw.write(ptr+"\n");
        }
        bw.flush();
    }
}
