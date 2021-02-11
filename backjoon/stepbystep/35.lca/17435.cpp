#include<stdio.h>

int spTable[21][200001];
int tfac[21]={1};
int M, Q, n, x;

int query(int n, int x){
	if(n == 1) return spTable[0][x];
	int idx = 1;
	while(tfac[idx] <= n) idx++;
	idx--;

	if(n-tfac[idx] == 0) return spTable[idx][x];
	return query(n-tfac[idx], spTable[idx][x]);
}
int main(){
	scanf("%d", &M);
	for(int i = 1; i <= M; i++){
		scanf("%d", &spTable[0][i]);
	}

	for(int i = 1; i < 21; i++){
		tfac[i] = tfac[i-1]*2;
		for(int j = 1; j <= M; j++){
			spTable[i][j]
				= spTable[i-1][spTable[i-1][j]];
		}
	}

	scanf("%d", &Q);
	while(Q--){
		scanf("%d %d", &n, &x);
		printf("%d\n", query(n, x));
	}
	return 0;
}