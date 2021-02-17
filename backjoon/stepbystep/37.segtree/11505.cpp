#include<stdio.h>
#include<vector>
#define MOD 1000000007
using namespace std;
typedef long long ll;

int N, M, Q, q, u, v;
ll d;

ll init(vector<ll>& seg, vector<ll>& arr, int cur, int s, int e){
	if(s == e) return seg[cur] = arr[s];
	int mid = (s+e)/2;
	return seg[cur] = 
		(init(seg, arr, cur*2, s, mid)
			* init(seg, arr, cur*2+1, mid+1, e))%MOD;
}

ll update(vector<ll>& seg, vector<ll>& arr, int cur,
	int s, int e, int idx, ll value){
	if(idx < s || e < idx) return seg[cur];
	if(s == e){
		return 
			seg[cur] = arr[idx] = value;
	}
	int mid = (s+e)/2;
	return seg[cur] = (
	update(seg, arr, cur*2, s, mid, idx, value) *
	update(seg, arr, cur*2+1, mid+1, e, idx, value)
	)%MOD;
}

ll query(vector<ll>& seg, int cur, int s, int e, int l, int r){
	if(e < l || r < s) return 1;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	return (query(seg, cur*2, s, mid, l, r) 
		* query(seg, cur*2+1, mid+1, e, l, r))%MOD;
}

int main(){
	scanf("%d %d %d", &N, &M, &Q);
	vector<ll> arr(N+1);
	vector<ll> seg(4*N+1);

	for(int i = 1; i <= N; i++)
		scanf("%lld", &arr[i]);

	init(seg, arr, 1, 1, N);
	M += Q;

	while(M--){
		scanf("%d", &q);
		if(q == 1){
			scanf("%d %lld", &u, &d);
			update(seg, arr, 1, 1, N, u, d);
		}else if(q == 2){
			scanf("%d %d", &u, &v);
			printf("%lld\n", query(seg, 1, 1, N, u, v));
		}
	}
	return 0;
}