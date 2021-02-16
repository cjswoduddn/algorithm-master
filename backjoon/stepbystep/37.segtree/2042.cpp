#include<vector>
#include<stdio.h>
using namespace std;
typedef long long ll;

int N, M, Q, u, v;
ll k;
vector<ll> arr;
vector<ll> seg;

ll init(int cur, int s, int e){
	if(s == e) return seg[cur] = arr[s];
	int mid = (s+e)/2;
	return seg[cur] = 
		init(cur*2, s, mid)+init(cur*2+1, mid+1, e);
}

void update(int cur, int s, int e, int target, ll value){
	seg[cur] += value;
	if(s == e){
		arr[s] += value;
		return;
	}
	int mid = (s+e)/2;
	if(target <= mid) update(cur*2, s, mid, target, value);
	else update(cur*2+1, mid+1, e, target, value);
}

ll query(int cur, int s, int e, int l, int r){
	if(e < l || r < s) return 0;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	return query(cur*2, s, mid, l, r)+
		query(cur*2+1, mid+1, e, l, r);
}

int main(){
	scanf("%d %d %d", &N, &M, &Q);
	arr.resize(N);
	seg.resize(4*N+1);
	M += Q;

	for(int i = 0; i < N; i++)
		scanf("%lld", &arr[i]);

	init(1, 0, N-1);

	while(M--){
		scanf("%d", &Q);
		if(Q == 1) {
			scanf("%d %lld", &u, &k);
			u--;
			update(1, 0, N-1, u, k-arr[u]);
		}else{
			scanf("%d %d", &u, &v);
			u--;v--;
			printf("%lld\n", query(1, 0, N-1, u, v));
		}
	}
	return 0;
}

