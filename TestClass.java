import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestClass {
    @Test
    public void testCompareHamming() {
        Hamming hamming=new Hamming();
        assertEquals(hamming.compare("speed", "speeding"), 3);
        assertEquals(hamming.compare("Hello", "hello"), 1);
        assertEquals(hamming.compare("", ""), -1);
    }

    @Test
    public void testCompareHammingCleared() {
        HammingCleared hCleared=new HammingCleared();
        assertEquals(hCleared.compare("hello", "__he  ll o "), 0);
        assertEquals(hCleared.compare("s p eed_ ing", "_sp ee  ding"), 0);
        assertEquals(hCleared.compare("", ""), -1);
    }

    @Test
    public void testCheckPlagiarism() throws IOException {
        CheckPlagiarism check=new CheckPlagiarism();

        File folder=new File("plagiarism_examples");
        File[] files=folder.listFiles();

        for(int i=0; i<files.length; i++) {
            for(int j=i+1; j<files.length; j++) {
                if(files[i].isFile() && files[j].isFile()) {
                    System.out.println("Comparing file "+files[i].getName()+" and "+files[j].getName());
                    check.compareFiles(files[i], files[j]);
                }
            }
        }
    }

    
}
