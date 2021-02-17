#include<stdio.h>
#include<vector>
using namespace std;

const int INF = 1000000001;
const int MINF = 0;
int N, M, u, v;

int maxInit(vector<int>& seg, vector<int>& arr, int cur,
	int s, int e){
	if(s == e) return seg[cur] = arr[s];
	int mid = (s+e)/2;
	int left = maxInit(seg, arr, cur*2, s, mid);
	int right = maxInit(seg, arr, cur*2+1, mid+1, e);
	return seg[cur] = left > right ? left : right;
}

int maxQuery(vector<int>& seg, int cur, int s, int e,
	int l, int r){
	if(e < l || r < s) return MINF;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	int left = maxQuery(seg, cur*2, s, mid, l, r);
	int right = maxQuery(seg, cur*2+1, mid+1, e, l, r);
	return left > right ? left : right;
}
int minInit(vector<int>& seg, vector<int>& arr, int cur,
	int s, int e){
	if(s == e) return seg[cur] = arr[s];
	int mid = (s+e)/2;
	int left = minInit(seg, arr, cur*2, s, mid);
	int right = minInit(seg, arr, cur*2+1, mid+1, e);
	return seg[cur] = left < right ? left : right;
}

int minQuery(vector<int>& seg, int cur, int s, int e,
	int l, int r){
	if(e < l || r < s) return INF;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	int left = minQuery(seg, cur*2, s, mid, l, r);
	int right = minQuery(seg, cur*2+1, mid+1, e, l, r);
	return left < right ? left : right;
}
int main(){
	scanf("%d %d", &N, &M);
	vector<int> arr(N+1);
	vector<int> minTree(4*N+1), maxTree(4*N+1);

	for(int i = 1; i <= N; i++)
		scanf("%d", &arr[i]);

	minInit(minTree, arr, 1, 1, N);
	maxInit(maxTree, arr, 1, 1, N);

	while(M--){
		scanf("%d %d", &u, &v);
		printf("%d ", minQuery(minTree, 1, 1, N, u, v));
		printf("%d\n", maxQuery(maxTree, 1, 1, N, u, v));
	}
	return 0;
}