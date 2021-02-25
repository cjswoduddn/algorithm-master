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
		if(a.first == b.first)
			return a.second > b.second;
		return a.first < b.first;
	}
};

int N, M, u, v;

int main(){
	scanf("%d %d", &N, &M);
	vector<pii> g;

	while(N--){
		scanf("%d %d", &u, &v);
		if(u <= v) continue;
		g.push_back(pii(v, u));
	}

	sort(g.begin(), g.end(), cmp());

	ll ans = 0;
	int ss = -1000000000, se = -1000000000;
	for(pii ptr : g){
		ss = ss > ptr.first ? ss : ptr.first;
		se = se > ptr.second ? se : ptr.second;
		ans += se-ss;
		ss = se;
	}

	ans *= 2;
	ans += M;

	printf("%lld\n", ans);
	return 0;
}