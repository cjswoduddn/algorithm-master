package programmers.kakao2018blind.n5;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int solution(int csize, String[] cities){
        int ret = 0;
        Map<String, Integer> map = new HashMap<>();

        int idx = 0;
        for(int i = 0; i < cities.length; i++){
            String str = cities[i].toLowerCase();
            if(map.containsKey(str)){
                ret++;
                map.put(str, idx++);
            }else{
                ret += 5;
                if(map.size() < csize){
                    map.put(str, idx++);
                }else{
                    Map.Entry<String, Integer> key = null;
                    for(Map.Entry<String, Integer> entry : map.entrySet()){
                        if(key == null) key = entry;
                        if(key.getValue().compareTo(entry.getValue()) > 0) key = entry;
                    }
                    map.remove(key.getKey());
                    map.put(str, idx++);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(3,
                new String[]
                        {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        System.out.println(ret);
    }
}
