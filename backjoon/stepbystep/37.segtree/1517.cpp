#include<stdio.h>
typedef long long ll;

int N, arr[500000], brr[500000];

ll merge(int s, int e){
	if(s == e) return 0;
	int mid = (s+e)/2;
	ll ret = merge(s, mid)+merge(mid+1, e);

	ll value = 0;
	int lidx = s, ridx = mid+1;
	int bidx = s;
	while(lidx <= mid && ridx <= e){
		while(lidx <= mid && arr[lidx] <= arr[ridx]){
			brr[bidx++] = arr[lidx++];
			int tmp = lidx-bidx;
			tmp = tmp < 0 ? tmp*-1 : tmp;
			value += tmp;
		}
		while(ridx <= e && arr[ridx] < arr[lidx]){
			brr[bidx++] = arr[ridx++];
			int tmp = ridx-bidx;
			tmp = tmp < 0 ? tmp*-1 : tmp;
			value += tmp;
		}
	}

	while(lidx <= mid){
		brr[bidx++] = arr[lidx++];
		int tmp = lidx-bidx;
		tmp = tmp < 0 ? tmp*-1 : tmp;
		value += tmp;
	}
	while(ridx <= e){
		brr[bidx++] = arr[ridx++];
		int tmp = ridx-bidx;
		tmp = tmp < 0 ? tmp*-1 : tmp;
		value += tmp;
	}

	for(int i = s; i <= e; i++)
		arr[i] = brr[i];

	return ret+(value/2);
}

int main(){
	scanf("%d", &N);
	for(int i = 0; i < N; i++)
		scanf("%d", &arr[i]);

	printf("%lld\n", merge(0, N-1));
	return 0;
}