public class Hamming {
    public int compare(String str1, String str2) {
        int distance=0;

        if(str1.isEmpty() && str2.isEmpty()) {
            return -1;
        }

        int length1=str1.length();
        int length2=str2.length();
        int shorterLength=0;
        int difference=0;

        if(length1>=length2) {
            shorterLength=length2;
            difference=length1-length2;
        } else {
            shorterLength=length1;
            difference=length2-length1;
        }

        for(int i=0; i<shorterLength; i++) {
            if(str1.charAt(i)!=str2.charAt(i)) {
                distance++;
            }
        }

        distance+=difference;

        return distance;
    }

}
