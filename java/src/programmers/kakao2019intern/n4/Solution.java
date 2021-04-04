package programmers.kakao2019intern.n4;

import java.util.*;

public class Solution {

    static List<Long> pos = new ArrayList<>();
    static List<Long> ret = new ArrayList<>();
    static int[] mySet;
    static long[] alloc;

    public long[] solution(long k, long[] rm){
        init(k, rm);
        for(int i = 0; i < rm.length; i++){
            int idx = Collections.binarySearch(pos, rm[i]);
            idx = find(idx);
            ret.add(alloc[idx]++);
            if(idx == pos.size()-1) continue;
            if(alloc[idx] == pos.get(idx+1)){
                union(idx, idx+1);
            }
        }
        long[] ans = new long[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }
        return ans;
    }

    private void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u != v){
            mySet[u] = v;
        }
    }

    private int find(int u){
        if(u == mySet[u]) return u;
        return mySet[u] = find(mySet[u]);
    }

    private void init(long k, long[] rm){
        Set<Long> set = new HashSet<>();
        for(long num : rm){
            set.add(num);
        }

        for(Long num : set){
            pos.add(num);
        }

        pos.add(k+1);
        mySet = new int[pos.size()];
        for(int i = 0; i < mySet.length; i++){
            mySet[i] = i;
        }
        alloc = new long[pos.size()];
        for(int i = 0; i < pos.size(); i++){
            alloc[i] = pos.get(i);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(10, new long[]{1, 3, 4, 1, 3, 1});
    }
}
