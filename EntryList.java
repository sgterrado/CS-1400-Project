public class EntryList {
    private Entry[] mainArray; //oversized
    private int mainArrayLength;
    private int numQuestions;

    //Constructors
    public EntryList(){}
    public EntryList(int n){
        this.mainArrayLength = 0;
        this.mainArray = new Entry[1000]; //try to oversize
        this.numQuestions = n;
    }

    //Add new entry to main Array
    public int append(Entry e){
        if(this.mainArrayLength < this.mainArray.length) {
            this.mainArray[this.mainArrayLength] = e;
            this.mainArrayLength += 1;
            return 0;
        }
        else{return -1;}
    }
    public int append(int[] a){
        if(this.mainArrayLength < mainArray.length) {
            Entry y = new Entry(a);
            this.mainArray[this.mainArrayLength] = y;
            mainArrayLength += 1;
            return 0;
        }
        else{return -1;}
    }

    //Testing
    public void testingPrint(){
        System.out.println("length: " + this.mainArrayLength);
        for(int i = 0; i < this.mainArrayLength; i++){
            System.out.print("row " + i + ": ");
            System.out.print(this.mainArray[i].print());
        }
    }

    //Shawn: Pulls Average Data from past 2 weeks
    public double[] avgData(int maxDate){
        int[][] data = getIntInt();
        double gadTotal = 0.0; double depTotal = 0.0;
        int minDate = maxDate - 14; //to get the 2-week range
        if(minDate%1000 > 365){ //if minDate is between YY985 - YY999
            minDate -= 634; //YY985 --> YY351 YY999 --> YY365
        }
        int numDays = 0; //to calculate results
        try{
            // loop iterates through entire result table, starting from the selected date
            for(int[] ints : data){
                if(minDate < ints[0] && ints[0] <= maxDate) {
                    for (int j = 2; j < Main.GAD_QUESTIONS + 2; j++) { //skip date and mood
                        gadTotal += ints[j]; // the answers to each question in the row are tallied
                    }
                    for (int j = Main.GAD_QUESTIONS + 2; j < Main.NUM_QUESTIONS; j++) { //skip date and mood
                        depTotal += ints[j]; // the answers to each question in the row are tallied
                    }
                    numDays++;
                }
            }
            double multiplier = 1.0 / numDays;
            if(numDays > 2){multiplier *= 3;}
            double[] ret = {gadTotal * multiplier, depTotal * multiplier};
            return ret;
        }

        /* if the date selected doesn't have two weeks of data preceding it,
         *  an exception is hit and the method returns 0
         */
        catch(Exception e){
            System.out.println("Selected date does not have 2 weeks of data behind it");
            double[] ret = {0.0, 0.0};
            return ret;
        }
    }

    //get vars
    public Entry[] getMainArray(){ return this.mainArray; }
    public int getMainArrayLength(){
        return this.mainArrayLength;
    }
    public int[][] getIntInt(){ //returns PERFECT SIZED int[][]
        int[][] ii = new int[this.mainArrayLength][numQuestions +1];
        for(int i = 0; i < this.mainArrayLength; i++) {
            ii[i] = this.mainArray[i].getArray();
        }
        return ii;
    }
}
