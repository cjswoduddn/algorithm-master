#include <iostream>
#include <cmath>
#include <vector>
#include <limits.h>
#define MAX 10000000

using namespace std;
const int INF = INT_MAX;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<bool> isPrime(MAX+1, true);
	isPrime[1] = false;

	vector<int> prime;
	for(int i = 2; i <= MAX; i++){
		if(isPrime[i]){
			prime.push_back(i);
			if(i >= sqrt(INF)) continue;
			for(int j = i*i; j <= MAX; j+=i){
				isPrime[j] = false;
			}
		}
	}

	int N;
	cin >> N;

	for(int pm : prime){
		while(N > 1 && N%pm==0){
			cout << pm << '\n';
			N /= pm;
		}
	}
	return 0;
}