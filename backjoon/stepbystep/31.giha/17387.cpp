#include<stdio.h>
typedef long long ll;
ll ccw(ll x1, ll y1, ll x2, ll y2, ll x3, ll y3){
    ll ans =  x1*y2+x2*y3+x3*y1-(y1*x2+y2*x3+y3*x1);
    if(ans > 0) return 1;
    else if(ans < 0) return -1;
    else if(ans == 0) return 0;
    return 0;
}

bool line(ll x1, ll y1, ll x2, ll y2, ll c, ll w){
    ll lx = x1 > x2 ? x1 : x2;
    ll sx = x1 < x2 ? x1 : x2;
    ll ly = y1 > y2 ? y1 : y2;
    ll sy = y1 < y2 ? y1 : y2;
    if(c >= sx && c <= lx && w >= sy && w <= ly) return true;
    else return false;
}

int main(){
    ll a, b, c, d, e, f, g, h;
    scanf("%lld %lld", &a, &b);
    scanf("%lld %lld", &c, &d);
    scanf("%lld %lld", &e, &f);
    scanf("%lld %lld", &g, &h);

    ll A = ccw(a, b, c, d, e, f);
    ll B = ccw(a, b, c, d, g, h);
    ll C = ccw(e, f, g, h, a, b);
    ll D = ccw(e, f, g, h, c, d);

    if(A == 0 && B != 0){
	   if(line(a, b, c, d, e, f)) printf("1\n");
	   else printf("0\n");
    }else if(A != 0 && B == 0){
	   if(line(a, b, c, d, g, h)) printf("1\n");
	   else printf("0\n");
    }else if(A == 0 && B == 0){
	    if(line(a, b, c, d, e, f)) printf("1\n");
    	else if(line(a, b, c, d, e, f)) printf("1\n");
    	else if(line(e, f, g, h, a, b)) printf("1\n");
    	else if(line(e, f, g, h, c, d)) printf("1\n");
    	else printf("0\n");
    }else if(A != 0 && B != 0){
    	if(A*B > 0 || C*D > 0) printf("0\n");
    	else printf("1\n");
    }
    return 0;
}
