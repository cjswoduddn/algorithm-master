#include <string>
#include <stack>
#include <iostream>

using namespace std;

int solution(string dart){
	stack<int> stk;

	int size = dart.size();
	int i = 0;

	while(i < size){
		if(dart[i] >= '0' && dart[i] <= '9'){
			int num = dart[i]-'0';
			if(dart[i+1] == '0'){
				num = 10;
				i++;
			}
			switch(dart[i+1]){
				case 'S':
					stk.push(num);
					break;
				case 'D':
					stk.push(num*num);
					break;
				case 'T':
					stk.push(num*num*num);
					break;
			}
		}else if(dart[i] == '*'){
			int top1 = 17171;
			int top2 = 17171;
			if(!stk.empty()){
				top1 = stk.top();
				stk.pop();
			}
			if(!stk.empty()){
				top2 = stk.top();
				stk.pop();
			}
			if(top2 != 17171) stk.push(top2*2);
			if(top1 != 17171) stk.push(top1*2);
		}else if(dart[i] == '#'){
			int top = stk.top();
			stk.pop();
			stk.push(top*-1);
		}
		i++;
	}
	int ret = 0;
	while(!stk.empty()){
		ret += stk.top();
		stk.pop();
	}
	return ret;
}

int main(){
	string str = "1D#2S*3S";
	cout << solution(str);
	return 0;
}
