package programmers.kakao2021blind.n5;

import java.util.Arrays;

public class Solution2 {

    int pTime;
    int adTime;
    long[] during;

    public String solution(String ptime, String adtime, String[] logs){
        init(ptime, adtime, logs);
        return solve();
    }

    private String solve() {
        int ret = 0;
        long value = 0;
        for(int i = 0; i <= adTime; i++){
            value += during[i];
        }

        long tvalue = value;
        for(int i = 1; i+adTime <= pTime; i++){
            tvalue += during[i+adTime]-during[i-1];
            if(tvalue > value){
                value = tvalue;
                ret = i;
            }
        }
        return convertIntToString(ret);
    }

    private String convertIntToString(int ret) {
        String hour = String.valueOf(ret/3600);
        if(hour.length() == 1) hour = "0"+hour;
        ret %= 3600;
        String min = String.valueOf(ret/60);
        if(min.length() == 1) min = "0"+min;
        String sec = String.valueOf(ret%60);
        if(sec.length() == 1) sec = "0"+sec;
        return hour+":"+min+":"+sec;
    }

    private void init(String ptime, String adtime, String[] logs) {
        pTime = convertStringToInt(ptime);
        adTime = convertStringToInt(adtime);
        during = new long[pTime+1];
        Arrays.fill(during, 0);

        for(int i = 0; i < logs.length; i++){
            String[] strs = logs[i].split("-");
            int s = convertStringToInt(strs[0]);
            int e = convertStringToInt(strs[1]);

            for(int j = s; j <= e; j++){
                during[j]++;
            }
        }
    }

    private int convertStringToInt(String ptime) {
        String[] strs = ptime.split(":");
        int hour = 3600*Integer.parseInt(strs[0]);
        int min = 60*Integer.parseInt(strs[1]);
        int sec = Integer.parseInt(strs[2]);
        return hour+min+sec;
    }
}
