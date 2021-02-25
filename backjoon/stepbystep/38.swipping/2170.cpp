#include<stdio.h>
#include<vector>
#include<algorithm>
using namespace std;
typedef pair<int, int> pii;
class cmp{
public:
	bool operator()(const pii& a, const pii& b){
		if(a.first == b.first)
			return a.second > b.second;
		return a.first < b.first;
	}
};

int N, u, v;

int main(){
	scanf("%d", &N);
	vector<pii> g(N);

	for(int i = 0; i < N; i++)
		scanf("%d %d", &g[i].first, &g[i].second);

	sort(g.begin(), g.end(), cmp());

	int ans = 0;
	int ss = -1000000000, se = -1000000000;
	for(pii ptr : g){
		ss = ss > ptr.first ? ss : ptr.first;
		se = se > ptr.second ? se : ptr.second;
		ans += se-ss;
		ss = se;
	}

	printf("%d\n", ans);
	return 0;
}
