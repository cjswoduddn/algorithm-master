#include <iostream>
#include <regex>
#include <string>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	regex re("[ABCDEF]?A+F+C+[ABCDEF]?$");

	int N;
	cin >> N;
	while(N--){
		string str;
		cin >> str;
		if(regex_match(str, re))
			cout << "Infected!\n";
		else 
			cout << "Good\n";
	}
	return 0;
}
