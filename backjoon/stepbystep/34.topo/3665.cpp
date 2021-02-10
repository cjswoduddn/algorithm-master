#include<stdio.h>
#include<vector>
using namespace std;

int T, N, M, u, v;

int main(){
	scanf("%d", &T);
	while(T--){
		scanf("%d", &N);
		vector<int> arr(N+1);
		vector<int> crr(N+1);
		for(int i = 1; i <= N; i++){
			scanf("%d", &u);
			crr[u] = arr[u] = i;
		}

		scanf("%d", &M);
		while(M--){
			scanf("%d %d", &u, &v);
			int bigger = crr[u] > crr[v] ? u : v;
			int smaller = crr[u] < crr[v] ? u : v;
			arr[bigger]--;
			arr[smaller]++;
		}
		vector<int> brr(N+1, -1);
		bool flag = true;
		for(int i = 1; i <= N; i++){
			if(brr[arr[i]] != -1){
				flag = false;
				break;
			}
			brr[arr[i]] = i;
		}
		if(!flag){
			printf("IMPOSSIBLE\n");
			continue;
		}
		for(int i = 1; i <= N; i++)
			printf("%d ", brr[i]);
		printf("\n");
	}
	return 0;
}