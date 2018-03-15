#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <thread>
#include <alg.h>
using namespace std;

//int mas[20];
int count1 = 0;

class root {
public:
	int info;
	root* leftS;
	root* rightS;
	root* father;
	bool wasHere;
	root() {
		set();
	}
	root(int inf) {
		set();
		this->info = inf;
	}
private:
	void set() {
		this->rightS = nullptr;
		this->leftS = nullptr;
		this->father = nullptr;
	}
	
};

root* m;
ofstream fout("output.txt");

/*
void leftWay(root* main,vector<int> * v) {
	//fout << main->info << std::endl;
	leftWay(main->leftS,v);
	leftWay(main->rightS,v);
}

void leftWayMain(root* main, vector<int>* v3) {
	if (main == nullptr)
		return;
	//v->insert(v->end(), main->info);

	vector<int> v1;
	vector<int> v2;
	v3->insert(v3->end(), main->info);

	thread thr(leftWayMain, main->rightS, &v2);
	leftWayMain(main->leftS, &v1);
	if (thr.joinable())
		thr.join();
	v1.insert(v1.end(), v2.begin(), v2.end());
	v3->insert(v3->end(), v1.begin(), v1.end());
}*/



void leftWay(root* main) {
	if (main == nullptr)
		return;
	fout << main->info << std::endl;
	leftWay(main->leftS);
	leftWay(main->rightS);
}


void addARoot(root* n, root* m1) {
	root* m = m1;
	root* help1 = m;
	root* next = m;
	while (next != nullptr) {
		m = next;
		if (n->info > m->info) {
			next = m->rightS;
		}
		else if (n->info==m->info) {
			return;
		}
		else {
			next = m->leftS;
		}
	}

	if (n->info > m->info) {
		m->rightS = n;
	}
	else {
		m->leftS = n;
	}
	n->father = m;
}
	
void createATree() {
	ifstream f("input.txt");
	f>>m->info;
//	mas[count1++] = m->info;
	string s;

	while ((f>>s)) {
		//f >> s;
	//	mas[count1++] = (atoi(s.c_str()));
		root *n = new root(atoi(s.c_str()));
		addARoot(n,m);
	}
}
void deleteTree(root* m) {
	while (m != nullptr) {
		if (m->leftS != nullptr) {
			m = m->leftS;
			continue;
		}
		if (m->rightS != nullptr) {
			m = m->rightS;
			continue;
		}
		if (m->father != nullptr)
		{
			root* help = m;
			m = m->father;
			if (m->leftS == help)m->leftS = nullptr;
			if (m->rightS == help)m->rightS = nullptr;
			delete help;
		}
		else {
			delete m;
			m = nullptr;
		}
	}
	fout.close();
}

void printVector(vector<int> v) {
	for (int i : v) {
		fout << i << "\n";
	}
}

int main() {
	try{
	m = new root();
	
	createATree();
	m->wasHere = true;
	
	//vector<int> v;
	leftWay(m);
	//leftWayMain(m,&v);
	//printVector(v);
	
	deleteTree(m);
	}
	catch (...) {

	}
	
	return 0;
}
