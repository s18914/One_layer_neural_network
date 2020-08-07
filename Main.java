import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int ilePlikow;
    public static ArrayList<Language> train = new ArrayList<>();
    public static ArrayList<Perceptron> perceptrony = new ArrayList<>();
    static int ok=0, no=0;
    static int licznikPetli=0;

    public static void main(String[] args) {
        //czytanie pliku
        FileService service = new FileService();
        String dirPathname = "C:/Users/Admn/Desktop/dane";
        File directory = new File(dirPathname);

        if(!directory.isDirectory()){

            System.out.println(dirPathname + " is not directory");

        }

        try {
            service.prepareLanguages(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //tworze perceptrony
        for(int i=0; i<FileService.nazwyKatalogow.size();i++){
            Perceptron p = new Perceptron(FileService.nazwyKatalogow.get(i));
            perceptrony.add(p);
        }

        do{
            ok=0;
            no=0;

            System.out.println("MIESZANIE ZBIORU TRENINGOWEGO.....................");
            Collections.shuffle(train);


            for(Perceptron p : perceptrony){
                for(Language l : train){

                    p.checkW(l.getDimensions(), l.getName(), p.name);

                }
            }


            System.out.println("poprawnych: "+ ok + "  niepoprawnych: " + no );
            licznikPetli++;
        }while(ok/(no + ok )<0.95 && licznikPetli<10);

//GUI

        new GUI();


    }

}