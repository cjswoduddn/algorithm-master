package programmers.kakao2018blind.n7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int[] solution(String msg){
        int[] ans;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < 26; i++){
            map.put((char)(i+'A')+"", i+1);
        }

        int idx = 27;
        int s = 0;
        int e = 1;

        List<Integer> ret = new ArrayList<>();
        while(e <= msg.length()){
            if(map.containsKey(msg.substring(s, e))) e++;
            else{
                ret.add(map.get(msg.substring(s, e - 1)));
                map.put(msg.substring(s, e), idx++);
                s = e-1;
            }
        }
        ret.add(map.get(msg.substring(s, e - 1)));


        ans = new int[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("");
    }
}
