package programmers.kakao2018blind.n8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    static class Nod{
        String head;
        int tail;
        int idx;

        public Nod(String head, int tail, int idx) {
            this.head = head;
            this.tail = tail;
            this.idx = idx;
        }
    }

    public String[] parser(String str){
        Pattern pattern = Pattern.compile("([^\\d]+)([0-9]{1,5})(.*)");
        Matcher matcher = pattern.matcher(str);
        String[] ret = new String[3];
        if(matcher.find()){
            for(int i = 0; i < 3; i++){
                ret[i] = matcher.group(i+1);
            }
        }
        return ret;
    }

    public String[] solution(String[] files){
        List<Nod> arr = new ArrayList<>();
        for(int i = 0; i < files.length; i++){
            String[] strs = parser(files[i]);
            arr.add(new Nod(strs[0].toLowerCase(), Integer.parseInt(strs[1]), i));
        }

        Collections.sort(arr, (item1, item2)->{
            if(item1.head.compareTo(item2.head) == 0){
                if(item1.tail == item2.tail){
                    return item1.idx-item2.idx;
                }
                return item1.tail-item2.tail;
            }
            return item1.head.compareTo(item2.head);
        });

        String[] ret = new String[files.length];
        for(int i = 0; i < arr.size(); i++){
            ret[i] = files[arr.get(i).idx];
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] ret = solution.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
        for(int i = 0; i < ret.length; i++){
            System.out.println(ret[i]);
        }
    }
}
