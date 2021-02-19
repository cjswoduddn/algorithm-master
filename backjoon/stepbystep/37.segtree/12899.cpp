#include<stdio.h>
#include<vector>
#define MAX 2000000
using namespace std;

int N, p, q;

void insert(vector<int>& seg, int cur, int s, int e, int idx){
	if(idx < s || e < idx) return;
	if(s == e){
		seg[cur]++;
		return;
	}
	seg[cur]++;
	int mid = (s+e)/2;
	insert(seg, cur*2, s, mid, idx);
	insert(seg, cur*2+1, mid+1, e, idx);
}

int query(vector<int>& seg, int cur, int s, int e, int idx){
	if(s == e){
		seg[cur]--;
		return s;
	}
	seg[cur]--;
	int mid = (s+e)/2;
	int left = seg[cur*2];
	if(idx <= left)
		return query(seg, cur*2, s, mid, idx);
	return query(seg, cur*2+1, mid+1, e, idx-left);

}

int main(){
	vector<int> seg(4*MAX+1, 0);

	scanf("%d", &N);
	while(N--){
		scanf("%d %d", &p, &q);
		if(p == 1)
			insert(seg, 1, 1, MAX, q);
		else
			printf("%d\n", query(seg, 1, 1, MAX, q));
	}
	return 0;
}