#include <string>
#include <regex>
#include <iostream>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N;
	cin >> N;
	while(N--){
		string regexstr;
		string src;

		cin >> regexstr >> src;
		int wc = 0;
		while(regexstr[wc] != '_') wc++;

		regexstr[wc] = '.';
		regex re(regexstr);
		if(!regex_match(src, re))
			cout << "!\n";
		else {
			regexstr[wc] = '1';
			regex re(regexstr);
			if(regex_match(src, re)){
				cout << "_\n";
			}else{
				for(int i = 0; i < 26; i++){
					regexstr[wc] = 'A'+i;
					regex re(regexstr);
					if(regex_match(src, re)){
						cout << (char)('A'+i) << '\n';;
						break;
					}
				}
			}
		}
	}
	return 0;
}