package programmers.kakao2019blind.n5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    int[] basic;
    double[] matchPoint;
    List<List<Integer>> g = new ArrayList<>();
    Map<Integer, String> map = new HashMap<>();

    public int solution(String word, String[] pages){
        word = word.toLowerCase();
        for(int i = 0; i < pages.length; i++){
            pages[i] = pages[i].toLowerCase();
            basic = new int[pages.length];
            matchPoint = new double[pages.length];
            g.add(new ArrayList<>());
        }

        for(int i = 0; i < pages.length; i++){
            basic[i] = getBasic(pages[i], word);
            System.out.println(basic[i]);
            matchPoint[i] = basic[i];
        }

        for(int i = 0; i < pages.length; i++){
            map.put(i, getUrl(pages[i]));
        }

        for(int i = 0; i < pages.length; i++){
            makeGraph(i, pages[i]);
        }

        for(int i = 0; i < pages.length; i++){
            for(int j = 0; j < g.get(i).size(); j++){
                matchPoint[g.get(i).get(j)] += (double)basic[i]/g.get(i).size();
            }
        }

        double ret = 0;
        for(int i = 0; i < pages.length; i++){
            ret = Math.max(ret, matchPoint[i]);
        }

        for(int i = 0; i < pages.length; i++){
            if(ret == matchPoint[i]) return i;
        }
        return 0;
    }

    private void makeGraph(int cur, String page) {
        Pattern pattern = Pattern.compile("href=\"(.*)\"");
        Matcher matcher = pattern.matcher(page);
        while(matcher.find()){
            for(int key : map.keySet()){
                if(map.get(key).equals(matcher.group(1))){
                    g.get(cur).add(key);
                    break;
                }
            }
        }
    }

    private String getUrl(String page) {
        Pattern pattern = Pattern.compile("content=\"(.*)\"");
        Matcher matcher = pattern.matcher(page);
        if(matcher.find()) return matcher.group(1);
        throw new RuntimeException("매칭 URL이 없어용");
    }

    private int getBasic(String page, String word) {
        Pattern pattern = Pattern.compile("[^a-z]+" + word + "[^a-z]+");
        Matcher matcher = pattern.matcher(page);
        int ret = 0;
        while(matcher.find()) ret++;
        return ret;
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int ret = solution.solution("blind",
//                new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
//        System.out.println(ret);
//
//    }
}
