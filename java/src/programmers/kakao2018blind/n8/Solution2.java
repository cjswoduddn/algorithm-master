package programmers.kakao2018blind.n8;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {

    static class Nod{
        String head;
        int body;
        int idx;

        public Nod(String head, int body, int idx) {
            this.head = head;
            this.body = body;
            this.idx = idx;
        }
    }

    List<String> files = new ArrayList<>();
    String[] pure;

    public String[] solution(String[] files){
        init(files);
        return solve();
    }

    private String[] solve() {
        List<Nod> nods = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^\\d]+)([\\d]+)");
        for(int i = 0; i < files.size(); i++){
            Matcher matcher = pattern.matcher(files.get(i));
            if(matcher.find()){
                nods.add(new Nod(matcher.group(1), Integer.parseInt(matcher.group(2)), i));
            }
        }

        nods.sort((nod1, nod2)->{
            if(nod1.head.equals(nod2.head)){
                if(nod1.body == nod2.body){
                    return nod1.idx-nod2.idx;
                }
                return nod1.body- nod2.body;
            }
            return nod1.head.compareTo(nod2.head);
        });
        String[] ret = new String[nods.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = pure[nods.get(i).idx];
            System.out.println(ret[i]);
        }
        return ret;
    }

    private void init(String[] files) {
        pure = files;
        for(int i = 0; i < files.length; i++){
            this.files.add(files[i].toLowerCase());
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
    }
}
