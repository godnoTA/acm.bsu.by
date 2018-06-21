using System;
using System.IO;
using System.Collections.Generic;
namespace DFS
{
	class MainClass
	{
		public static int I=1;
		public static int n;

		public static bool[]black; 
		public static List<int> gray; 

		public static void blackout(int [,]matr,int []res,int i)
		{
			if (black[i]) { 
				return;
			}
			gray.Add(i); 
			black[i] = true; 
			res[i]=I++;

			while (gray.Count!=0) {
				i = gray [0];
				for (int j = 0; j < n; j++) {
					if(matr[i,j]==1){
						if (!black [j]) {
							//Console.WriteLine (i+"[]"+j);
							gray.Add (j);
							black [j] = true;
							res [j] = I++;
						}
					}
				}
				gray.RemoveAt (0);
				foreach (int l in gray)
					Console.Write (l + " // ");
				Console.WriteLine ();
			}
		}

		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			n = Convert.ToInt32 (fo.ReadLine());
			int [,]matr=new int[n,n];
			int []result=new int[n];
			black=new bool[n];
			gray = new List<int> (n);
			String[] s;
			for (int i = 0; i < n; i++) {
				s = fo.ReadLine ().Split ();
				for (int j = 0; j < n; j++)
					matr [i, j] = Convert.ToInt32 (s[j]);
			}
			fo.Close ();

			for (int i = 0; i < n; i++) {
					blackout (matr, result, i);
			}
			fo.Close ();

			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			foreach (int u in result){
				//Console.Write (u + " ");
				sw.Write (u+" ");
			}
			sw.Close ();
		}
	}
}
