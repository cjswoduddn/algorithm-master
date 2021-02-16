#include<stdio.h>
typedef long long ll;

int N, M;
ll arr[100001];
int u, v;

int main(){
	scanf("%d %d", &N, &M);
	for(int i = 1; i <= N; i++)
		scanf("%lld", &arr[i]);

	for(int i = 2; i <= N; i++)
		arr[i] += arr[i-1];

	while(M--){
		scanf("%d %d", &u, &v);
		printf("%lld\n", arr[v]-arr[u-1]);
	}

	return 0;
}