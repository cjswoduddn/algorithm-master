package programmers.kakao2020intern.n3;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] solution(String[] gems){
        Map<String, Integer> map = new HashMap<>();

        for(String str : gems){
            if(!map.containsKey(str)){
                map.put(str, 0);
            }
        }
        int rs = 0;
        int re = 0;
        int rdis = 100000;

        int cnt = 0;
        int target = map.size();
        int s = 0;
        int e = 0;

        while(s <= e){
            if(cnt < target){
                if(e == gems.length) break;
                if(map.get(gems[e]) == 0) cnt++;
                map.put(gems[e], map.get(gems[e])+1);
                e++;
            }else if(cnt == target){
                if(e-s < rdis){
                    rdis = e-s;
                    rs = s;
                    re = e;
                }
                if(map.get(gems[s]) == 1) cnt--;
                map.put(gems[s], map.get(gems[s])-1);
                s++;
            }
        }
        return new int[]{rs+1, re};
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
//        solution.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
//    }
}
