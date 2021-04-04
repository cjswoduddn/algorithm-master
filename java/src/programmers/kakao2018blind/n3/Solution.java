package programmers.kakao2018blind.n3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int solution(String str1, String str2){
        List<String> subStr1 = getSubStr(str1);
        List<String> subStr2 = getSubStr(str2);

        Map<String, Integer> map = new HashMap<>();
        subStr1.forEach(item->{
            int cnt = map.containsKey(item) ? map.get(item) : 0;
            map.put(item, cnt+1);
        });

        double ret = 0;
        for(String item : subStr2){
            if(map.containsKey(item) && map.get(item) > 0){
                ret++;
                map.put(item, map.get(item)-1);
            }
        }
        return (int)(((ret)/(subStr1.size()+subStr2.size()-ret))*65536);
    }

    private List<String> getSubStr(String str) {
        List<String> ret = new ArrayList<>();

        for(int i = 0; i < str.length()-1; i++){
            if(str.substring(i, i+2).matches("[A-z][A-z]")){
                ret.add(str.substring(i, i+2).toLowerCase());
            }
        }

        return ret;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution("FRANCE", "french");
        System.out.println(ret);
    }
}
