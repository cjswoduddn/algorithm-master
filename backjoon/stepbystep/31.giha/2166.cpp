#include<stdio.h>
#include<vector>
#include<utility>
#define x first
#define y second
using namespace std;
typedef pair<int, int> pii;
typedef long long ll;

int N, u, v;

ll ccw(pii a, pii b, pii c){
	ll ret = a.x*(ll)b.y+b.x*(ll)c.y+c.x*(ll)a.y;
	ret -= b.x*(ll)a.y+c.x*(ll)b.y+a.x*(ll)c.y;
	return ret;
}
int main(){
	scanf("%d", &N);
	vector<pii> g(N);

	for(int i = 0; i < N; i++)
		scanf("%d %d", &g[i].x, &g[i].y);

	ll ans = 0;
	for(int i = 1; i < N-1; i++){
		ans += ccw(g[0], g[i], g[i+1]);
	}

	double ret = ans/(double)2;
	if(ret < 0)
		ret *= -1;
	printf("%.1lf\n", ret);
	return 0;
}