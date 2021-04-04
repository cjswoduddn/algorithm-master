package backjoon.stepbystep.n1181;

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
        int line = Integer.parseInt(br.readLine());
        List<String> arr = new ArrayList<>();

        for(int i = 0; i < line; i++){
            String str = br.readLine();
            if(!arr.contains(str))
                arr.add(str);
        }

        arr.sort((a, b)->{
            if(a.length() > b.length()) return 1;
            else if(a.length() < b.length()) return -1;
            return a.compareTo(b);
        });

        for (String str : arr){
            bw.write(str+"\n");
        }
        bw.flush();
    }
}
