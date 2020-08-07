import java.util.Random;

public class Perceptron {

    String name;
    private double t=0.1; //prog
    double[] W;
    double[] wPrim ;
    double a = 0.5 ; //stala uczenia od uzytkownika (0,1)
    double netCheck;


    public  Perceptron(String name){
        this.W = new double[27];
        wPrim = new double[27];
        this.name = name;
        Random r = new Random();
        for(int i=0; i<26; i++){
            this.W[i] = r.nextDouble();
        }
        this.W[26]=0;
        normalizuj();
    }

    public void checkW(double[] p, String type, String pName){
        netCheck = findNet(p)-t;
        wyswietlPerceptron();

        if(type.equals(pName)&& netCheck>=0){   
            Main.ok++;
            System.out.println("ten jest dobrze");
        }else if(type.equals(pName) && netCheck<0) {
            findW(1, 0, p);
            Main.no++;
            System.out.println("ten jest zle!! i teraz t to: " + t);
        }else if(!type.equals(pName) && netCheck<0) { 
            Main.ok++;
            System.out.println("ten jest dobrze, bo net<0");
        }else if(!type.equals(pName) && netCheck>=0) {
            findW(0, 1, p);
            System.out.println(type + " taki jest : " + pName +"  ten jest zle!! i teraz t to: " + t);
            Main.no++;
        }
    }

    public void findW( int d, int y, double[] x){
        this.W[26] = t;
        x[26] = -1;
        for(int i = 0; i<26;i++){
            wPrim[i] = W[i] + (d-y)*a *x[i];
        }
        t = t+(d-y)*a*(-1);
        wPrim[26] = 0;
        W= wPrim;
        normalizuj();
    }



    public double findNet(double[] p) {
        double sum = 0;
        for(int i = 0; i < p.length; i++) {
            sum += p[i]*W[i];
        }
        return sum;
    }
    public void wyswietlPerceptron(){
        System.out.println(W[0] + " " +W[1] + " " +W[2] + " " +W[3] + " " +W[4] + " " +W[5] + " " +W[6] + " " +W[7] + " " +W[8] + " " +W[9] + " " +W[10] + " " +W[11]);
    }

    public void normalizuj(){
        double suma=0;
        for(int i=0; i<26;i++){
            suma+=W[i];
        }
        for(int i=0; i<26;i++){
            W[i] =W[i]/suma;
        }
    }


    public String checkLang(double[] l, String pName){
        netCheck = findNet(l)-t;
        System.out.println("to mój net " + netCheck + " a to t " + this.t + " w jezyku  " + pName);

        if( netCheck>=0){  
            return pName;

        }else  { 
            System.out.println("To nie " + pName);
            return null;
        }
    }

}