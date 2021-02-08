#include<stdio.h>
#include<limits.h>

const int INF = INT_MAX;
int N, cost[16][16];
int dp[16][1<<16];

int recur(int cur, int state){
	if(state == ((1<<N)-1))
		return cost[cur][0];
	int& ret = dp[cur][state];
	if(ret != -1) return ret;

	ret = INF;
	for(int i = 0; i < N; i++){
		int bit = 1<<i;
		if(state&bit || cost[cur][i] == 0) continue;
		int tmp = recur(i, state|bit);
		if(tmp == 0 || tmp == INF) continue;
		tmp += cost[cur][i];
		ret = ret < tmp ? ret : tmp;
	}
	return ret;
}

int main(){
	scanf("%d", &N);
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
			scanf("%d", &cost[i][j]);
		}
	}
	for(int i = 0; i < N; i++){
		for(int j = 0, size = 1<<N; j < size; j++){
			dp[i][j] = -1;
		}
	}

	printf("%d\n", recur(0, 1));
	return 0;
}