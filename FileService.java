import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileService {

    public static ArrayList<String> nazwyKatalogow = new ArrayList<>();

    static String catalogName=" a";



    private static final boolean RECURSIVE = true;


    public void prepareLanguages(File directory) throws IOException {

        File[] files = directory.listFiles();

        for (File file : files) {

            if(file.isFile()){
                file.getName();
                printFile(file, catalogName);

            } else if(file.isDirectory() && RECURSIVE){
                catalogName=file.getName();
                nazwyKatalogow.add(file.getName());
                System.out.println("nazwa katalogu : " + catalogName);
                // jeszcze raz to samo
                prepareLanguages(file);
            }
        }
    }


    private void printFile(File file, String catName) throws IOException{

        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            createLanguages(reader, catName);
        }finally{
            reader.close();
        }
    }


    private static void printReaderContent(BufferedReader reader) throws IOException {

        String line = null;

        while( (line = reader.readLine()) != null ){

            System.out.println(line);

        }

    }

    private void createLanguages(BufferedReader reader, String name) {

        String line = null;

            try {
                if ((line = reader.readLine()) != null) {
                    Language lang = new Language(countLetters(line),name);
                    Main.train.add(lang);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

// liczenie wektora
    private static double[] countLetters(String s){
        double[] vector = new double[27];
        vector[26]=0;
        char c ='a';
        int ileLiter=0;
        for(int i=0; i<26;i++){
            int licznik=0;
            for(int j =0; j<s.length();j++){
                if(s.charAt(j)==c||Character.toString(s.charAt(j))==Character.toString(c).toUpperCase()){
                    licznik++;
                    ileLiter++;
                }
            }
            vector[i] = licznik;
            c+=1;
        }
        //normalizacja...
        for(int i=0; i<26;i++){
            vector[i] = vector[i]/ileLiter;
        }

        return vector;
    }

    static  String sb = "";

    public static String oczysc(String l) {
        Pattern p = Pattern.compile("[A-Za-z]");

        for(int i=0; i<l.length();i++){
            Matcher m = p.matcher(Character.toString(l.charAt(i)));
            if(m.matches()){
                sb+=Character.toString(l.charAt(i));
            }
        }
        return sb;
    }

    public String findLang(String s){
        String line = oczysc(s);
        Language lang = new Language(countLetters(line));
        String ans = lang.findName();
        if(ans==null){
            return "Nie wybrano jezyka";
        }
        return ans;
    }

}
