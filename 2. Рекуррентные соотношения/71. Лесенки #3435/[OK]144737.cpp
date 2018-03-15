#include <fstream>

using namespace std;
ofstream out("output.txt");

class hugeint
{
private:
	short integer[20];

public:
	hugeint()
	{
		for(int i=0; i<20; i++)
			integer[i]=0;
	}

	hugeint(long val)
	{
		int i, j;
		for(i=0; i<20; i++)
			integer[i]=0;

		for(int i=19; val!=0 && i>=0; i--)
		{
			integer[i]=val%10;
			val/=10;
		}
	}

	hugeint operator+(hugeint &op2)
	{
		hugeint temp;
		int carry=0;
		for(int i=19; i>=0; i--)
		{
			temp.integer[i]=integer[i]+op2.integer[i]+carry;
			if(temp.integer[i]>9)
			{
				temp.integer[i]%=10;
				carry=1;
			}
			else
				carry=0;
		}
		return temp;
	}

	friend ostream& operator<<(ostream &output, hugeint &num)
	{
		int i;
		for(i=0; (num.integer[i]==0) && (i<20); i++)
			;
		if(i==20)
			output<<0;
		else
			for(; i<20; i++)
				output<<num.integer[i];
		return output;
	}
};

int floor(int n)
{
	int a=0, b=0;
	while(a<=n)
	{
		a=a+b;
		b++;
	}
	return b-2;
}

int main()
{
	ifstream in;
	in.open("input.txt");

	int n;
	in>>n;
	in.close();

	hugeint c;

	if(n<0)
		return 0;
	if(n==0)
	{
		out<<0<<endl;
		return 0;
	}
	if(n<=2)
	{
		out<<1<<endl;
		return 0;
	}

	int fl=floor(n);

	hugeint **m=new hugeint*[fl];
	for(int i=0; i<fl; i++)
		m[i]=new hugeint[n];

	for(int i=0; i<n; i++)
	{
		m[0][i]=1;
		m[1][i]=i/2;
	}

	int k=1;
	for(int i=2; i<fl; i++)
		for(int j=0; j<n; j++)
		{
			while(j-(i+1)*k>=0)
			{
				m[i][j]=m[i][j]+m[i-1][j-(i+1)*k];
				k++;
			}
			k=1;
		}

	for(int i=0; i<fl; i++)
		c=c+m[i][n-1];

	out<<c<<endl;

	return 0;
}