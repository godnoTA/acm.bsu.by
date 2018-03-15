#include <map>  
#include <iostream> 
#include <fstream>

using namespace std;  

int main( )  
{  
	long long number_of_shops;
	long long number_of_goods;
    ifstream fin("input.txt");

    fin >> number_of_shops; 
	fin >> number_of_goods;

	multimap <long long, long long> m1;
	typedef pair <long long, long long> Int_Pair;  
  
    multimap <long long, long long> :: iterator m1_Iter;  

	for(int i = 0; i<number_of_shops;i++){
		long long price;
		long long additional_price;
		fin>>price;
		fin>>additional_price;
		m1.insert(Int_Pair(price, additional_price));
	}
    fin.close(); 

	long long result = 0;
	long long new_price = 0;
	long long new_additional_price = 0;

	for(long long i = 0; i< number_of_goods;i++){
		m1_Iter = m1.begin ( );
		result+=m1_Iter -> first;
		new_price = (m1_Iter -> first) + m1_Iter -> second;
		new_additional_price = m1_Iter -> second;
		
        m1.erase ( m1_Iter ); 
		m1.insert(Int_Pair(new_price, new_additional_price));
	}

	cout << result;	


	ofstream fout("output.txt"); 
    fout << result;
    fout.close(); 
}  
