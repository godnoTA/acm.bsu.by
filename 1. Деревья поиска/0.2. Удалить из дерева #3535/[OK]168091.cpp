#include <iostream>
#include <fstream>
#include <string>
using namespace std;



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



void leftWay(root* main) {
	if (main == nullptr)
		return;
	fout << main->info << endl;
	leftWay(main->leftS);
	leftWay(main->rightS);
	return;
}

/*
void leftWay(root* main) {
	if (main == nullptr)e
		return;
	if (main->wasHere == false) {
		fout  << main->info<<endl;
	}
	main->wasHere = true;
	if (main->leftS != nullptr && main->leftS->wasHere == false) {
		leftWay(main->leftS);
	}
	if (main->rightS != nullptr && main->rightS->wasHere == false) {
		leftWay(main->rightS);
	}
	if (main->father == nullptr)
		return;
	leftWay(main->father);
}
*/

void addARoot(root* n, root* m1) {
	root* m = m1;
	root* help1 = m;
	root* next = m;
	while (next != nullptr) {
		m = next;
		if (n->info > m->info) {
			next = m->rightS;
		}
		else if (n->info == m->info) {
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
int delR;


void createATree() {
	ifstream f("input.txt");
	f >> delR;
	string s;
	//f >> s;
	f >> m->info;
	
	while (f >> s) {
		root *n = new root(atoi(s.c_str()));
		addARoot(n, m);
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
}

void EraseIt(root * &s)
{
	if (s->father != nullptr)
	{
		if ((s->father)->leftS == s)
			(s->father)->leftS = nullptr;
		else
			(s->father)->rightS = nullptr;
	}
	else m = nullptr; //если лист-корень
	delete s;
	s = nullptr;
}

bool find(root* R, int a, root* &t)
{
	if (R == NULL)
	{
		t = NULL;
		return false;
	}
	t = R;
	for (;;)
	{
		if (t->info == a)
			return true;
		if (t->info > a)
		{
			if (t->leftS == NULL)
				return false;
			t = t->leftS;
		}
		else
		{
			if (t->rightS == NULL)
				return false;
			t = t->rightS;
		}
	}
}

void EraseNd(root* &s)
// удаление вершины, имеющей не более одного сына
{
	root *q;
	if (s->leftS != NULL)
		q = s->leftS;
	else
		q = s->rightS;
	if (q != NULL)
		q->father = s->father;
	if (s->father == NULL)
		m = q;
	else
		if ((s->father)->leftS == s)
			(s->father)->leftS = q;
		else
			(s->father)->rightS = q;
	delete s;	s = NULL;
}


bool Erase(root* &R, int info)
// удаление вершины по значению
{
	root *s, *q;
	if (!find(R, info, s))
		return false;
	else
	{
		if ((s->leftS != NULL) && (s->rightS != NULL))
		{
			q = s->rightS;
			while (q->leftS != NULL)
				q = q->leftS;
			s->info = q->info;
			EraseNd(q);
		}
		else
			EraseNd(s);
		return true;
	}
}



int main() {
	m = new root();
	
	createATree();
	
	//fout << m->info<<endl;
	m->wasHere = true;
	Erase(m, delR);
	leftWay(m);
	//fout << "\n";
	deleteTree(m);
	return 0;
}
