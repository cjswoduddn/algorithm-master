package programmers.kakao2018blind.n6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {
    
    static class Nod{
        String name;
        String song;
        int idx;
        int length;

        public Nod(String name, String song, int idx, int length) {
            this.name = name;
            this.song = song;
            this.idx = idx;
            this.length = length;
        }
    }

    String rem;
    List<Nod> music = new ArrayList<>();
    Pattern pattern;

    public String solution(String m, String[] musics){
        init(m, musics);
        return solve();
    }

    private String solve() {
        if(music.size() == 0) return "(None)";
        music.sort((nod1, nod2)->{
            if(nod1.length == nod2.length){
                return nod1.idx-nod2.idx;
            }
            return nod2.length-nod1.length;
        });
        return music.get(0).name;
    }

    private void init(String m, String[] music) {
        pattern = Pattern.compile("[A-Z]#?");
        rem = convertString(m);
        Pattern compile = Pattern.compile(rem);

        for(int i = 0; i < music.length; i++){
            Nod nod = convertMusic(music[i], i);
            Matcher matcher = compile.matcher(nod.song);
            if(matcher.find()){
                this.music.add(nod);
            }
        }
    }

    private Nod convertMusic(String music, int idx) {
        String[] strs = music.split(",");
        int length = getLength(strs[1], strs[0]);
        String song = convertString(strs[3]);
        song = convertDuringSong(length, song);
        return new Nod(strs[2], song, idx, length);
    }

    private String convertDuringSong(int length, String song) {
        String ret = "";
        while(length > song.length()){
            ret += song;
            length -= song.length();
        }
        ret += song.substring(0, length);
        return ret;
    }

    private int getLength(String e, String s) {
        int te = Integer.parseInt(e.substring(0, 2))*60+Integer.parseInt(e.substring(3, 5));
        int ts = Integer.parseInt(s.substring(0, 2))*60+Integer.parseInt(s.substring(3, 5));
        return te-ts;
    }

    private String convertString(String m) {
        Matcher matcher = pattern.matcher(m);
        String ret = "";
        while(matcher.find()){
            String str = matcher.group();
            if(str.equals("C#")){
                ret += "P";
            }else if(str.equals("D#")){
                ret += "Q";
            }else if(str.equals("F#")){
                ret += "R";
            }else if(str.equals("G#")){
                ret += "S";
            }else if(str.equals("A#")){
                ret += "V";
            }else {
                ret += str;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        String ret = solution2.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"});
        System.out.println("ret = " + ret);

    }
}
