import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Tubes {

    static class Configuration{
        private int firstTube;
        private int secondTube;
        private int numbOfTransfusions;

        public Configuration(int first,int second,int numb){
            firstTube = first;
            secondTube = second;
            numbOfTransfusions = numb;
        }

        public Configuration(Configuration config){
            firstTube = config.firstTube;
            secondTube = config.secondTube;
            numbOfTransfusions = config.numbOfTransfusions;
        }

    }

    private static LinkedList<Configuration> listOfTubes = new LinkedList<>();
    private static int matrix[][] = new int[101][101];
    private static ArrayList<Integer> marks = new ArrayList<>();

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        marks.add(0);
        int startFirst = 0;
        int startSecond = 0;
        int needWater  = 0;
        int numb = 0 ;
        String negativeAnswer = "No solution";

        long timeSpent = System.currentTimeMillis() - startTime;
        // ****************************** READ FILE **************************
        try {
            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("in.txt")), "UTF-8"));
            String strData;
            while ((strData = readData.readLine()) != null) {
                needWater = Integer.parseInt(strData);
                strData = readData.readLine().trim();
                startFirst = Integer.parseInt(strData);
                strData = readData.readLine().trim();
                startSecond = Integer.parseInt(strData);
                strData = readData.readLine().trim();
                StringTokenizer stringTokenizer = new StringTokenizer(strData," ",false);
                while (stringTokenizer.hasMoreTokens()){
                    String tok = stringTokenizer.nextToken();
                    if (!tok.equals("0")){
                        marks.add(Integer.parseInt(tok));
                    }
                }
            }
            marks.add(100);
        //----------------------------------------------------------------------

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101 ; j++) {
                    matrix[i][j] = 5000;
                }
            }

            final int index = marks.size();

            Configuration startConfig = new Configuration(startFirst,startSecond,0);
            listOfTubes.add(startConfig);

            while (!listOfTubes.isEmpty()){
                Configuration workingConfig, noNameConfig;

                workingConfig = new Configuration(listOfTubes.pollFirst());

                int sup = 0;

                if (matrix[workingConfig.firstTube][workingConfig.secondTube] > workingConfig.numbOfTransfusions)
                    matrix[workingConfig.firstTube][workingConfig.secondTube] = workingConfig.numbOfTransfusions;
                else continue;

                sup = 100 - workingConfig.firstTube - workingConfig.secondTube;

                //1. 1->2 по делениям 1
                for (Integer mark : marks){
                    if ( mark <= workingConfig.firstTube ){
                        noNameConfig = new Configuration(mark,workingConfig.secondTube + workingConfig.firstTube - mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //2. 1->2 по делениям 2
                for (Integer mark : marks){
                    if ( ( (mark - workingConfig.secondTube) <= workingConfig.firstTube ) && (workingConfig.secondTube < mark) ){
                        noNameConfig = new Configuration(workingConfig.firstTube + workingConfig.secondTube - mark,mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //3. 1->3
                for (Integer mark : marks){
                    if ( mark <= workingConfig.firstTube ){
                        noNameConfig = new Configuration(mark,workingConfig.secondTube,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //4. 2->1 по делениям во второй
                for (Integer mark : marks){
                    if ( mark <= workingConfig.secondTube ){
                        noNameConfig = new Configuration(workingConfig.firstTube + workingConfig.secondTube - mark,mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //5. 2->1 по делениям в первой
                for (Integer mark : marks){
                    if ( ((mark - workingConfig.firstTube) <= workingConfig.secondTube) && (workingConfig.firstTube < mark) ){
                        noNameConfig = new Configuration(mark,workingConfig.firstTube + workingConfig.secondTube - mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //6. 2->3
                for (Integer mark : marks){
                    if ( mark <= workingConfig.secondTube ){
                        noNameConfig = new Configuration(workingConfig.firstTube,mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                //7. 3->1
                for (Integer mark : marks){
                    if ( ( (mark - workingConfig.firstTube) <= sup) && (mark > workingConfig.firstTube) ){
                        noNameConfig = new Configuration(mark,workingConfig.secondTube,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                noNameConfig = new Configuration(workingConfig.firstTube+sup,workingConfig.secondTube,workingConfig.numbOfTransfusions + 1);
                listOfTubes.push(noNameConfig);

                //8. 3->2
                for (Integer mark : marks){
                    if ( ( (mark - workingConfig.secondTube) <= sup) && (mark > workingConfig.secondTube) ){
                        noNameConfig = new Configuration(workingConfig.firstTube,mark,workingConfig.numbOfTransfusions + 1);
                        listOfTubes.addLast(noNameConfig);
                    }
                }

                noNameConfig = new Configuration(workingConfig.firstTube,workingConfig.secondTube+sup,workingConfig.numbOfTransfusions + 1);
                listOfTubes.push(noNameConfig);
            }


            //************************* WRITE FILE *********************************
            FileWriter writer = new FileWriter("out.txt", false);
            int answer = 5000;

            for (int i = 0; i <= 100 - needWater; i++)
            {
                if (answer > matrix[i][100 - needWater - i])
                {
                    answer = matrix[i][100 - needWater - i];
                }
            }
            if (answer == 5000) {
                writer.write(negativeAnswer);
            }
            else {
                writer.write(String.valueOf(answer));
            }
            writer.flush();
        //----------------------------------------------------------------------

        }catch (IOException ex){
            ex.getStackTrace();
        }

        System.out.println("ALL program  -  " + timeSpent + "  - milliseconds ");
    }
}
