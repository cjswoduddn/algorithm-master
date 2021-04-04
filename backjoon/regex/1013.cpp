#include <iostream>
#include <regex>
#include <vector>

using namespace std;

int main(){
	regex re("(100+1+|01)+");
	int N;

	cin >> N;
	string str;
	while(N--){
		cin >> str;
		bool flag = regex_match(str, re);
		if(flag) cout << "YES" << '\n';
		else cout << "NO" << '\n';
	}

	return 0;
}