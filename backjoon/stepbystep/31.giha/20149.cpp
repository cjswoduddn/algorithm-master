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

double crossX(ll x1, ll y1, ll x2, ll y2
    , ll x3, ll y3, ll x4, ll y4){
    ll header = (x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4);
    ll fotter = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
    return (double)header/(double)fotter;
}
double crossY(ll x1, ll y1, ll x2, ll y2
    , ll x3, ll y3, ll x4, ll y4){
    ll header = (x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4);
    ll fotter = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
    return (double)header/(double)fotter;
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
	   if(line(a, b, c, d, e, f)){
           printf("1\n");
           printf("%lld %lld\n", e, f);
       }
	   else printf("0\n");
    }else if(A != 0 && B == 0){
	   if(line(a, b, c, d, g, h)) {
           printf("1\n");
           printf("%lld %lld\n", g, h);
       }
	   else printf("0\n");
    }else if(A == 0 && B == 0){
	    if(line(a, b, c, d, e, f)){
            printf("1\n");
            // printf("%lld %lld\n", e, f);
        }
    	else if(line(a, b, c, d, g, h)){
            printf("1\n");
            // printf("%lld %lld\n", g, h);
        }
    	else if(line(e, f, g, h, a, b)){
            printf("1\n");
            // printf("%lld %lld\n", a, b);
        }
    	else if(line(e, f, g, h, c, d)){
            printf("1\n");
            // printf("%lld %lld\n", c, d);
        }
    	else printf("0\n");
    }else if(A != 0 && B != 0){
    	if(A*B > 0 || C*D > 0)
            printf("0\n");
    	else{
            printf("1\n");
            printf("%lf %lf\n", crossX(a, b, c, d, e, f, g, h),
                crossY(a, b, c, d, e, f, g, h));
        }
    }
    return 0;
}
