#include <iostream>
#include <cmath>
#include <vector>
#define MAX 10000

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<bool> isPrime(MAX+1, true);
	isPrime[1] = false;

	for(int i = 2; i <= sqrt(MAX); i++){
		if(isPrime[i]){
			for(int j = i*i; j <= MAX; j+=i)
				isPrime[j] = false;
		}
	}

	int N, M;
	cin >> N >> M;

	int maxValue = 0;
	int minValue = 10000;

	for(int i = N; i <= M; i++){
		if(isPrime[i]){
			maxValue += i;
			minValue = minValue < i ? minValue : i;
		}
	}

	if(maxValue == 1 || minValue == 10000) cout << -1 << '\n';
	else cout << maxValue << '\n' << minValue << '\n';
	return 0;

}
