#include <iostream>
#include <fstream>
using namespace std;

int set[100002];
int rang[100002];
int ans;

int Find(int x)
{
    if (set[x] < 0) return x;
    return set[x] = Find(set[x]);
}
void Link(int x, int y)
{
    x = Find(x);
    y = Find(y);
	if(x==y){
		ans++;
	}else{
    if (rang[x] < rang[y]){
		set[x] = y;}
    else
    {
        set[y] = x;
        if (rang[x] == rang[y])
            ++rang[x];
    }
	}
}
void main(){
	int n,p,o,k;
	ifstream in("input.txt");
	ofstream out("output.txt");
	in>>n;
	in>>p;
	memset(set,-1,sizeof(set));
	for(int i=0;i<p;i++){
		in>>o;in>>k;
		Link(o,k);
	}
	out<<ans<<endl;
}