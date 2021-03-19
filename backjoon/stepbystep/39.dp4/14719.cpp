#include<stdio.h>

int N, M;
int arr[500];

int main(){
	scanf("%d %d", &N, &M);
	for(int i = 0; i < M; i++)
		scanf("%d", &arr[i]);

	int ans = 0;

	for(int i = 1; i <= N; i++){
		bool left = false;
		int cnt = 0;

		for(int j = 0; j < M; j++){
			if(!left){
				if(arr[j] < i) continue;
				else left = true;
			}else{
				if(arr[j] < i) cnt++;
				else{
					ans += cnt;
					cnt = 0;
				}
			}
		}
	}

	printf("%d\n", ans);
	return 0;
}