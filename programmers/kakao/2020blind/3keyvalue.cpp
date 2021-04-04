#include <vector>

using namespace std;

vector<vector<int>> rotateing(vector<vector<int>>& arr, int size){
	vector<vector<int>> tmp(size, vector<int>(size));
	for(int i = 0; i < size; i++){
		for(int j = 0; j < size; j++){
			tmp[i][j] = arr[size-j-1][i];
		}
	}
	return tmp;
}
int getLockHome(vector<vector<int>>& lock, int lsize){
	int ret = 0;
	for(int i = 0; i < lsize; i++){
		for(int j = 0; j < lsize; j++){
			if(lock[i][j] == 0) ret++;
		}
	}
	return ret;
}

bool check(vector<vector<int>>&map, vector<vector<int>>&key,
	int x, int y, int ans){
	int tmp = 0;
	for(int i = 0; i < key.size(); i++){
		for(int j = 0; j < key.size(); j++){
			int mvalue = map[x+i][y+j];
			int kvalue = key[i][j];
			if(mvalue == -1) continue;
			if(mvalue == 0 && kvalue == 1) tmp++;
			if(mvalue == 1 && kvalue == 1) return false;
		}
	}
	if(tmp == ans) return true;
	return false;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock){
	int ksize = key.size();
	int lsize = lock.size();
	int size = ksize*2+lsize;
	vector<vector<int>> map(size, vector<int>(size, -1));

	for(int i = 0; i < lsize; i++){
		for(int j = 0; j < lsize; j++){
			map[ksize+i][ksize+j] = lock[i][j];
		}
	}

	int lh = getLockHome(lock, lsize);
	vector<vector<int>> key1 = rotateing(key, ksize);
	vector<vector<int>> key2 = rotateing(key1, ksize);
	vector<vector<int>> key3 = rotateing(key2, ksize);

	for(int i = 0; i < size-ksize; i++){
		for(int j = 0; j < size-ksize; j++){
			if(check(map, key, i, j, lh)) return true;
			if(check(map, key1, i, j, lh)) return true;
			if(check(map, key2, i, j, lh)) return true;
			if(check(map, key3, i, j, lh)) return true;
		}
	}
	return false;
}

int main(){
	return 0;
}