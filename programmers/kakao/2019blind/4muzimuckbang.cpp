#include <vector>
#include <algorithm>

using namespace std;
class cmp{
public:
	bool operator()(const pii& a, const pii& b){
		return a.second < b.second;
	}
};
typedef long long ll;
typedef pair<int, int> pii;

ll recur(vector<int>& ftime, ll s, ll e, ll k){
	if(s > e) return -1;
	ll mid = (s+e)/2;
	ll cnt = 0;
	for(int ptr : ftime){
		ll tmp = mid > ptr ? ptr : mid;
		cnt += tmp;
	}

	if(cnt > k) return recur(ftime, s, mid-1, k);
	return recur(ftime, mid, )
}

int solution(vector<int> ftime, ll k){
	ll ptr = recur(ftime, 1, k, k);
}

int main(){
	return 0;
}
