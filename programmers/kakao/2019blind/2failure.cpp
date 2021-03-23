#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;
typedef long long ll;
class nod{
public:
	int up;
	int down;
	int num;
};
class cmp{
public:
	bool operator()(nod a, nod b){
		ll left = (ll)a.up*b.down;
		ll right = (ll)b.up*a.down;
		if(left == right) return a.num < b.num;
		return left > right;
	}
};

vector<int> solution(int N, vector<int> stages){
	vector<int> failCnt(N+2, 0);
	for(int ptr : stages)
		failCnt[ptr]++;

	vector<int> sum(N+2, 0);
	for(int i = 0; i <= N; i++)
		sum[i+1] = sum[i]+failCnt[i+1];

	vector<nod> arr;
	for(int i = 1; i <= N; i++)
		arr.push_back({failCnt[i], sum[N+1]-sum[i-1], i});

	sort(arr.begin(), arr.end(), cmp());
	for(nod n : arr)
		cout << n.up << " " << n.down << " " << n.num << '\n';

	vector<int> ret;
	for(nod n : arr)
		ret.push_back(n.num);

	return ret;
}

int main(){
	int n = 5;
	vector<int> arr = {2, 1, 2, 6, 2, 4, 3, 3};
	solution(n, arr);
	return 0;
}