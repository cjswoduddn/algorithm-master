#include <string>
#include <iostream>
#include <stack>

using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string line;
	stack<char> stk;

	while(getline(cin, line)){
		bool flag = true;
		if(line == ".") break;
		for(char c : line){
			if(c == '(' || c == '[') 
				stk.push(c);
			else if(c == ')'){
				if(stk.empty() || stk.top() == '['){
					flag = false;
					break;
				}else stk.pop();
			}else if(c == ']'){
				if(stk.empty() || stk.top() == '('){
					flag = false;
					break;
				}else stk.pop();
			}
		}
		if(!stk.empty()) flag = false;
		if(flag) cout << "yes" << '\n';
		else cout << "no" << '\n';
		while(!stk.empty()) stk.pop();
	}
	return 0;
}