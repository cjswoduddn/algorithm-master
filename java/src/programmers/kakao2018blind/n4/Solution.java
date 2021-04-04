package programmers.kakao2018blind.n4;

// 24
public class Solution {

    int n;
    int m;
    int[][] map;

    int[] dx = new int[]{0, 0, 1, 1};
    int[] dy = new int[]{0, 1, 0, 1};

    public int solution(int m, int n, String[] bd){
        init(m, n, bd);
        return solve();
    }

    private int solve() {
        int ret = 0;
        int pre = 0;
        do{
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    System.out.printf(" %2d", map[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");
            pre = ret;
            for(int i = 0; i < n-1; i++){
                for(int j = 0; j < m-1; j++){
                    if(map[i][j] == -1) continue;
                    int tmp = crush(i, j, map[i][j]);
                    if(tmp == 0) continue;
                    ret += tmp;
                    break;
                }
                if(ret != pre) break;
            }
            mapSetting();
        }while(ret != pre);
        return ret;
    }

    // -2인 요소전부 -1 또는 블락으로 만들어 버리기
    private void mapSetting() {
        for(int j = 0; j < m; j++){
            // 첫 -2의 위치를 찾는다
            int idx = 0;
            while(idx < n && map[idx][j] != -2) idx++;
            if(idx == n) continue;
            int count = 0;
            while(idx+count < n && map[idx+count][j] == -2) count++;
            for(int i = idx-1; i >= 0; i--){
                map[i+count][j] = map[i][j];
                map[i][j] = -2;
            }
            for(int i = 0; i < n && map[i][j] == -2; i++){
                map[i][j] = -1;
            }
        }
    }

    // 허용되는 값은 value 또는 -2
    private int crush(int x, int y, int value){
        int ret = 0;
        for(int i = 0; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= m) return 0;
            if(!(map[nx][ny] == value || map[nx][ny] == -2)) return 0;
        }
        for(int i = 0; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(map[nx][ny] == value){
                ret++;
                map[nx][ny] = -2;
            }
        }
        for(int i = 1; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            ret += crush(nx, ny, value);
        }
        return ret;
    }

    private void init(int p, int q, String[] bd) {
        n = p;
        m = q;
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = bd[i].charAt(j)-'A';  // 블록은 0~25 사이의 수, 공백은 -1로 부수는 중은 -2로 정하면 재귀적 사용 가능
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution(6, 6, new String[]{"AABBEE","AAAEEE","VAAEEV","AABBEE","AACCEE","VVCCEE" });
        System.out.println(ret);
    }
}
