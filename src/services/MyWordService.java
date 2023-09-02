package services;
import java.io.InputStream;
import java.util.*;

public class MyWordService implements WordService{
    private Set<String> set = new HashSet<>();
    private static final Locale locale = Locale.forLanguageTag("tr-TR");

    @Override
    public void load(InputStream stream) {
        try{
            Scanner sc = new Scanner(stream);
            while(sc.hasNext()){
                String line = sc.nextLine();
                set.add(line.toUpperCase(locale));
            }
            stream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isWord(String word) {
        return set.contains(word);
    }

    @Override
    public String getRandomWord() {
        int randNum = new Random().nextInt(0, set.size());
        int i = 0;
        for(String word : set){
            if(i == randNum){
                return word;
            }
            i++;
        }
        System.out.println("error");
        return null;
    }
}
