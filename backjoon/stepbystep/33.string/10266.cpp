#include<vector>
#include<stdio.h>
#include<algorithm>
using namespace std;

int N, n;
vector<bool> up(720000, false);
vector<bool> down(360000, false);

vector<int> getPi(vector<bool>& down){
	vector<int> pi(360000, 0);
	int j = 0;
	for(int i = 1; i < 360000; i++){
		while(j > 0 && down[i] != down[j])
			j = pi[j-1];
		if(down[i] == down[j])
			pi[i] = ++j;
	}
	return pi;
}

bool kmp(vector<bool>& up, vector<bool>& down, vector<int>& pi){
	int j = 0;
	for(int i = 0; i < 720000; i++){
		while(j > 0 && up[i] != down[j])
			j = pi[j-1];
		if(up[i] == down[j]){
			j++;
			if(j == 360000) return true;
		}
	}
	return false;
}

int main(){
	scanf("%d", &N);
	for(int i = 0; i < N; i++){
		scanf("%d", &n);
		up[n] = up[n+360000] = true;
	}
	for(int i = 0; i < N; i++){
		scanf("%d", &n);
		down[n] = true;
	}

	vector<int> pi = getPi(down);
	if(kmp(up, down, pi)) printf("possible\n");
	else printf("impossible\n");
	return 0;
}