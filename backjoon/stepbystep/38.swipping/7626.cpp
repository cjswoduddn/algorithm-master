#include<stdio.h>
#include<vector>
#include<algorithm>
using namespace std;
typedef pair<int, int> pii;
typedef long long ll;
class nod{
public:
	int x, sy, ey;
	int se;
};
class cmp{
public:
	bool operator()(nod a, nod b){
		return a.x < b.x;
	}
};

int N, x1, x2, y1, y2;

void update(vector<pii>& seg, vector<int>& ypos, int cur, int s,
 int e,	int l, int r, int value){
	if(e < l || r < s) return;
	if(l <= s && e <= r){
		seg[cur].first += value;
	}else{
		int mid = (s+e)/2;
		update(seg, ypos, cur*2, s, mid, l, r, value);
		update(seg, ypos, cur*2+1, mid+1, e, l, r, value);
	}

	if(seg[cur].first > 0){
		seg[cur].second = ypos[e]-ypos[s-1];
	}else{
		if(s == e) seg[cur].second = 0;
		else seg[cur].second = 
			seg[cur*2].second+seg[cur*2+1].second;
	}
}

int main(){
	scanf("%d", &N);
	vector<nod> g;
	vector<int> ypos;

	for(int i = 0; i < N; i++){
		scanf("%d %d %d %d", &x1, &x2, &y1, &y2);
		g.push_back({x1, y1, y2, 1});
		g.push_back({x2, y1, y2, -1});
		ypos.push_back(y1);
		ypos.push_back(y2);
	}

	sort(g.begin(), g.end(), cmp());
	sort(ypos.begin(), ypos.end());
	ypos.erase(unique(ypos.begin(), ypos.end()), ypos.end());
	vector<pii> seg(ypos.size()*4+1, pii(0, 0));

	ll ans = 0;
	int preX = 0;
	for(nod& ptr : g){
		ans += (ll)seg[1].second*(ptr.x-preX);
		preX = ptr.x;
		int sidx = lower_bound(ypos.begin(), ypos.end(), ptr.sy)-ypos.begin();
		int eidx = lower_bound(ypos.begin(), ypos.end(), ptr.ey)-ypos.begin();
		update(seg, ypos, 1, 0, ypos.size()-1, sidx+1, eidx, ptr.se);
	}

	printf("%lld\n", ans);
	return 0;
}
