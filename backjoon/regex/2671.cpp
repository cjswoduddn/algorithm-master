#include <string>
#include <regex>
#include <iostream>

using namespace std;

int main(){
	regex re("(100+1+|01)+");
	string str;
	cin >> str;

	bool flag = regex_match(str, re);
	if(flag) cout << "SUBMARINE";
	else cout << "NOISE";
	return 0;
}