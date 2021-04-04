package kakaocommerce.n5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 00 ~ 10
public class Solution {

    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> wants = new ArrayList<>();
    int size;

    public int solution(int[] giftCard, int[] wants){
        init(giftCard, wants);
        return solve();
    }

    private int solve() {
        int ret = size;
        for(int i = 0; i < wants.size(); i++){
            int item = wants.get(i);
            if(!map.containsKey(item) || map.get(item) == 0) continue;
            ret--;
            map.put(item, map.get(item)-1);
        }
        return ret;
    }

    private void init(int[] giftCard, int[] wants) {
        size = giftCard.length;
        for(int i = 0; i < giftCard.length; i++){
            int item = giftCard[i];
            map.putIfAbsent(item, 0);
            map.put(item, map.get(item)+1);
        }

        for(int i = 0; i < wants.length; i++){
            this.wants.add(wants[i]);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(
                new int[]{4, 5, 3, 2, 1},
                new int[]{2, 4, 4, 5, 1}
        );
    }
}
