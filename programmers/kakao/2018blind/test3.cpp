#include <string>
#include <sstream>
#include <vector>

using namespace std;
typedef pair<int, string> pis;
bool checkHip(string& str){
	for(char c : str){
		if(c == '-') return true;
	}
	return false;
}

vector<int> getPi(string& str){
	vector<int> pi(str.size(), 0);
	int j = 0;
	for(int i = 1; i < str.size(); i++){
		while(j > 0 && str[i] != str[j])
			j = pi[j-1];
		if(str[i] == str[j])
			pi[i] = ++j;
	}
	return pi;
}

vector<int> kmp(string& fline, string& sline, vector<int>&pi){
	vector<int> n;
	int j = 0;
	for(int i = 0; i < fline.size(); i++){
		while(j > 0 && fline[i] != sline[j]){
			j = pi[j-1];
		}
		if(fline[i] == sline[j]){
			j++;
			if(j == sline.size()){
				n.push_back(i-sline.size()+2);
				j = 0;
			}
		}
	}
	return n;
}

void dfs(int cur, vector<vector<int> >& g, vector<string>& ggg,
	vector<pis>&value, vector<int>&idx){
	ggg.push_back(value[idx[cur]].second);
	dfs(g[cur][0], g, ggg, value, idx);
}

vector<string> solution(vector<string> data, string word){
	vector<string> ans;
	vector<vector<int> > g(data.size()+1);
	vector<pis> value;
	vector<int> idx(data.size()+1);


	for(string& str : data){
		stringstream stream(str);
		vector<string> dparse(3);
		for(int i = 0; i < 3; i++)
			stream >> dparse[i];

		int id = stoi(dparse[0]);
		int pid = stoi(dparse[2]);

		g[id].push_back(pid);
		value.push_back({id, dparse[1]});
		idx[id] = value.size()-1;
	}

	vector<int> pi = getPi(word);
	bool exec = false;
	vector<int> arr;
	int retId = -1;

	for(int i = 0; i < value.size(); i++){
		if(!checkHip(value[i].second)) continue;
		if(exec){
			if(word == value[i].second && retId > value[i].first){
				retId = value[i].first;
			}
		}else{
			vector<int> brr = kmp(value[i].second, word, pi);
			if(brr.size() > arr.size()){
				retId = value[i].first;
				arr = brr;
			}else if(brr.size() == arr.size()){
				bool flag = false;
				for(int i = 0; i < brr.size(); i++){
					if(brr[i] == arr[i]) continue;
					else if(brr[i] < arr[i]){
						flag = true;
						arr = brr;
						retId = value[i].first;
						break;
					}
				}
				if(flag == false && retId > value[i].first){
					arr = brr;
					retId = value[i].first;
				}
			}
		}
	}

	if(retId == -1){
		ans.push_back("Your search for ("+word+") didn't return any results");
		return ans;
	}

	vector<string> ggg;
	dfs(retId, g, ggg, value, idx);
	for(int i = ggg.size()-1; i >= 0; i--)
		ans.push_back(ggg[i]);
	return ans;
}

int main(){
	return 0;
}