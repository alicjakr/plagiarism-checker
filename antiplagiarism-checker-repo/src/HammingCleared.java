public class HammingCleared extends Hamming {
    private String clear(String str) {
        str=str.replaceAll("\\s+", "");
        str=str.replaceAll("_", "");
        return str;
    }

    @Override
    public int compare(String str1, String str2) {
        str1=clear(str1);
        str2=clear(str2);
        return super.compare(str1, str2);
    }
}