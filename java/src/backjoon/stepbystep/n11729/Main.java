package backjoon.stepbystep.n11729;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> src = new ArrayList<>();
    static List<Integer> tar = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());

        recur(1, 3, 2, 1, k);
        bw.write(src.size()+"\n");
        for(int i = 0; i < src.size(); i++){
            bw.write(src.get(i)+" "+tar.get(i)+"\n");
        }

        bw.flush();
    }

    static void recur(int start, int end, int ano, int svalue, int evalue){
        if(svalue == evalue){
            src.add(start);
            tar.add(end);
            return;
        }

        recur(start, ano, end, svalue, evalue-1);
        recur(start, end, ano, evalue, evalue);
        recur(ano, evalue, start, svalue, evalue-1);
    }

}
