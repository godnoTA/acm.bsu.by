#include <iostream>
#include <fstream>
#include <list> 
#include <iterator>

using namespace std;
 
int main()
{
	int N, M;
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL )
        return 1;
	input >> N >> M;
	if((N<1)||(N>100000)||(M<0)||(M>200000))
		return 1;
	list <int> *lister = new list<int>[N];
	if(M!=0)
	{
		while(!input.eof())
		{
			int u,v;
			input >> u >> v;  
			if((u==v)||(u<1)||(u>N)||(v<1)||(v>N))
				return 1;
			if((!(lister[u-1].empty()))&&(!(lister[v-1].empty())))
				if(((lister[u-1].front())==v)&&((lister[v-1].front())==u))
					break;
			lister[u-1].push_front(v);
			lister[v-1].push_front(u);
		}
	}
	for(int i=0; i<N; i++)
	{
		output << lister[i].size();
		for (list<int>::iterator it=lister[i].begin(); it != lister[i].end(); it++)
			output << " " << *it;
		output << "\n";
	}
    input.close();
	output.close();

    return 0;
}