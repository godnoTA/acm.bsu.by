#include <fstream>
#include <iostream>
#include <vector>
#include <map>
#include <string>

using namespace std;

string s;
vector<int> vec;

int main()
{
    ofstream cout("output.txt");
    ifstream cin("input.txt");
    cin>>s;
    for(int i=0; i<s.size(); i++)
    {
        if(s[i] == '(')
            vec.push_back(')');
        else if(s[i] == '[')
            vec.push_back(']');
        else if(s[i] == '{')
            vec.push_back('}');
        else if(vec.empty() || s[i] != vec.back())
        {
            cout<<"NO"<<endl<<i<<endl;
            return 0;
        }
        else
            vec.pop_back();

    }
    if(vec.empty())
        cout<<"YES"<<endl;
    else
        cout<<"NO"<<endl<<s.size()<<endl;
}
