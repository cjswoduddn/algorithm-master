#include <vector>
#include <algorithm>
#include <iostream>
#define x first
#define y second

using namespace std;
typedef pair<int, int> pii;
class info{
public:
	pii pos;
	int num;
};
class cmp{
public:
	bool operator()(const info& a, const info& b){
		return (a.pos).y > (b.pos).y;
	}
};
class nod{
public:
	pii pos;
	int num;
	nod *lchild;
	nod *rchild;

	nod(){
		lchild = rchild = NULL;
		num = -1;
	}

	void insert(info item){
		if(this->num == -1){
			this->pos = item.pos;
			this->num = item.num;
			return;
		}

		if(this->pos.x > item.pos.x){
			if(this->lchild == NULL)
				this->lchild = new nod();
			this->lchild->insert(item);
		}else{
			if(this->rchild == NULL)
				this->rchild = new nod();
			this->rchild->insert(item);
		}
	}

	void inorder(vector<int>& ret){
		ret.push_back(this->num);
		if(this->lchild != NULL)
			this->lchild->inorder(ret);
		if(this->rchild != NULL)
			this->rchild->inorder(ret);
	}
	void preorder(vector<int>& ret){
		if(this->lchild != NULL)
			this->lchild->preorder(ret);
		if(this->rchild != NULL)
			this->rchild->preorder(ret);
		ret.push_back(this->num);
	}
};


vector<vector<int> > solution(vector<vector<int> > nodes){
	vector<info> arr;
	for(int i = 0; i < nodes.size(); i++)
		arr.push_back({pii(nodes[i][0], nodes[i][1]), i+1});

	sort(arr.begin(), arr.end(), cmp());
	nod *root = new nod();

	for(info item : arr)
		root->insert(item);

	vector<int> inod;
	vector<int> preod;
	root->inorder(inod);
	root->preorder(preod);
	vector<vector<int> > ret;
	ret.push_back(inod);
	ret.push_back(preod);

	for(int ptr : inod)
		cout << ptr << " ";
	cout << '\n';
	for(int ptr : preod)
		cout << ptr << " ";
	cout << '\n';
	return ret;

}

int main(){
	vector<vector<int> > nodes = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
	solution(nodes);
	return 0;
}