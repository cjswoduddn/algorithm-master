#include <string>
#include <regex>
#include <iostream>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	string line;
	regex re("BUG");

	while(getline(cin, line)){
		int size = line.size();
		if(size == 0) cout << "\n";
		else{
			do{
				size = line.size();
				line = regex_replace(line, re, "");
			}while(size != line.size());
			cout << line << '\n';
		}
	}
	return 0;
}
