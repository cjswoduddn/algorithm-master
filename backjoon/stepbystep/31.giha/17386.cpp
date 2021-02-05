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
	pll p1, p2, p3, p4;
	scanf("%lld %lld %lld %lld", &p1.x, &p1.y, &p2.x, &p2.y);
	scanf("%lld %lld %lld %lld", &p3.x, &p3.y, &p4.x, &p4.y);

	ll left = ccw(p1, p2, p3)*ccw(p1, p2, p4);
	ll right = ccw(p3, p4, p1)*ccw(p3, p4, p2);

	if(left < 0 && right < 0) printf("1\n");
	else printf("0\n");
	return 0;
}