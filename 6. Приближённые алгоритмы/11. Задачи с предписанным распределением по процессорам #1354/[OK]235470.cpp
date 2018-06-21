#pragma comment (linker, "/STACK:64000000")
#include<iostream>
#include<fstream>
#include<vector>
#include<functional>
#include<algorithm>
#include<queue>

std::ifstream fin("input.txt");
std::ofstream fout("output.txt");
long long n, m;


class Programme {
private: int progNum;
		 int processorNum;
		 long long downloadTime;
		 long long workTime;
public:
	int getprogNum() { return progNum; }
	int getprocessorNum() { return processorNum; }
	long long getdownloadTime() { return downloadTime; }
	long long getworkTime() { return workTime; }

	void setdownloadTime(long long downloadT) { downloadTime = downloadT; }
	void setworkTime(long long workT) { workTime = workT; }
	Programme(int progN, long long downloadT, long long workT, int processorN) {
		progNum = progN;
		downloadTime = downloadT;
		workTime = workT;
		processorNum = processorN;
	}
	Programme() {}

};


bool compProg(Programme  p1, Programme p2) {
	if (p1.getworkTime() != p2.getworkTime())
		return p1.getworkTime() > p2.getworkTime();
	else
		return p1.getdownloadTime() > p2.getdownloadTime();
}


int main() {
	fin >> n >> m;
	std::vector<long long> processorsTime(m + 1, 0);  // до m+1, чтобы не менять нумерацию процессоров
	long long minus = 0;

	std::vector<Programme> progVector(n);
	for (int i = 0; i < n; i++) {
		long long x, y, z;
		fin >> x >> y >> z;
		Programme p(i + 1, x, y, z);
		progVector[i] = p;
	}
	sort(progVector.begin(), progVector.end(), compProg);   //сортирую все программы (в таком порядке они и будут выполняться)
	long long needTime = 0;
	needTime = needTime + progVector[0].getdownloadTime();  //скачиваю первую программу
	processorsTime[progVector[0].getprocessorNum()] = progVector[0].getworkTime();  //заношу значение выолнения программы в соответствующий процессор

	for (int i = 1; i < n; i++) {
		if (processorsTime[progVector[i].getprocessorNum()] - minus <= 0) {   //если нужный  процессор свободен
			needTime = needTime + progVector[i].getdownloadTime();    //скачиваем нужную задачу с сервера
			minus += progVector[i].getdownloadTime();
			processorsTime[progVector[i].getprocessorNum()] = progVector[i].getworkTime() + minus;  //помещаем время выполнения в сам процессор
		}
		else {

			needTime += processorsTime[progVector[i].getprocessorNum()] - minus; //прибавляем время предыдущей еще незаконченной задачи, которая работает на процессоре, который нужен сейчас
			needTime += progVector[i].getdownloadTime();   //скачиваем новую задачу, которая будет обработана на этом процессоре
			minus += processorsTime[progVector[i].getprocessorNum()] + progVector[i].getdownloadTime() - minus;
			processorsTime[progVector[i].getprocessorNum()] = progVector[i].getworkTime() + minus;   //записываем в процессор новое время работы скаченной задачи

		}
	}


	//по сути needTime сейчас хранит время скачиваний и время простоев
	sort(processorsTime.begin(), processorsTime.end(), std::greater<long long>());
	needTime += *processorsTime.begin() - minus;            //берем максимальное время с процессоров (то есть все остальные задачи тоже успеют завершиться, пока завершится самая долгая)
	fout << needTime << "\n";
	for (int i = 0; i < n; i++) {
		fout << progVector[i].getprogNum() << " ";
	}

	return 0;

}