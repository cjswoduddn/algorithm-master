#include<stdio.h>
#include<vector>
using namespace std;

int T, N, M, q, u, v;

int minInit(vector<int>& tree, vector<int>& arr, int cur,
	int s, int e){
	if(s == e) return tree[cur] = arr[s];
	int mid = (s+e)/2;
	int left = minInit(tree, arr, cur*2, s, mid);
	int right = minInit(tree, arr, cur*2+1, mid+1, e);
	return tree[cur] = left < right ? left : right;
}
int maxInit(vector<int>& tree, vector<int>& arr, int cur,
	int s, int e){
	if(s == e) return tree[cur] = arr[s];
	int mid = (s+e)/2;
	int left = maxInit(tree, arr, cur*2, s, mid);
	int right = maxInit(tree, arr, cur*2+1, mid+1, e);
	return tree[cur] = left > right ? left : right;
}
int minUpdate(vector<int>& tree, int cur, int s, int e,
	int idx, int value){
	if(idx < s || e < idx) return tree[cur];
	if(s == e) return tree[cur] = value;
	int mid = (s+e)/2;
	int left = minUpdate(tree, cur*2, s, mid, idx, value);
	int right = minUpdate(tree, cur*2+1, mid+1, e, idx, value);
	return tree[cur] = left < right ? left : right;
}
int maxUpdate(vector<int>& tree, int cur, int s, int e,
	int idx, int value){
	if(idx < s || e < idx) return tree[cur];
	if(s == e) return tree[cur] = value;
	int mid = (s+e)/2;
	int left = maxUpdate(tree, cur*2, s, mid, idx, value);
	int right = maxUpdate(tree, cur*2+1, mid+1, e, idx, value);
	return tree[cur] = left > right ? left : right;
}
int minQuery(vector<int>& tree, int cur, int s, int e,
	int l, int r){
	if(e < l || r < s) return 100000;
	if(l <= s && e <= r) return tree[cur];
	int mid = (s+e)/2;
	int left = minQuery(tree, cur*2, s, mid, l, r);
	int right = minQuery(tree, cur*2+1, mid+1, e, l, r);
	return left < right ? left : right;
}
int maxQuery(vector<int>& tree, int cur, int s, int e,
	int l, int r){
	if(e < l || r < s) return 0;
	if(l <= s && e <= r) return tree[cur];
	int mid = (s+e)/2;
	int left = maxQuery(tree, cur*2, s, mid, l, r);
	int right = maxQuery(tree, cur*2+1, mid+1, e, l, r);
	return left > right ? left : right;
}


int main(){
	scanf("%d", &T);
	while(T--){
		scanf("%d %d", &N, &M);
		vector<int> arr(N);
		for(int i = 0; i < N; i++)
			arr[i] = i;
		vector<int> minTree(4*N+1);
		vector<int> maxTree(4*N+1);
		minInit(minTree, arr, 1, 0, N-1);
		maxInit(maxTree, arr, 1, 0, N-1);

		while(M--){
			scanf("%d %d %d", &q, &u, &v);
			if(q == 0){ // u번 선반을 v번 선반으로
				int ou = arr[u], ov = arr[v];
				minUpdate(minTree, 1, 0, N-1, u, ov);
				minUpdate(minTree, 1, 0, N-1, v, ou);
				maxUpdate(maxTree, 1, 0, N-1, u, ov);
				maxUpdate(maxTree, 1, 0, N-1, v, ou);
				arr[u] = ov, arr[v] = ou;
			}else{
				int minValue = minQuery(minTree, 1, 0, N-1, u, v);
				int maxValue = maxQuery(maxTree, 1, 0, N-1, u, v);
				if(u == minValue && v == maxValue)
					printf("YES\n");
				else
					printf("NO\n");
			}
		}
	}
	return 0;
}