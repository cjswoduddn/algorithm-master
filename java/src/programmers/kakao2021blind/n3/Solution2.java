package programmers.kakao2021blind.n3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {

    List<String> info = new ArrayList<>();
    List<String> query = new ArrayList<>();

    Map<String, ArrayList<Integer>> map = new HashMap<>();
    boolean[] check = new boolean[4];

    public int[] solution(String[] info, String[] query){
        init(info, query);
        return solve();
    }

    private int[] solve() {
        for(int i = 0; i < info.size(); i++){
            String[] strs = info.get(i).split("\\s");
            for(int j = 0; j <= 4; j++){
                permutation(0, 0, j, strs);
            }
        }

        for(List<Integer> list : map.values()){
            list.sort(Integer::compareTo);
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < query.size(); i++){
            String[] strs = query.get(i).split(" (and )?");
            String ret = "";
            for(int j = 0; j < strs.length-1; j++){
                ret += strs[j];
            }
            if(!map.containsKey(ret)) {
                ans.add(0);
                continue;
            }
            List<Integer> list = map.get(ret);
            ans.add(list.size() - lowerBound(list, 0, list.size() - 1, Integer.parseInt(strs[strs.length - 1])));
        }

        int[] ret = new int[ans.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private int lowerBound(List<Integer> list, int s, int e, int value) {
        if(s > e) return list.size();
        int mid = (s+e)/2;
        if(list.get(mid) >= value){
            return Math.min(mid, lowerBound(list, s, mid - 1, value));
        }
        return lowerBound(list, mid+1, e, value);
    }

    private void permutation(int s, int cnt, int tar, String[] strs) {
        if(cnt == tar){
            String ret = "";
            for(int i = 0; i < strs.length-1; i++){
                if(check[i]) ret += '-';
                else ret += strs[i];
            }
            map.putIfAbsent(ret, new ArrayList<>());
            map.get(ret).add(Integer.parseInt(strs[4]));
        }

        for(int i = s; i < strs.length-1; i++){
            check[i] = true;
            permutation(i+1, cnt+1, tar, strs);
            check[i] = false;
        }
    }

    private void init(String[] info, String[] query) {
        for(int i = 0; i < info.length; i++){
            this.info.add(info[i]);
        }

        for(int i = 0; i < query.length; i++){
            this.query.add(query[i]);
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(
                new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}
                , new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}
        );
    }
}
