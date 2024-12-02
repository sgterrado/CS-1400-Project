class Main {
    public static final int GAD_QUESTIONS = 7;
    public static final int DEP_QUESTIONS = 8;
    public static final int NUM_QUESTIONS = 1 + GAD_QUESTIONS + DEP_QUESTIONS;

    public static void main(String[] args) {
        UI ui = new UI(); //UI houses the System.in scanner

        //Read from File
        ReadingWriting p = new ReadingWriting();
        EntryList e = p.readF(ui);

        //Ask Questions
        Entry today = ui.takeSurvey(p.getGAD(), p.getDep());
        e.append(today);

        //Write to File
        p.printF(e);

        //Calculate Average
        double[] averages = e.avgData(ui.getDate());

        //Print Average
        System.out.printf("Score average over the last 2 weeks: Anxiety = %.2f, ", averages[0]);
        System.out.printf("Depression = %.2f\n", averages[1]);
        ui.diagnose(averages);

        //Give Suggestions
        System.out.println();
        ui.respond();

    }
}
