#include <iostream>
#include <fstream>
#include <queue>
#include <alg.h>
#include <functional>

using namespace std;


//queue<infotype> q;
struct infotype {
	long long A;
	long long B;
	long long C;
	infotype(long long a, long long b) {
		A = a;
		B = b;
		C = 0;
	}
	infotype(long long a, long long b,long long c) {
		A = a;
		B = b;
		C = c;
	}
	infotype(const infotype& i) {
		A = i.A;
		B = i.B;
		C = i.C;
	}
	infotype() {
		A = 0;
		B = 0;
		C = 0;
	}
	long long sum() {
		C++;
		long long ans = A + B*C;
		return ans;
	}
	long long sum2() {
		long long ans = A + B*C;
		return ans;
	}
	/*bool operator() (const infotype& a, const infotype& b)
	{
		long long num1 = a.A + a.B*a.C;
		long long num2 = b.A + b.B*b.C;
		if (num1 < num2)  return true;
		return false;
	}*/
	
	//bool compare()
};

class mycomparison
{
	bool reverse;
public:
	mycomparison(const bool& revparam = false)
	{
		reverse = revparam;
	}
	bool operator() (const infotype& lhs, const infotype& rhs) const
	{
		long long num1 = lhs.A + lhs.B*lhs.C;
		long long num2 = rhs.A + rhs.B*rhs.C;

		if (reverse) return (num1>num2);
		else return (num1<num2);
	}
};


