#include <list>
#include <bits.h>
#include <iostream>
#include <fstream>
#include<vector>

using namespace std;

//vector<int> vectOr[n];   

ofstream out("output.txt");
ifstream in("input.txt");

int main()
{

	if (in.is_open())
	{
		int n = 0;
		int m = 0;
		in >> n>>m;
		list<int>* List = new list<int>[n];

		for (int i = 0; i < m; i++){
			int d;
			int f;
			in >> d>>f;
			List[d - 1].push_front(f);
			List[f - 1].push_front(d);
		}

		for (int i = 0; i < n; i++){
			out << List[i].size();
			for (int j : List[i])
				out << " " << j;
			out << endl;
		}
	}
	else
		cout << "file is not opened!!!"<<endl;
	in.close();
	out.close();
	return 0;
}

















//#include <iostream>
//#include <fstream>
//#include <math.h>
//#include <vector>
//
//using namespace std;
//ifstream in("input.txt");
//ofstream out("output.txt");
//
//
//int main() {
//	if (in.is_open())
//	{
//		int n;
//		bool b = true;
//		in >> n;
//		int * A = new int[n];
//		for (int i = 0; i < n; i++)
//			in >> A[i];
//		for (int i = 0; i <= n / 2; i++) {
//			if (2 * (i + 1)-1 > n-1)
//				break;
//			if ((2 * (i + 1) > n-1)){
//				if((A[2 * i + 1] >= A[i])) {continue;}
//				else if((A[2 * i + 1] < A[i])) {
//					b = false;
//					break;
//				}
//			}
//			if ((A[2 * (i + 1)-1] >= A[i])&&(A[2 * (i + 1)] >= A[i])){continue;}
//			else {
//				b = false;
//				break;
//			}
//		}
//		out << ((b)? "Yes" : "no")<<endl;
//	}
//	else
//		cout << "file is not opened!!!"<<endl;
//	in.close();
//	out.close();
//	return 0;
//}