#include <iostream> 
#include <fstream> 
#include <vector> 
#include <alg.h>
#include <stack>


using namespace std;



struct root {
	bool inPath = false;
	bool full = false;
	int ans = 0;
};
vector<root>* mainInf;



int firstPosOfFalse = 0;

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
	int temp;

	stack<vector<vector<int>>::iterator> path;
	
	vector<vector<int>>::iterator tempIt;
	vector<int>::iterator tempBeg;
	bool flag = false;

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
	fout.close();

	return 0;
}

/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class MyWay {

	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("in.txt"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt")));
		
		int count=in.nextInt();
		int str_1=in.nextInt()-1;
		int stb_1=in.nextInt()-1;
		int str_2=in.nextInt()-1;
		int stb_2=in.nextInt()-1;
		int matr[][]=new int [count][count];
		int [][]matr_res=new int [count][count];
		
		for(int i=0;i<count;i++){
			for(int j=0;j<count;j++){
				matr[i][j]=in.nextInt();
				matr_res[i][j]=0;
			}
		}
		int a=str_1,b=stb_1;
		 ArrayDeque<Integer> arr_1 = new ArrayDeque<Integer>();
		 ArrayDeque<Integer> arr_2 = new ArrayDeque<Integer>();
		 
		while(true){
			matr_res[a][b]=matr[a][b];
			if(a==0 && b==0){
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.push(a+1);
					arr_2.push(b);
				}
					
				if (matr[a][b]> matr[a][b+1] && matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
			}
			if(a==0 && b!=0 && b!=count-1){
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.add(a+1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b+1] && matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
				if (matr[a][b]> matr[a][b-1] && matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
			}
			
			if(a==count-1 && b==count-1){
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b-1]&& matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
			}
			
			if(a==count-1 && b!=count-1 && b!=0){
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b-1]&& matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
				if (matr[a][b]> matr[a][b+1]&& matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
			}
			
			if(a==0 && b==count-1){
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.add(a+1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b-1] && matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
			}
			
			if(a!=0 && a!=count-1 && b==count-1){
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.add(a+1);
					arr_2.add(b);
				}
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b-1] && matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
			}
			
			if(a==count-1 && b==0){
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b+1] && matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
			}	
			
			if(a!=count-1 && a!=0 && b==0){
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.add(a+1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b+1] && matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
			}	
			
			if(a!=0 && b!=0 && a!=count-1 && b!=count-1){
				if(matr[a][b]> matr[a-1][b] && matr_res[a-1][b]==0){
					arr_1.add(a-1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b+1] && matr_res[a][b+1]==0){
					arr_1.add(a);
					arr_2.add(b+1);
				}
				if(matr[a][b]> matr[a+1][b] && matr_res[a+1][b]==0){
					arr_1.add(a+1);
					arr_2.add(b);
				}
					
				if (matr[a][b]> matr[a][b-1] && matr_res[a][b-1]==0){
					arr_1.add(a);
					arr_2.add(b-1);
				}
			}
			
			if(arr_1.isEmpty())
				break;
			else{			
			a=arr_1.pop();
			b=arr_2.pop();
			}
		}
		
		
		for(int i=0;i<count-1;i++){
			for(int j=0;j<count-1;j++){
				out.write(matr_res[i][j]+" ");
			}
			out.write(matr_res[i][count-1]+"\n");
		}
		
		for(int i=0;i<count-1;i++)
			out.write(matr_res[count-1][i]+" ");
		out.write(matr_res[count-1][count-1]+"");
		out.flush();

	}

}
*/