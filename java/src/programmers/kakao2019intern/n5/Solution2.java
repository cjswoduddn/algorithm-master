package programmers.kakao2019intern.n5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2 {

    int[] mySet;
    Long[] count;
    List<Long> list;

    public long[] solution(long k, long[] rnum){
        init(rnum);

        List<Long> ans = new ArrayList<>();
        for(int i = 0; i < rnum.length; i++){
            k = rnum[i];
            int idx = find(lowerBound(0, list.size()-1, k));
            ans.add(count[idx]++);
            if(count[idx] == list.get(idx+1)){
                union(idx, idx+1);
            }
        }

        long[] ret = new long[ans.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = ans.get(i);
        }
        return ret;
    }

    int lowerBound(int s, int e, long value){
        if(s > e) return list.size();
        int mid = (s+e)/2;
        if(list.get(mid) >= value){
            int tmp = lowerBound(s, mid-1, value);
            return Math.min(mid, tmp);
        }
        return lowerBound(mid+1, e, value);
    }

    int find(int u){
        if(mySet[u] == u) return u;
        return mySet[u] = find(mySet[u]);
    }

    void union(int u, int v){
        u = find(u);
        v = find(v);
        if(u != v){
            mySet[u] = v;
        }
    }

    private void init(long[] rnum) {
        Set<Long> set = new HashSet<>();
        set.add(Long.MAX_VALUE);
        for(int i = 0; i < rnum.length; i++){
            set.add(rnum[i]);
        }

        mySet = new int[set.size()];
        for(int i = 0; i < mySet.length; i++){
            mySet[i] = i;
        }
        list = new ArrayList<>(set);
        list.sort(Long::compareTo);
        count = new Long[list.size()];
        for(int i = 0; i < count.length; i++){
            count[i] = list.get(i);
        }
    }
}
