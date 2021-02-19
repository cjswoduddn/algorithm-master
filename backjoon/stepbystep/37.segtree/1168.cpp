#include<stdio.h>
#include<vector>
using namespace std;

int N, M;

int init(vector<int>& seg, int cur, int s, int e){
	if(s == e) return seg[cur] = 1;
	int mid = (s+e)/2;
	return seg[cur] = 
		init(seg, cur*2, s, mid)+init(seg, cur*2+1, mid+1, e);
}

int query(vector<int>& seg, int cur, int s, int e, int k){
	if(s == e){
		seg[cur]--;
		return s;
	}
	seg[cur]--;
	int mid = (s+e)/2;
	int left = seg[cur*2];

	if(k <= left) return query(seg, cur*2, s, mid, k);
	return query(seg, cur*2+1, mid+1, e, k-left);
}

int main(){
	scanf("%d %d", &N, &M);
	vector<int> seg(4*N+1);
	init(seg, 1, 1, N);

	printf("<");
	int t = 1;
	while(true){
		t = (t-1+M)%seg[1];
		t = t == 0 ? seg[1] : t;
		printf("%d", query(seg, 1, 1, N, t));
		if(seg[1] == 0) break;
		printf(", ");
	}
	printf(">");

	return 0;
}