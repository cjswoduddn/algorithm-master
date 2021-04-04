package programmers.kakao2018blind.n7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 20 ~
public class Solution2 {

    String message;

    public int[] solution(String msg){
        init(msg);
        return solve();
    }

    private int[] solve() {
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        while(index <= 25){
            map.put(String.valueOf((char)(index+'A')), index+1);
            index++;
        }

        int s = 0;
        int e = 1;

        List<Integer> list = new ArrayList<>();
        while(e <= message.length()){
            if(map.containsKey(message.substring(s, e))) e++;
            else{
                list.add(map.get(message.substring(s, e-1)));
                map.put(message.substring(s, e), index++);
                s = e-1;
            }
        }
        list.add(map.get(message.substring(s, e - 1)));

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    private void init(String msg) {
        this.message = msg;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution("KAKAO");
    }
}
