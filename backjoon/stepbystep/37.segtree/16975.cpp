#include<stdio.h>
#include<vector>
using namespace std;
typedef long long ll;

int N, M, q, u, v;
ll d;

void update(vector<ll>& seg, int cur, int s, int e,
	int l, int r, ll value){
	if(e < l || r < s) return;
	if(l <= s && e <= r){
		seg[cur] += value;
		return;
	}
	int mid = (s+e)/2;
	update(seg, cur*2, s, mid, l, r, value);
	update(seg, cur*2+1, mid+1, e, l, r, value);
}

ll query(vector<ll>& seg, int cur, int s, int e, int idx){
	if(idx < s || e < idx) return 0;
	if(s == e) return seg[cur];
	int mid = (s+e)/2;
	return seg[cur] + query(seg, cur*2, s, mid, idx)
		+ query(seg, cur*2+1, mid+1, e, idx);
}

int main(){
	scanf("%d", &N);
	vector<ll> arr(N+1);
	vector<ll> seg(4*N+1, 0);

	for(int i = 1; i <= N; i++)
		scanf("%lld", &arr[i]);

	scanf("%d", &M);
	while(M--){
		scanf("%d", &q);
		if(q == 1){
			scanf("%d %d %lld", &u, &v, &d);
			update(seg, 1, 1, N, u, v, d);
		}else{
			scanf("%d", &u);
			printf("%lld\n", query(seg, 1, 1, N, u)+arr[u]);
		}
	}

	return 0;
}