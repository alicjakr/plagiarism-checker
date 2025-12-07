import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.junit.Test;

public class TestClass {
    @Test
    public void testCompareHamming() {
        Hamming hamming=new Hamming();
        assertEquals(3, hamming.compare("speed", "speeding"));
        assertEquals(1, hamming.compare("Hello", "hello"));
        assertEquals(-1, hamming.compare("", ""));
    }

    @Test
    public void testCompareHammingCleared() {
        HammingCleared hCleared=new HammingCleared();
        assertEquals(0, hCleared.compare("hello", "__he  ll o "));
        assertEquals(0, hCleared.compare("s p eed_ ing", "_sp ee  ding"));
        assertEquals(-1, hCleared.compare("", ""));
    }

    @Test
    public void testCheckPlagiarism() throws IOException {
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
        assertEquals(78, comparisons);
    }
}
