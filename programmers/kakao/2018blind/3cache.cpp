#include <string>
#include <vector>
#include <iostream>
#define x first
#define y second

using namespace std;
typedef pair<int, string> pis;


int solution(int csize, vector<string> cities){
	vector<pis> cache(csize, {-1, ""});
	int idx = 0;
	int ret = 0;

	for(string& str : cities){
		int sidx=-1;
		for(int i = 0; i < csize; i++){
			if(str == cache[i].y){
				sidx = i;
				break;
			}
		}

		if(sidx != -1){
			ret += 1;
			cache[sidx].x = idx++;
		}else{
			int min = 10000000;
			for(int i = 0; i < csize; i++){
				if(cache[i].x < min){
					sidx = i;
					min = cache[i].x;
				}
			}
			cache[sidx].x = idx++;
			cache[sidx].y = str;
			ret += 5;
		}
	}
	return ret;
}

int main(){
	int size = 2;
	vector<string> arr = {
		};
	cout << solution(size, arr);

	return 0;
}