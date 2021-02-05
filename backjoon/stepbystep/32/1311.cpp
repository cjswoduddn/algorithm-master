#include<stdio.h>

int MAX;
int N;
int cost[21][21];
int dp[21][1<<20];

int recur(int cur, int state){
	if(state == MAX) return 0;
	int& ret = dp[cur][state];
	if(ret != -1) return ret;

	ret = 2100000000;
	for(int i = 0; i < N; i++){
		int bit = 1<<i;
		if((state&bit)) continue;
		int tmp = recur(cur+1, state|bit)+cost[cur][i+1];
		ret = ret < tmp ? ret : tmp;
	}
	return ret;
}

int main(){
	scanf("%d", &N);
	MAX = (1<<N)-1;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			scanf("%d", &cost[i][j]);
		}
	}
	for(int i = 1; i <= N; i++){
		for(int j = 0; j <= MAX; j++)
			dp[i][j] = -1;
	}

	printf("%d\n", recur(1, 0));
	return 0;
}