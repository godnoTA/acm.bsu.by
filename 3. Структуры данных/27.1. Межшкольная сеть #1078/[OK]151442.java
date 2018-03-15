import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Run {
    int [][] source_data;
    int[][] paths;
    boolean[] visited;
    int[] outputs, inputs;
    int dim;
    Run() throws FileNotFoundException {
        readFile();
        initialFullfill();
        int max = findMaxIndex(outputs, dim);
        int connections=0;
        int components=1;
        while(outputs[max]!=dim) {
            //создание единой компоненты связности
            int head=-1;
            for(int i=0; i<dim; i++) {
                if(paths[max][i]==0) {
                   if(head!=-1) {
                       if(outputs[head]<outputs[i]) head=i;
                   } else head = i;
                }
            }
            int tail=-1;
            int last=-1;
            for(int i=0; i<dim; i++) {
                if(paths[max][i]==1) {
                    last=i;
                    if(paths[i][max]==0){
                        if(tail!=-1){
                            if(outputs[i]==1 && paths[head][i]==0) {tail=i;break;}
                            if(inputs[i]>inputs[tail]) tail=i;
                        } else {
                            tail=i;
                            if(outputs[i]==1 && paths[head][i]==0) break;
                        }
                    }
                }
            }
            if(tail==-1) tail=last;
            if(tail==-1) tail=max;
            
            if((tail==-1)||(head==-1)) break;
            connect(tail,head);
            connections++;
            components++;
        }
        while(inputs[max]!=dim) {
            //сшивание хвостов
            int tail=-1;
            for(int i=0; i<dim; i++) {
                if(paths[i][max]==0) {
                    if(tail!=-1) {
                        if(inputs[i]>inputs[tail]) tail=i;
                    } else tail=i;
                }
            }
            if(tail==-1) break;
            connect(tail,max);
            connections++;
        }
        PrintWriter wr = new PrintWriter("output.txt");
        wr.println(components);
        wr.println(connections);
        wr.close();
    }
    public void connect(int first, int second) {
        for(int i=0;i<dim; i++) {
            if((paths[first][i]==0)&&(paths[second][i]==1)) {
                paths[first][i]=1;
                outputs[first]++;
                inputs[i]++;
            }
        }
        for(int i=0;i<dim; i++) {
            if(paths[i][first]==1) {
                for(int k=0;k<dim;k++) {
                    if((paths[i][k]==0)&&(paths[first][k]==1)) {
                        paths[i][k]=1;
                        outputs[i]++;
                        inputs[k]++;
                    }
                }
            }
        }
    }
    public int findMaxIndex(int[] arr, int length) {
        int max=0;
        for(int i=1; i<length; i++) {
            if(arr[i]>arr[max]) max = i;
        }
        return max;
    }
    public void recursiveFunction(int i, int dim) {
        if(visited[i]==true) return;
        else {
            visited[i]=true;
            for(int j=0; j<dim; j++) {
                if(paths[i][j]==1) {
                    recursiveFunction(j,dim);
                    for(int k=0; k<dim; k++) {
                        if(paths[j][k]==1) {
                            if(paths[i][k]==0) {
                                outputs[i]++;
                                inputs[k]++;
                            }
                            paths[i][k]=1;
                        }
                    }
                }
            }
        }
    }
    public void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        dim = sc.nextInt();
        visited = new boolean[dim];
        source_data = new int[dim][dim];
        inputs = new int[dim];
        outputs = new int[dim];
        int row = 0;
        int col = 0;
        while(sc.hasNext()) {
            int tmp = sc.nextInt();
            source_data[row][col] = tmp-1;
            if(tmp==0) {
                row++;
                col=0;
            } else col++;
        }        
    }
    public void initialFullfill() {
        paths = new int[dim][dim];
        for(int i=0; i<dim; i++) {
            for(int j=0; j<dim; j++) {
                if(source_data[i][j] !=-1) {
                    paths[i][source_data[i][j]] = 1;
                    outputs[i]++;
                    inputs[source_data[i][j]]++;
                } else break;
            }
        }
        for(int i=0; i<dim; i++) {
            if(visited[i]==false) recursiveFunction(i,dim);
            if(paths[i][i]==0) {
                paths[i][i]=1;
                outputs[i]++;
            }
        }
    }
    public void show() {
        for(int i=0; i<dim;i++) {
            for(int j=0; j<dim; j++) {
                System.out.print(paths[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");        
        for(int i=0; i<dim; i++) {
            System.out.print(outputs[i] + " ");
        }
        System.out.println("");
        for(int i=0; i<dim; i++) {
            System.out.print(inputs[i] + " ");
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Run run = new Run();
    }    
}