#include<stdio.h>
#include<vector>
#include<algorithm>
#define x first
#define y second
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

ll ccw(pll a, pll b, pll c){
	ll tmp = a.x*b.y+b.x*c.y+c.x*a.y;
	tmp -= b.x*a.y+c.x*b.y+a.x*c.y;
	if(tmp < 0) return -1;
	if(tmp == 0) return 0;
	return 1;
}
int main(){
	vector<pll> g;
	ll x, y;
	scanf("%lld %lld", &x, &y);
	g.push_back(pll(x, y));
	scanf("%lld %lld", &x, &y);
	g.push_back(pll(x, y));
	scanf("%lld %lld", &x, &y);
	g.push_back(pll(x, y));

	printf("%lld\n", ccw(g[0], g[1], g[2]));
	return 0;
}