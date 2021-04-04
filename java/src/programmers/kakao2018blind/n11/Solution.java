package programmers.kakao2018blind.n11;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public int stringToInt(String str){
        int hour = 60*Integer.parseInt(str.substring(0, 2));
        int min = Integer.parseInt(str.substring(3, 5));
        return hour+min;
    }

    public String intToString(int k){
        String hour = String.valueOf(k/60);
        if(hour.length() == 1) hour = "0"+hour;
        k %= 60;
        String min = String.valueOf(k);
        if(min.length() == 1) min = "0"+min;
        return hour+":"+min;
    }

    static class Bus{
        int cnt;
        int arriveTime;
        int lastTime;

        public Bus(int cnt, int arriveTime, int lastTime) {
            this.cnt = cnt;
            this.arriveTime = arriveTime;
            this.lastTime = lastTime;
        }
    }

    public String solution(int n, int t, int m, String[] tt){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < tt.length; i++){
            pq.add(stringToInt(tt[i]));
        }

        List<Bus> buses = new ArrayList<>();
        int stime = stringToInt("09:00");
        for(int i = 0; i < n; i++){
            buses.add(new Bus(m, stime+t*i, 0));
        }

        for(int i = 0; i < n; i++){
           while(!pq.isEmpty() && buses.get(i).cnt > 0){
               if(pq.peek() > buses.get(i).arriveTime) break;
               buses.get(i).cnt--;
               buses.get(i).lastTime = pq.poll();
           }
        }

        if(buses.get(buses.size()-1).cnt > 0){
            return intToString(buses.get(buses.size()-1).arriveTime);
        }else{
            System.out.println(buses.get(buses.size()-1).lastTime);
            return intToString(buses.get(buses.size()-1).lastTime-1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ret = solution.solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"});
        System.out.println(ret);
    }
}
