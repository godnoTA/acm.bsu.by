#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

void main()
{
	FILE * in;
	FILE * out;
	in = fopen("input.txt","r");
	int n,m,s,i,j;
	int sum1(0),sum2(0);
	bool flag (false);
	fscanf(in,"%d%d%d",&n,&m,&s);
	int *masn = new int[n];
	int *masm = new int[m];
	for (i=0;i<n;++i) 
	{
		fscanf(in,"%d",&masn[i]);
	}
	for (i=0;i<m;++i)
	{
		fscanf(in,"%d",&masm[i]);
	}

	for (i=0;i<n;i++) 
	{
		sum1 = sum1 + masn[i];
	}

		out = fopen("output.txt","w");

		if (s>sum1)
	{
		fprintf(out,"No");
		fclose(in);
		fclose(out);
		return ;
	}
		if (s==sum1)
	{
		fprintf(out,"Yes");
		fclose(in);
		fclose(out);
		return;
	}

	int raz = sum1 - s;
	int* mix = new int [raz+1];
	for (int i=0;i<raz+1;i++)
	{
		mix[i]=0;
	}
	mix[0]=1;
	for (i=0;(i<n&&!flag); i++) 
		for (int j=raz-1;j>=0;j--){
			if (mix[j]==1) {
				if (j+masn[i]==raz) {
					flag = true;
					fprintf(out,"Yes");
					fclose(in);
					fclose(out);
					return;
				}
				else if (j+masn[i]<raz) {
						mix[j+masn[i]]=1;
					};
			};
		};

	if (flag) return;

	for (i=0;(i<m&&!flag); i++) 
		for (int j=raz-1;j>=0;j--){
			if (mix[j]==1) {
				if (j+masm[i]==raz) {
					flag=true;
					fprintf(out,"Yes");
					fclose(in);
					fclose(out);
				}
				else if (j+masm[i]<raz) {
						mix[j+masm[i]]=1;
					};
			};
		};

	if (!flag)	
	{
		fprintf(out,"No");
		fclose(in);
		fclose(out);
		return ;
	}
}

