public class Main {
    public static void main(String[] args) {
        //vars
        final int NUM_QUESTIONS = 10;
        final String TEXT_FILE = "wordFile.txt";
        
        //Read previous responses from file
        //* open file
        FileInputStream fileByteStream = new FileInputStream(TEXT_FILE); 
        //add try/catch
        Scanner inScnr = new Scanner(fileByteStream);  
      
        //* first line is num of lines following
        int mainArrayLength = inScnr.nextInt(); //add try/catch
      
        //Add data to array
        int[][] mainArray = new int[mainArrayLength][NUM_QUESTIONS + 1];
      
        int fileLineIndex = 0; int[] temp;
        while (inScnr.hasNext()) {
           temp = new int[NUM_QUESTIONS + 1]; //+1 because first one is date
           for(int i = 0; i < NUM_QUESTIONS + 1; i++){
             temp[i] = inScnr.nextInt(); //add try/catch
           }
           mainArray[fileLineIndex] = temp;
           fileLineIndex++;
        }
        fileByteStream.close();
    }
}
