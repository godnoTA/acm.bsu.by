using System.Globalization;
using System.IO;
using System.Linq;

namespace Alg_lab2
{
	class Program
	{
		static bool Check(int n, double[,] a)
		{
			for (int it = 0; it < 10; it++)
			{
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						for (int k = 1; k < n; k++)
						{
							if (a[i, j] < a[i, k] * a[k, j] * a[j, i])
							{
								a[i, j] = a[i, k] * a[k, j] * a[j, i];
							}
						}
					}
				}
			}
			for(int i = 0; i < n; i++)
			{
				if(a[i,i] > 1)
				{
					return true;
				}
			}
			return false;
		}
		static void Main(string[] args)
		{
			int n;
			double[,] a;
			using (StreamReader sr = new StreamReader("in.txt"))
			{
				string str = sr.ReadLine();
				n = int.Parse(str);
				a = new double[n, n];
				for (int i = 0; i < n; i++)
				{
					str = sr.ReadLine();
					CultureInfo ci = (CultureInfo)CultureInfo.CurrentCulture.Clone();
					ci.NumberFormat.CurrencyDecimalSeparator = ".";
					var line = str.Split(' ').Select(x => double.Parse(x, NumberStyles.Any, ci)).ToArray();
					for (int j = 0; j < n; j++)
					{
						a[i, j] = line[j];
					}
				}
			}
			string result = "no";
			if (Check(n, a))
			{
				result = "yes";
			}
			using (StreamWriter sw = new StreamWriter("out.txt"))
			{
				sw.WriteLine(result);
			}
		}
	}
}
