#include <iostream>
#include <regex>
#include <string>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	regex re("[A-Z][a-z]{0,}");
	smatch match;
	string str;
	cin >> str;

	int ret = 0;
	while(regex_search(str, match, re)){
		string mstr = match.str();
		if(mstr.size() == str.size()) break;
		int tmp = 4-(mstr.size()%4);
		if(tmp != 4) ret += tmp;
		str = match.suffix();
	}

	cout << ret;

}
