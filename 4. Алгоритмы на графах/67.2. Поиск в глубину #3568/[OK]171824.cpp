#include <iostream> 
#include <fstream> 
#include <vector> 
#include <alg.h>
//#include <set>
#include <stack>


using namespace std;



struct root {
	bool inPath = false;
	bool full = false;
	int ans = 0;
};
vector<root>* mainInf;



int firstPosOfFalse = 0;

/*void Push(int i) {
Queue.push(i);
}*/

bool isFull(int num) {
	return (mainInf->begin() + num - 1)->full;
}

bool isInPath(int num) {
	return (mainInf->begin() + num - 1)->inPath;
}

void setPath(int num, bool path) {
	(mainInf->begin() + num - 1)->inPath = path;
}
void setAns(int num, int answer) {
	(mainInf->begin() + num - 1)->ans = answer;
}

int getAns(vector<root>::iterator beg, int num) {
	return (beg + num - 1)->ans;
}

void setFull(int num) {
	(mainInf->begin() + num - 1)->full = true;
}

bool hasAns(int num) {
	return (mainInf->begin() + num - 1)->ans ? true : false;
}

bool checkBothClause(int num) {
	return !isFull(num) && !isInPath(num);
}

bool FindNotFull(int size) {
	for (int i = firstPosOfFalse; i < size; i++)
		if (checkBothClause(i + 1)) {
			firstPosOfFalse = i;
			return true;
		}
	return false;
}



void Push(stack<vector<vector<int>>::iterator>* Stack, vector<vector<int>>::iterator beg, int place, int* ans) {
	Stack->push(beg + place);
	if(!hasAns(place+1))
		setAns(place + 1, (*ans)++);
	setPath(place + 1, true);
}
int count1 = 1;

vector<vector<int>>::iterator Pop(stack<vector<vector<int>>::iterator>* Stack, vector<vector<int>>::iterator beg, int now) {
	vector<vector<int>>::iterator ans = Stack->top();
	Stack->pop();
	int pl = ans - beg;
	setPath(pl + 1, false);
	setFull(now + 1);
	if (!hasAns(now + 1))
		setAns(now + 1, count1++);
	return ans;
}

void printAns(ofstream& out) {
	vector<root>::iterator beg = mainInf->begin();
	out << getAns(beg, 1);
	for (int i = 1; i < mainInf->size(); i++)
		out << " " << getAns(beg, i + 1);
	out << "\n";
}
//set<int, root> mainS;

int main() {
	ifstream fin("input.txt");
	int n1;
	fin >> n1;
	vector<vector<int>> mas(n1);
	mainInf = new vector<root>(n1);
	int I;
	vector<vector<int>>::iterator beg = mas.begin(), last;
	for (int i = 0; i < n1; i++) {
		for (int j = 0; j < n1; j++) {
			fin >> I;
			I ? (beg + i)->push_back(j) : 0;

		}
		//if ((beg + i)->size() == 0) setFull(i + 1);
	}
	fin.close();
	// запомнил все дуги

	ofstream fout("output.txt");
	beg = mas.begin();
	//	last = mas.end();
	int temp;

	stack<vector<vector<int>>::iterator> path;
	//fout << "1";
	//path.push()
	//vector<int>::iterator b = mas.begin();
	vector<vector<int>>::iterator tempIt;
	vector<int>::iterator tempBeg;
	//int tempInt;
	bool flag = false;
	//int temp;

	while (FindNotFull(n1)) {
		tempIt = beg + firstPosOfFalse;
		do {
			tempBeg = tempIt->begin();
			for (int i = 0; i<tempIt->size(); i++) {
				if (checkBothClause(*(tempBeg + i) + 1)) {
					Push(&path, beg, tempIt - beg, &count1);
					tempIt = beg + *(tempBeg + i);
					flag = true;
					break;
				}
			}

			if(flag==false)
				setFull(tempIt - beg + 1);
			if (flag == false && !path.empty()) {
				tempIt = Pop(&path, beg, tempIt - beg);
						
			}
			if (path.empty()) {
				if (!hasAns(tempIt - beg + 1))
					setAns(tempIt - beg + 1, count1++);
			}
			flag = false;
		} while (!path.empty() || !isFull(tempIt-beg+1));
	}
	printAns(fout);

	//fout << "\n";
	//delete[] sd;
	fout.close();

	return 0;
}