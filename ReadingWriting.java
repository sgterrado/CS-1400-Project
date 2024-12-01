import java.io.*;
import java.util.Scanner;

//Handles File Management
class ReadingWriting {
    private final String TEXT_FILE = "C:\\Users\\lalat\\IdeaProjects\\untitled\\src\\main\\resources\\wordFile.txt";
    private final int numberQuestions = Main.NUM_QUESTIONS; //Get number of questions
    private int[][] mainArray; //Equivalent of EntryList, with each row as an Entry of int questions
    private int mainArrayLength;
    private boolean haveGAD; //Ask questions about anxiety (Generalized Anxiety Disorders)?
    private boolean haveDep; //As questions about depression?

    //Constructor
    public ReadingWriting() {
        this.mainArray = new int[100][numberQuestions + 1]; //Oversized array
        this.mainArrayLength = 0; //Oversized array index
        haveGAD = false; haveDep = false;
    }

    //Angela: Reads array from file
    public EntryList readF(UI ui){
        //open file
        FileInputStream fileByteStream = null;
        try {fileByteStream = new FileInputStream(TEXT_FILE);}
        catch (FileNotFoundException e) {e.printStackTrace();}

        Scanner inScnr = new Scanner(fileByteStream); //add try/catch

        //first line is haveGAD, haveDep, num of lines following
        if(!inScnr.hasNext()) {
            int personalization = ui.setUp();
            if (personalization == 1 || personalization == 3) {
                haveGAD = true;
            }
            if (personalization == 2 || personalization == 3) {
                haveDep = true;
            }
        }else{
            this.haveGAD = inScnr.nextInt() == 1; //add try/catch
            this.haveDep = inScnr.nextInt() == 1; //add try/catch
            this.mainArrayLength = inScnr.nextInt(); //add try/catch

            //Add data to array
            int fileLineIndex = 0;
            int[] temp;
            while (inScnr.hasNext()) {
                temp = new int[numberQuestions + 1]; //+1 because first one is date
                for (int i = 0; i < numberQuestions + 1; i++) {
                    try {
                        temp[i] = inScnr.nextInt();
                    } catch (Exception e) {
                        break;
                    }
                }
                this.mainArray[fileLineIndex] = temp;
                fileLineIndex++;
            }

            //this.mainArrayLength = fileLineIndex;
        }

        try {
            fileByteStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EntryList e = new EntryList(this.numberQuestions);
        for (int i = 0; i < this.mainArrayLength; i++) {
            e.append(mainArray[i]);
        }
        return e;
    }

    //Angela: Prints array to file
    public int printF(EntryList el){
        mainArray = el.getIntInt();
        mainArrayLength = el.getMainArrayLength();

        PrintWriter outFS = null;
        try{
            FileOutputStream fileStream = new FileOutputStream(TEXT_FILE);
            outFS = new PrintWriter(fileStream);

            if(haveGAD){outFS.print("1 ");}else{outFS.print("0 ");}
            if(haveDep){outFS.print("1 ");}else{outFS.print("0 ");}
            outFS.println(mainArrayLength);

            for(int i = 0; i < mainArrayLength; i++){
                for(int j : mainArray[i]){
                  outFS.print(j + " ");
                }
                outFS.println();
            }

            return 0;
        }
        catch(Exception e){return -1;}
        finally{
            outFS.close();
        }

    }

    //Testing
    public void testPrint(){
        for(int i = 0; i < this.mainArrayLength; i++){
            for(int j = 0; j < numberQuestions + 1; j++){
                System.out.print(mainArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Get vars
    public boolean getGAD(){return haveGAD;}
    public boolean getDep(){return haveDep;}
}
