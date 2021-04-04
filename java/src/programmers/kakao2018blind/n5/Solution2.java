package programmers.kakao2018blind.n5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 26 ~ 32
public class Solution2 {

    int csize;
    List<String> cities = new ArrayList<>();

    public int solution(int csize, String[] cities){
        init(csize, cities);
        return solve();
    }

    int solve(){
        Map<String, Integer>  map = new HashMap<>();
        int ret = 0;
        int count = 0;

        for(String str : cities){
            if(map.containsKey(str)){
                ret += 1;
                map.put(str, count++);
            }else{
                ret += 5;
                System.out.println(str+" "+map.size());
                if(map.size() == csize) {
                    int min = Integer.MAX_VALUE;
                    for (int value : map.values()) {
                        min = Math.min(min, value);
                    }

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue() == min) {
                            map.remove(entry.getKey());
                            break;
                        }
                    }
                }
                map.put(str, count++);
            }
        }
        return ret;
    }

    private void init(int csize, String[] cities) {
        this.csize = csize;
        for(int i = 0; i < cities.length; i++){
            this.cities.add(cities[i].toLowerCase());
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
    }
}
