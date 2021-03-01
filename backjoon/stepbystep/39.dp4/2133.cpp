#include<stdio.h>
typedef long long ll;

int N;
ll dp[31];

ll recur(int k){
	if(k%2 == 1) return 0;
	ll& ret = dp[k];
	if(ret != -1) return ret;

	ret = recur(k-2)*3;
	for(int i = 0; i < k-2; i+=2)
		ret += recur(i)*2;
	return ret;
}

int main(){
	for(int i = 0; i < 31; i++)
		dp[i] = -1;
	dp[0] = 1;
	dp[1] = 0;
	dp[2] = 3;
	scanf("%d", &N);
	printf("%lld\n", recur(N));
	return 0;
}