import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class mainProbirks {
	
static Integer[] p;
static int l = 0;
static int [][] allEvents;
static special bufferedQueueSt;
static int needInThird;

public static class event {
	int first;
	int second;
	int steps;
	event nextEvent;
}

public static class special {
	event beg;
	event end;
	public special() {
		beg = end = null;
	}
	boolean isEmpty() {
		if(beg == null) return true;
		else return false;
	}
	void push(event ev) {
		if(beg == null) {
			beg = new event();
			beg.nextEvent = end;
			beg.first = ev.first;
			beg.second = ev.second;
			beg.steps = ev.steps;
			end = beg; 
	     }
		 else {
			end.nextEvent = new event();
			end.nextEvent.first = ev.first;
			end.nextEvent.second = ev.second;
			end.nextEvent.steps = ev.steps;
			end.nextEvent.nextEvent = null;
			end = end.nextEvent;
		 }
	}
	event pop() {
		event ev = new event();
		ev.first = beg.first;
		ev.second = beg.second;
		ev.steps = beg.steps;
		beg = beg.nextEvent;
		return ev;
	}
}

static void findSolution() {
   
   again: while (!bufferedQueueSt.isEmpty()) {
	   int inThird;
	   event originalE, bufferedE;
	   originalE = bufferedQueueSt.pop();
	   bufferedE = new event();
	   if(originalE.first + originalE.second + needInThird == 100) {System.out.println(allEvents[originalE.first][originalE.second]);}
	   
	   if(allEvents[originalE.first][originalE.second] > originalE.steps) allEvents[originalE.first][originalE.second] = originalE.steps;
	   else { continue again; } 
	   inThird = 100 - originalE.first - originalE.second;

	   
	   for(int i = 0; i < l; i++) {
		   if(p[i] <= originalE.first) {//выливаем из 1 до поинта (поинт 1) во 2
			   bufferedE.first = p[i];
			   bufferedE.second = originalE.second + originalE.first - p[i];
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   for(int i = 0; i < l; i++) {//выливаем из 1 во 2 до поинта (поинт 2)
		   if(((p[i] - originalE.second) <= originalE.first) && (originalE.second < p[i])) {
			   bufferedE.first = originalE.first - (p[i]-originalE.second);
			   bufferedE.second = p[i];
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   for(int i = 0; i < l; i++) { //в 3 выливаем до поинта из 1 (поинт 1)
		   if(p[i] <= originalE.first) {
			   bufferedE.first = p[i];
			   bufferedE.second = originalE.second;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   for(int i = 0; i < l; i++) { //выливем из 2 в 1 до поинта(поинт 2)
		   if(p[i] <= originalE.second) { 
			   bufferedE.second = p[i];
			   bufferedE.first = originalE.first + originalE.second - p[i];
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   for(int i = 0; i < l; i++) { //выливаем в 1 из 2 до поинта (поинт 1)
		   if(((p[i] - originalE.first) <= originalE.second) && (originalE.first < p[i])) {
			   bufferedE.first = p[i];
			   bufferedE.second = originalE.second  - (p[i] - originalE.first) ;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   for(int i = 0; i < l; i++) {//выливаем из 2 в 3 до поинта
		   if(p[i] <= originalE.second) {
			   bufferedE.second = p[i];
			   bufferedE.first = originalE.first;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   
//выливаем из 2 в 3 все

			   bufferedE.second = 0;
			   bufferedE.first = originalE.first;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);

//выливаем из 1 в 3 все

			   bufferedE.second = originalE.second;
			   bufferedE.first = 0;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);

	   
	   for(int i = 0; i < l; i++) { //выливаем в 1 из 3 до поинта
		   if(((p[i]-originalE.first) <= inThird) && (p[i] > originalE.first)) {
			   bufferedE.first = p[i];
			   bufferedE.second = originalE.second;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   bufferedE.second = originalE.second; //все из 3 в 1
	   bufferedE.first = originalE.first + inThird;
	   bufferedE.steps = originalE.steps + 1;
	   bufferedQueueSt.push(bufferedE);
	   
	   for(int i = 0; i < l; i++) { //выливаем в 2 из 3 до поинта
		   if(((p[i]-originalE.second) <= inThird) && (p[i] > originalE.second)) {
			   bufferedE.second = p[i];
			   bufferedE.first = originalE.first;
			   bufferedE.steps = originalE.steps + 1;
			   bufferedQueueSt.push(bufferedE);
		   }
	   }
	   bufferedE.second = originalE.second + inThird;//все из 3 во 2
	   bufferedE.first = originalE.first;
	   bufferedE.steps = originalE.steps + 1;
	   bufferedQueueSt.push(bufferedE);
   }
}

public static void main(String[] args) throws IOException {
	Scanner SC = new Scanner(new File("in.txt"));
    if(!SC.hasNext()) { SC.close(); return; }    
    allEvents = new int[101][101];
    for (int i=0; i<=100; i++)
		for (int j=0; j<=100; j++)
			allEvents[i][j]=10000;
    needInThird = SC.nextInt();  
    int a = SC.nextInt();
    int b = SC.nextInt();
    event eventMine = new event();
    eventMine.first = a;
    eventMine.second = b;
    eventMine.steps = 0;
    bufferedQueueSt = new special();
    bufferedQueueSt.push(eventMine);
    p = new Integer[100];
   	p[0] = 100;
   	 l = 0;
	   while(p[l] != 0) {
		   l++;
		   p[l] = SC.nextInt();
	   }
	   l++;
    try {
    	PrintWriter pw = new PrintWriter(new File("out.txt"));
    	findSolution();
    	int sol = 10000;
    	for(int i = 0; i <= 100 - needInThird; i++) {
    		if(allEvents[i][(100 - needInThird) - i] < sol) { sol = allEvents[i][(100 - needInThird) - i];}
    	}
    	if(sol != 10000) {
    		pw.print(sol);
    	}
    	else pw.print("No solution");
    	pw.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    SC.close();
}

}