import java.io.*;
import java.util.*;


public class Test {
	static double current_max = 0;
	static int size_of_array = 0;
	static List <Double> my_centres = new ArrayList<Double>();
	static List <Double> my_radiuses = new ArrayList<Double>();
	
	public static double find_max(double []mas){
		double max = 0;
		for (int i = 0; i< mas.length;i++){
			if(mas[i]>max){
				max = mas[i];
			}
		}
		return max;
	}
	public static void add_new_center(double radius){
		double[] temp_mas = new double[my_centres.size()];
		for(int i = 0;i<my_centres.size(); i++){
			temp_mas[i] = my_centres.get(i) +2*Math.sqrt(my_radiuses.get(i)*radius);
		}
		my_centres.add(find_max(temp_mas));
	}
	
	
	public static void main(String[] args) throws IOException {

		
		try{
			File file = new File("in.txt");
				
	        Scanner in = new Scanner(file);
	        size_of_array = in.nextInt();
	        my_radiuses = new ArrayList<Double>(size_of_array);
	        while(in.hasNext()){
	        	my_radiuses.add(in.nextDouble());
	        }
	        in.close();
		}
		
		catch(Exception e){e.printStackTrace();}
		my_centres.add((double) 0);
        for(int i = 1; i<size_of_array;i++){
        	add_new_center(my_radiuses.get(i));
        }
        
        
        double[] LDi = new double[size_of_array];
        double[] RDi = new double[size_of_array];
        double max_rd = 0;
        double min_ld = 0;
        for(int i = 0; i<size_of_array; i++){
        	LDi[i] = my_centres.get(i)-my_radiuses.get(i);
        	RDi[i] =my_centres.get(i)+my_radiuses.get(i);
        	if(RDi[i]>max_rd){
        		max_rd =RDi[i];
        	}
        	if(LDi[i]<min_ld){
        		min_ld =LDi[i];
        	}
        }    
        try{
        	PrintWriter output = new PrintWriter(new FileWriter("out.txt"));
        	output.format("%.5f%n", (max_rd-min_ld));
        	output.close();
        }
        catch(Exception e){}
	}
		

	
	
}
