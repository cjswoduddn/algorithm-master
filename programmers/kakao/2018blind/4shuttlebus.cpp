#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int getValue(string& t){
	int hour = (t[0]-'0')*10+t[1]-'0';
	int minute = (t[3]-'0')*10+t[4]-'0';
	return hour*60+minute;
}

string solution(int n, int t, int m, vector<string> tt){
	vector<int> bus(n, 0);
	string startBus = "09:00";
	int startTime = getValue(startBus);
	int lastTime = startTime+n*t-1;

	vector<int> table;
	for(string& str : tt)
		table.push_back(getValue(str));

	sort(table.begin(), table.end());

	for(int ptr : table){
		for(int i = 0; i < n; i++){
			int value = startTime+i*t;
			if(ptr <= value && bus[i] < m){
				bus[i]++;
				if(i == n-1 && bus[n-1] == m){
					lastTime = ptr-1;
				}
				break;
			}
		}
	}

	cout << lastTime << '\n';
	int hour = lastTime/60;
	string shour;
	if(hour < 10) shour = "0"+to_string(hour);
	else shour = to_string(hour);

	int minute = lastTime-60*hour;
	string sminute;
	if(minute < 10) sminute = "0"+to_string(minute);
	else sminute = to_string(minute);
	return shour+":"+sminute;
}

int main(){
	cout << solution(2, 10, 2, {"09:10", "09:09", "08:00"});
	return 0;
}