package programmers.kakao2019blind.n1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static class Nod{
        int stage;
        double failRatio;

        public Nod(int stage, double failRatio) {
            this.stage = stage;
            this.failRatio = failRatio;
        }
    }

    public int[] solution(int n, int[] stgs){
        int[] fail = new int[n+2];
        for(int i = 0; i < stgs.length; i++){
            fail[stgs[i]]++;
        }

        int[] sum = new int[n+2];
        sum[n+1] = fail[n+1];
        for(int i = n; i > 0; i--){
            sum[i] = sum[i+1]+fail[i];
        }

        List<Nod> nods = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            nods.add(new Nod(i, (double)fail[i]/sum[i]));
        }

        Collections.sort(nods, (i1, i2)->{
            if(i1.failRatio < i2.failRatio) return 1;
            else if(i1.failRatio > i2.failRatio) return -1;
            if(i1.stage < i2.stage) return 1;
            return -1;
        });

        List<Integer> ret = new ArrayList<>();
        nods.forEach(item->ret.add(item.stage));
        int[] ans = new int[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }
        return ans;
    }
}
