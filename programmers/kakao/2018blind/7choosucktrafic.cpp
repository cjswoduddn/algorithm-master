#include <string>
#include <vector>

using namespace std;

int solution(vector<string> lines){
	int ans = 0;

	vector<int> start;
	vector<int> end;
	for(string& str : lines){
		str.pop_back();
		string hour, minute, sec, msec, duration;

		hour = str.substr(11, 2);
		minute = str.substr(14, 2);
		sec = str.substr(17, 2);
		msec = str.substr(20, 3);
		duration = str.substr(24);

		int ch, cm, cs, cms, cd;
		ch = stoi(hour)*3600*1000;
		cm = stoi(minute)*60*1000;
		cs = stoi(sec)*1000;
		cms = stoi(msec);
		cd = stof(duration)*1000;

		int etime = ch+cm+cs+cms;
		int stime = etime-cd+1;

		start.push_back(stime);
		end.push_back(etime);
	}

	int ret = 0;
	for(int i = 0; i < end.size(); i++){
		int dtime = end[i]+1000;
		int count = 0;
		for(int j = i; j < start.size(); i++){
			if(start[j] < dtime) count++;
		}
		ret = ret > count ? ret : count;
	}
	return ret;
}

int main(){
	return 0;
}

