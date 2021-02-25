#include<stdio.h>
#include<vector>
#include<algorithm>
#define x first
#define y second
using namespace std;
typedef pair<int, int> pii;
typedef long long ll;
class cmp{
public:
	bool operator()(const pii& a, const pii& b){
		if(a.x == b.x) return a.y > b.y;
		return a.x < b.x;
	}
};

int T, N, u, v;

int query(vector<int>& seg, int cur, int s, int e, int l, int r){
	if(e < l || r < s) return 0;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	return query(seg, cur*2, s, mid, l, r)+
		query(seg, cur*2+1, mid+1, e, l, r);
}

void insert(vector<int>& seg, int cur, int s, int e, int idx){
	if(idx < s || e < idx) return;
	seg[cur]++;
	if(s == e) return;

	int mid = (s+e)/2;
	insert(seg, cur*2, s, mid, idx);
	insert(seg, cur*2+1, mid+1, e, idx);
}

int main(){
	scanf("%d", &T);
	while(T--){
		scanf("%d", &N);
		vector<pii> pos(N);
		vector<int> ypos;

		for(int i = 0; i < N; i++){
			scanf("%d %d", &pos[i].x, &pos[i].y);
			ypos.push_back(pos[i].y);
		}
		sort(ypos.begin(), ypos.end());
		ypos.erase(unique(ypos.begin(), ypos.end()), ypos.end());

		sort(pos.begin(), pos.end(), cmp());
		vector<int> seg(4*ypos.size()+1, 0);

		ll ans = 0;
		for(pii ptr : pos){
			int idx = lower_bound(ypos.begin(), ypos.end(), ptr.y)-ypos.begin();
			int cnt = query(seg, 1, 0, ypos.size()-1, idx, ypos.size()-1);
			ans += cnt;
			insert(seg, 1, 0, ypos.size()-1, idx);
		}
		printf("%lld\n", ans);
	}
	return 0;
}