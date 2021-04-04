package backjoon.stepbystep.S2562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int ret = 0;
        int idx = 1;
        int max = Integer.MIN_VALUE;

        List<Integer> arr = new ArrayList<>();
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        arr.add(Integer.parseInt(br.readLine()));
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i) > max){
                max = arr.get(i);
                ret = i+1;
            }
        }

        bw.write(max+"\n");
        bw.write(ret+"\n");
        bw.flush();
    }
}
