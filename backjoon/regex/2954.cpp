#include <iostream>
#include <regex>
#include <vector>

using namespace std;

int main(){
	regex re("([aeiou])p[aeiou]");
	smatch match; // 매칭된 문자열을 string형태로 돌려줌

	string str;
	getline(cin, str);

	cout << regex_replace(str, re, "$1");
	return 0;
}
