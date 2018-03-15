#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

struct Para {
	int kv;
	int dp;
	Para(int k = 0,int d = -1):kv(k),dp(d) {}
	Para(Para* mm):kv(mm->kv),dp(mm->dp) {}
};

class KNode {
public:
	int info;
	KNode* left;
	KNode* right;
public:
	KNode(int inf = 0, KNode* l = NULL, KNode* r = NULL) {
		info = inf;
		left = l;
		right = r;
	}
	int GetInfo() {
		return info;
	}
	void SetInfo(int in) {
		info = in;
	}
	KNode* GetLeft() {
		return left;
	}
	KNode* GetRight() {
		return right;
	}
	void SetLeft(KNode* l) {
		left = l;
	}
	void SetRight(KNode* r) {
		right = r;
	}
};

class KBTree {
	KNode* t;
public:
	KBTree(KNode* k = NULL) {
		t = k;
	}
	KNode* GetRoot() {
		return t;
	}
	void SetRoot(KNode* k) {
		t = k;
	}
	void Insert(int in) {
		KNode* p = t;
		KNode** pp = &p;
		int a;
		if(t==NULL) {;
			t = new KNode(in,NULL,NULL);
		}
		else {
			while(p!=NULL) {
				a = p->GetInfo();
				if(a==in) break;			
				if(a<in) {
					pp = &p->right;
					p = p->GetRight();
				}
				else {
					pp = &p->left;
					p = p->GetLeft();
				}			
			}
			if(a!=in) {
				*pp = new KNode(in,NULL,NULL);
			}
		}
	}
	void DeleteNode(int a) {
		KNode* p = this->GetRoot();
		KNode* pp = NULL;
		while(p!=NULL && p->GetInfo()!=a) {
			pp = p;
			if (a < p->GetInfo()) {
				p = p->GetLeft();
			}
			else {
				p = p->GetRight();
			}
		}
		if (p != NULL) {
			KNode* del = NULL; 
			if (p->GetLeft() == NULL || p->GetRight() == NULL) {   
				KNode* child = NULL;
				del = p;
				if (p->GetLeft() != NULL) {
					child = p->GetLeft();
				}
				else {
					if (p->GetRight() != NULL) {
						child = p->GetRight();
					}
				}
				if (pp == NULL) {
					this->SetRoot(child);
				}
				else
				{
					if (pp->GetLeft() == p) {
						pp->SetLeft(child);
					}
					else {
						pp->SetRight(child); 
					}
				}				
			}
			else {
				KNode* mostLeft = p->GetRight();
				KNode* mostLeftParent = p;            
				while (mostLeft->left != NULL) {
					mostLeftParent = mostLeft;
					mostLeft = mostLeft->GetLeft();
				} 
				p->SetInfo(mostLeft->GetInfo());
				del = mostLeft;		
				if (mostLeftParent->left == mostLeft) {
					mostLeftParent->left = mostLeft->right;
				}
				else {
					mostLeftParent->right = mostLeft->right;
				}
			}
			delete del;
		}
	}
};

void ObchodsV(KNode* k,vector<int> &o);
int max(int a,int b);
Para Kolichestvo(KNode* k);
bool SrTree(KNode* k);
void Obchod(KNode* k,ofstream &o);

int main()
{
	ifstream in("in.txt");
	ofstream on("out.txt");
	vector<int> v;
	KBTree* myTree = new KBTree();
	if(in!=NULL) {
		int c;
		in >> c;
		while(! in.eof()) {
			myTree->Insert(c);
			in >> c;
		}
		myTree->Insert(c);
		in.close();
	}
	ObchodsV(myTree->GetRoot(),v);
	if(v.size()%2 == 1) {
		int j = v[v.size()/2];
		myTree->DeleteNode(j);
	}	
	Obchod(myTree->GetRoot(),on);
	on.close();
	return 0;
}


void Obchod(KNode* k,ofstream &o) {
	o << k->GetInfo() << endl;
	if(k->GetLeft()!=NULL) {
		Obchod(k->GetLeft(),o);
	}		
	if(k->GetRight()!=NULL) {
		Obchod(k->GetRight(),o);
	}
}

void ObchodsV(KNode* k,vector<int> &o) {
	if(k->GetLeft()!=NULL) {
		ObchodsV(k->GetLeft(),o);
	}
	if(SrTree(k)) {
		o.push_back(k->GetInfo());
	}
	if(k->GetRight()!=NULL) {
		ObchodsV(k->GetRight(),o);
	}
}

int max(int a,int b) {
	if(a<b) {return b;}
	return a;
}

Para Kolichestvo(KNode* k) {
	Para m(0,0);
	if(k == NULL) {
		m.kv = 0;
		m.dp = -1;
	}
	else {
		Para lm = Kolichestvo((k->GetLeft()));
		Para rm = Kolichestvo(k->GetRight());
		m.kv = lm.kv + rm.kv + 1;
		m.dp = max(lm.dp,rm.dp) +1;
	}
	return m;
}

bool SrTree(KNode* k) {
	if(k == NULL) {
		return false;
	}
	else {
		Para l = Kolichestvo(k->GetLeft());
		Para r = Kolichestvo(k->GetRight());
		if(l.kv!=r.kv) {
			return false;
		}
		else {
			if(l.dp == r.dp) {
				return false;
			}
			else {
				return true;
			}
		}
	}
}