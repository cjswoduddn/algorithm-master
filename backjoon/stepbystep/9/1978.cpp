#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int N=1000;

int main(){
	vector<bool> isPrime(N+1, true);
	isPrime[1] = false;

	for(int i = 2, size = sqrt(N); i <= size; i++){
		if(isPrime[i]){
			for(int j = i*i; j <= N; j+=i)
				isPrime[j] = false;
		}
	}
	cin >> N;

	int n;
	int ret = 0;
	while(N--){
		cin >> n;
		if(isPrime[n])
			ret++; 
	}
	cout << ret << '\n';
	return 0;
}