package programmers.kakao2019blind.n3;

import java.util.*;

// 09 ~ 36
public class Solution {

    int csize;
    int rsize;
    String[][] ret;
    boolean[] arr = new boolean[8];
    List<Integer> ans = new ArrayList<>();

    public int solution(String[][] relation){
        Arrays.fill(arr, false);
        csize = relation[0].length;
        rsize = relation.length;
        ret = relation;
        for(int i = 1; i <= csize; i++){
            process(0, 0, i);
        }
        return ans.size();
    }

    private void process(int s, int cur, int m) {
        if(cur == m){
            int bit = getValue();
            for(int i = 0; i < ans.size(); i++){
                if((bit&ans.get(i)) == ans.get(i)) return;
            }
            if(!validCandy()) return;
            ans.add(bit);
            return;
        }

        for(int i = s; i < csize; i++){
            arr[i] = true;
            process(i+1, cur+1, m);
            arr[i] = false;
        }
    }

    private boolean validCandy() {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < rsize; i++){
            String str = "";
            for(int j = 0; j < csize; j++){
                if(arr[j]) str += ret[i][j];
            }
            if(set.contains(str)) return false;
            set.add(str);
        }
        return true;
    }

    private int getValue() {
        int ret = 0;
        for(int i = 0; i < csize; i++){
            if(!arr[i]) continue;
            int bit = 1<<i;
            ret |= bit;
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int gg = solution.solution(new String[][]{
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}});
        System.out.println("gg = " + gg);
    }
}
