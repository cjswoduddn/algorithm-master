#include <string>
#include <regex>
#include <iostream>
using namespace std;

int main(){
	regex re(".*problem.*", regex::icase);

	string str;
	while(getline(cin, str)){
		if(regex_match(str, re))
			cout << "yes" << '\n';
		else 
			cout << "no\n";
	}

	return 0;
}