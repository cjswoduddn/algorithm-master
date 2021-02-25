#include<stdio.h>
#include<queue>
#include<vector>
#include<algorithm>
#define x first
#define y second
#define MOD 1000000007
#define SIZE 200000
using namespace std;
typedef pair<int, int> pii;
class cmp{
public:
	bool operator()(const pii& a, const pii& b){
		return a.y > b.y;
	}
};

int N;

void insert(vector<int>& seg, int cur, int s, int e, int idx){
	if(idx < s || e < idx) return;
	seg[cur]++;
	if(s == e) return;
	int mid = (s+e)/2;
	insert(seg, cur*2, s, mid, idx);
	insert(seg, cur*2+1, mid+1, e, idx);
}

int query(vector<int>& seg, int cur, int s, int e, int l, int r){
	if(e < l || r < s) return 0;
	if(l <= s && e <= r) return seg[cur];
	int mid = (s+e)/2;
	return query(seg, cur*2, s, mid, l, r) +
		query(seg, cur*2+1, mid+1, e, l, r);
}

int main(){
	scanf("%d", &N);
	vector<pii> pos(N);
	vector<int> ypos(SIZE*2+1, 0);
	vector<int> seg(ypos.size()*4+1, 0);

	for(int i = 0; i < N; i++)
		scanf("%d %d", &pos[i].x, &pos[i].y);

	sort(pos.begin(), pos.end(), cmp());

	queue<pii> buf;
	int preY = 0;
	long long ans = 0;

	for(pii& ptr : pos){
		if(ptr.y != preY){
			preY = ptr.y;
			while(!buf.empty()){
				insert(seg, 1, 0, SIZE*2, buf.front().x+SIZE);
				buf.pop();
			}
		}
		buf.push(ptr);
		if(ptr.x+SIZE == 0 || ptr.x == SIZE) continue;
		int left = query(seg, 1, 0, SIZE*2, 0, ptr.x+SIZE-1);
		int right = query(seg, 1, 0, SIZE*2, ptr.x+SIZE+1, SIZE*2);
		ans += ((long long)left*right)%MOD;
		ans %= MOD;
	}

	printf("%lld\n", ans);

	return 0;
}