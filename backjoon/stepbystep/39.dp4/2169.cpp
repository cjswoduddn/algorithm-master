#include<stdio.h>
#include<queue>
#include<utility>
#include<limits.h>
#define x first
#define y second
using namespace std;
typedef pair<int, int> pii;
/*
일반적인 dp가 안될 경우 상황을 분할해서 문제를 해결할 수 있다
이경우 일반적인 dp가 통용되지 않으니 위, 좌, 우 세가지로 케이스를 분할
*/

const int MINF = INT_MIN;
int map[1000][1000];
int dp[3][1000][1000];
int N, M;

int dfs(int curx, int cury, int prey){
	int vec;
	if(cury == prey) vec = 1; 		// 위에서 내려온 경우
	else if(cury > prey) vec = 0; 	// 왼쪽에서 온 경우
	else if(cury < prey) vec = 2;	// 오른쪽에서 온 경우
	int& ret = dp[vec][curx][cury];
	if(ret != MINF) return ret;
	if(curx == N-1 && cury == M-1)
		return ret = map[N-1][M-1];

	if(cury-1 >= 0 && cury-1 != prey){
		int tmp = dfs(curx, cury-1, cury);
		if(tmp != MINF) {
			tmp += map[curx][cury];
			ret = ret > tmp ? ret : tmp;
		}
	}

	if(cury+1 < M && cury+1 != prey){
		int tmp = dfs(curx, cury+1, cury);
		if(tmp != MINF) {
			tmp += map[curx][cury];
			ret = ret > tmp ? ret : tmp;
		}
	}

	if(curx+1 < N){
		int tmp = dfs(curx+1, cury, cury);
		if(tmp != MINF) {
			tmp += map[curx][cury];
			ret = ret > tmp ? ret : tmp;
		}
	}

	return ret;
}

int main(){
	scanf("%d %d", &N, &M);
	for(int i = 0; i < N; i++){
		for(int j = 0; j < M; j++){
			scanf("%d", &map[i][j]);
			dp[0][i][j] = dp[1][i][j] = dp[2][i][j] = MINF;
		}
	}

	printf("%d\n", dfs(0, 0, 0));
	return 0;
}