/*
class LQueue {
private:
	struct QItem {
		infotype info;
		QItem* next;
		QItem(infotype Ainfo) : info(Ainfo), next(NULL) {}
	};
	QItem *front, *rear;
	unsigned size;

	void Erase();
	void Clone(const LQueue &);
public:
	void Refresh();
	void Refresh(infotype Ainfo);
	LQueue() : front(NULL), rear(NULL), size(0) {};
	LQueue(const LQueue&);
	~LQueue();
	LQueue& operator = (const LQueue&);

	void Push(infotype AInfo);
	bool Pop();
	infotype GetFirst() const;
	bool IsEmpty()const;

	unsigned GetSize() const;
	infotype operator [] (unsigned) const;
	void Browse(void ItemWork(infotype)) const;
	void Browse(void ItemWork(infotype&));
};
LQueue *q = new LQueue();
void LQueue::Erase() {
	while (Pop());
	size = 0;
}

void LQueue::Clone(const LQueue& Q) {
	//for (unsigned i=0; i<Q.size; i++)
	//	Push(Q[i]);
	QItem *tmp = Q.front;
	for (unsigned i = 0; i<Q.size; i++) {
		Push(tmp->info);
		tmp = tmp->next;
	}
}
LQueue::LQueue(const LQueue& Q) {
	size = 0; 	Clone(Q);
}

LQueue::~LQueue() {
	Erase();
}

LQueue& LQueue::operator = (const LQueue& Q) {
	if (this != &Q) {
		Erase();
		Clone(Q);
	}
	return *this;
}
void LQueue::Push(infotype Ainfo) {
	QItem* tmp = new QItem(Ainfo);
	if (size>0)
		rear->next = tmp;
	else
		front = tmp;
	rear = tmp;
	size++;
}

void LQueue::Refresh(infotype Ainfo) {
	//if (this->size == 1)
	//	return;
	QItem* tmp = new QItem(Ainfo);
	long long thisSum = tmp->info.sum2();
	//this->Pop();
	QItem* nowPos = this->front;
	QItem* tmp2 = NULL;
	bool flag=false;
	for (long long i = 0; i < this->GetSize(); i++) {
		if (thisSum < nowPos->info.sum2()) {
			if (tmp2 != NULL)
				tmp2->next = tmp;
			else
				front = tmp;
			tmp->next = nowPos;
			flag = true;
			break;
		}
		tmp2 = nowPos;
		nowPos = nowPos->next;
	}
	if (flag == false)
		this->Push(tmp->info);
	else
		size++;
}

void LQueue::Refresh() {
	if (this->size == 1)
		return;
	QItem* tmp = new QItem(this->GetFirst());
	long long thisSum = tmp->info.sum();
	this->Pop();
	QItem* nowPos = this->front;
	QItem* tmp2 = NULL;
	bool flag = false;
	for (long long i = 0; i < this->GetSize(); i++) {
		if (thisSum < nowPos->info.sum2()) {
			if (tmp2 != NULL)
				tmp2->next = tmp;
			else
				front = tmp;
			tmp->next = nowPos;
			flag = true;
			break;
		}
		tmp2 = nowPos;
		nowPos = nowPos->next;
	}
	if (flag == false)
		this->Push(tmp->info);
	size++;
}
*/
/*void LQueue::ReverseRefresh() {
	if (this->size == 1)
		return;
	QItem* tmp = new QItem(*this->rear);
	long long thisSum = tmp->info.sum();
	//this->Pop();

	if (size == 0)
		return;
	QItem *tmp3 = this->rear;
	
	
	tmp3 = NULL;
	size--;
	if (size == 0)
		rear = NULL;
	return true;


	QItem* nowPos = this->front;
	QItem* tmp2 = NULL;
	bool flag = false;
	for (long long i = 0; i < this->GetSize(); i++) {
		if (thisSum < nowPos->info.sum()) {
			if (tmp2 != NULL)
				tmp2->next = tmp;
			tmp->next = nowPos;
			flag = true;
			break;
		}
		tmp2 = nowPos;
		nowPos = nowPos->next;
	}
	if (flag == false)
		this->Push(tmp->info);
}*/
/*
bool LQueue::Pop() {
	if (size == 0)
		return false;
	QItem *tmp = front;
	front = front->next;
	delete tmp;
	size--;
	if (size == 0)
		rear = NULL;
	return true;
}

infotype LQueue::GetFirst() const {
	if (size == 0)
		throw exception("Impossible to execute GetFirst: queue is empty");
	return front->info;
}

bool LQueue::IsEmpty() const {
	return (size == 0);
}


unsigned LQueue::GetSize() const {
	return size;
}

infotype LQueue::operator [] (unsigned k) const {
	if ((k<0) || (k >= size))
		throw exception("Impossible to execute operator[]: invalid index");
	QItem *tmp = front;
	for (unsigned i = 0; i<k; i++)
		tmp = tmp->next;
	return tmp->info;
}
/*const infotype& LQueue::GetByIndex(unsigned k) const
{
	if ((k<0) || (k >= size))
		throw exception("Impossible to execute operator[]: invalid index");
	QItem *tmp = front;
	for (unsigned i = 0; i<k; i++)
		tmp = tmp->next;
	return tmp->info;
}

infotype& LQueue::operator [] (unsigned k)
{
	return (infotype&)GetByIndex(k);
}
void LQueue::Browse(void ItemWork(infotype)) const {
	QItem *tmp = front;
	for (unsigned i = 0; i<size; i++) {
		ItemWork(tmp->info);
		tmp = tmp->next;
	}
}

void LQueue::Browse(void ItemWork(InfoType&)) {
	QItem *tmp = front;
	for (unsigned i = 0; i<size; i++) {
		ItemWork(tmp->info);
		tmp = tmp->next;
	}
}
*/

/*class mycomparison
{
	bool reverse;
public:
	mycomparison(bool revparam)
	{
		reverse = revparam;
	}
	bool operator() (const infotype& lhs, const infotype& rhs) const
	{
		if (reverse) return (lhs.sum2>rhs.sum2);
		else return (lhs.sum2<rhs.sum2);
	}
};

*/

typedef std::priority_queue<infotype, std::vector<infotype>, mycomparison> mypq_type;

mypq_type q(mycomparison(true));

//priority_queue<infotype> q;

int main() {
	unsigned long long ans = 0;
	long long N;
	long long K;
	ifstream fin("input.txt");
	fin >> N;
	fin >> K;
	for (long long i = 0; i<N; i++) {
		infotype* inf = new infotype();
		fin >> inf->A;
		fin >> inf->B;
		q.push(*inf);
	}
	fin.close();
	while (K>0) {
		infotype* inf = new infotype(q.top());
		long long num = inf->sum2();
		if (num<0)
			K= K;
		ans += num;
		q.pop();
		inf->C+=1;
		q.push(*inf);
		K--;
	}
	ofstream fout("output.txt");
	fout << ans;
	fout.close();
	return 0;
}