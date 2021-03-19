#include <string>
#include <vector>
using namespace std;

int dp[2][202];

int recur(vector<string>& str, int s){

}

int solution(vector<string> arr){
	for(int i = 0; i < 202; i++)
		dp[0][i] = dp[1][i] = -1;
	// 0은 연산을 바로 한 경우 1은 연산을 다음 연산과 한 경우
	return recur(arr, 0, );
}
