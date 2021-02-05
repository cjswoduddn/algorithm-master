#include<string>
#include<iostream>
using namespace std;

int N, k;
string str;
int cur = 0;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	while(N--){
		cin >> str;
		if(str != "all" && str != "empty"){
			cin >> k;
			k--;
		}
		int bit = 1<<k;
		if(str == "add")
			cur |= bit;
		else if(str == "remove")
			cur &= (~bit);
		else if(str == "check"){
			if((cur&bit)) printf("1\n");
			else printf("0\n");
		}else if(str == "toggle")
			cur ^= bit;
		else if(str == "all")
			cur = (1<<20)-1;
		else if(str == "empty")
			cur = 0;
	}
	return 0;
}