package programmers.kakao2020intern.n3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 30 ~ 47...
public class Solution2 {

    public int[] solution(String[] gems){
        Set<String> set = new HashSet<>();
        for(int i = 0; i < gems.length; i++){
            set.add(gems[i]);
        }

        int rs = 0;
        int re = 0;
        int dist = Integer.MAX_VALUE;

        int s = 0;
        int e = 0;
        Map<String, Integer> map = new HashMap<>();
        while(s <= e){
            System.out.println(map.size() + " " + set.size());
            if(map.size() == set.size()){
                if(dist > s-e){
                    dist = e-s;
                    rs = s;
                    re = e;
                }
                map.put(gems[s], map.get(gems[s])-1);
                if(map.get(gems[s]) == 0) map.remove(gems[s]);
                s++;
            }else{
                if(e == gems.length) break;
                if(!map.containsKey(gems[e])) map.put(gems[e], 0);
                map.put(gems[e], map.get(gems[e])+1);
                e++;
            }
        }

        return new int[]{rs+1, re+1};
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }
}
