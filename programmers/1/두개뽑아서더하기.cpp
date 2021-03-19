#include<stdio.h>

int N, M;

int GCD(int a, int b){
	if(b == 0) return a;
	return GCD(b, a%b);
}

int LCA(int a, int b, int gcd){
	int ret = 1;
	ret *= a/gcd;
	ret *= b/gcd;
	return ret*gcd;
}

int main(){
	scanf("%d %d", &N, &M);
	int gcd = GCD(N, M);
	int lca = LCA(N, M, gcd);
	printf("%d %d\n", gcd, lca);
	return 0;
}