import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CheckPlagiarism {
    private static final double THRESHOLD=8.0;

    private ArrayList<String> readFile(File filename) throws IOException {
        ArrayList<String> lines=new ArrayList<>();
        try (BufferedReader reader=new BufferedReader(new FileReader(filename))) {
            String line;
            while((line=reader.readLine())!=null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return lines;
    }

    public void compareFiles(File filename1, File filename2) throws IOException {
        ArrayList<String> file1=readFile(filename1);
        ArrayList<String> file2=readFile(filename2);
        ArrayList<String> startFile;
        ArrayList<String> otherFile;

        //start with file with more lines
        if(file1.size()>file2.size()) {
            startFile=file1;
            otherFile=file2;
        } else {
            startFile=file2;
            otherFile=file1;
        }

        HammingCleared hammingCleared = new HammingCleared();
        ArrayList<Integer> minimalHDs= new ArrayList<>();
        int identicalLines=0;

        //compare any line from first file to all lines from second file -> record minimal HD
        //blank lines - catch exception or return the distance as -1 -> don't include
        for(String line : startFile) {
                    int minimalHD=Integer.MAX_VALUE;
            for(String otherLine : otherFile) {
                //does not include lines which are empty
                if(line.isEmpty() && otherLine.isEmpty()) {
                    continue;
                } else {
                    int currentHD=hammingCleared.compare(line, otherLine);
                    if(currentHD<minimalHD) {
                        minimalHD=currentHD;
                    }
                }
            }
            if(minimalHD!=Integer.MAX_VALUE) {
                minimalHDs.add(minimalHD);
            }
            if(minimalHD==0) {
                identicalLines+=1;
            }
        }

        //calculate average of minimal HD -> if below threshold classify both files as "Plagiarism detected"
        double averageMinimalHD=0.0;
        int sum=0;
        for(int dist : minimalHDs) {
            sum+=dist;
        }
        averageMinimalHD=(double) sum/minimalHDs.size();

        //check for plagiarism
        boolean isPlagiarism=averageMinimalHD<THRESHOLD;

        //report average value of min HD and how many lines are identical in both files
        System.out.println("The average value of minimal Hamming distance is "+averageMinimalHD+", and "+identicalLines+" identical lines were found across both files.");
        //if av=0 -> identical files
        if(averageMinimalHD==0) {
            System.out.println("Both files are identical.");
        }
        //plagiarism
        if(isPlagiarism) {
            System.out.println("Plagiarism detected.");
        } else {
            System.out.println("No plagiarism detected.");
        }
    }

    public static void main(String[] args) throws IOException {
        CheckPlagiarism check=new CheckPlagiarism();

        File folder=new File("antiplagiarism-checker-repo/plagiarism_examples");
        File[] files=folder.listFiles();

        int comparisons=0;
        for(int i = 0; i<Objects.requireNonNull(files).length; i++) {
            for(int j=i+1; j<files.length; j++) {
                if(files[i].isFile() && files[j].isFile()) {
                    System.out.println("Comparing file "+files[i].getName()+" and "+files[j].getName());
                    check.compareFiles(files[i], files[j]);
                    comparisons++;
                }
            }
        }
        System.out.println("Comparisons done: "+comparisons);
    }
}