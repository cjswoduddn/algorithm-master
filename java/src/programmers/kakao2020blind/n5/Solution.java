package programmers.kakao2020blind.n5;


import java.util.*;

// 45 ~
public class Solution {

    List<List<Integer>> weaks = new ArrayList<>();

    private boolean permuattion(List<Integer> items, int[] arr, boolean[] check, int cnt){
        if(cnt == check.length){
            List<Integer> brr = new ArrayList<>();
            for(int i = 0; i < arr.length; i++){
                brr.add(items.get(arr[i]));
            }
            return solve(brr);
        }

        for(int i = 0; i < check.length; i++){
            if(check[i] == true) continue;
            check[i] = true;
            arr[cnt] = i;
            boolean ret = permuattion(items, arr, check, cnt + 1);
            if(ret == true) return true;
            check[i] = false;
        }
        return false;
    }

    private boolean solve(List<Integer> clear) {
        for(int i = 0; i < weaks.size(); i++){
            if(innerSolve(clear, weaks.get(i))) return true;
        }
        return false;
    }

    private boolean innerSolve(List<Integer> clear, List<Integer> weak) {
        int idx = 0;
        for(int i = 0; i < clear.size(); i++){
            int ptr = 0;
            while(idx+ptr < weak.size() && weak.get(idx+ptr)-weak.get(idx) <= clear.get(i))
                ptr++;
            idx += ptr;
        }
        if(idx == weak.size()) return true;
        return false;
    }

    public int solution(int n, int[] weak, int[] dist){

        for(int i = 0; i < weak.length; i++){
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < weak.length; j++){
                tmp.add(weak[(j+i)%weak.length]);
            }
            weaks.add(tmp);
            weak[i] += n;
        }

        List<Integer> friends = new ArrayList<>();
        for(int i = 0; i < dist.length; i++){
            friends.add(dist[i]);
        }

        Collections.sort(friends, Collections.reverseOrder());
        List<Integer> clear = new ArrayList<>();
        for(int i = 0; i < friends.size(); i++){
            clear.add(friends.get(i));
            boolean[] check = new boolean[clear.size()];
            int[] arr = new int[clear.size()];
            if(permuattion(clear, arr, check, 0)) return i+1;
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 5, 10, 40, 30});
        System.out.println(ret);

    }


}