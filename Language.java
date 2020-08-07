
public class Language {

    String name;
    double[] dimensions ;
    int type;
    double distance;



    public Language(double[] data, String name) {
        this.name = name;
        this.dimensions = new double[data.length];
        this.dimensions = data;
    }
    public Language(double[] data) {
        this.dimensions = new double[data.length];
        this.dimensions = data;
    }



    public String findName(){
        String ans;
        for(Perceptron p : Main.perceptrony){
               ans = p.checkLang(this.getDimensions(), p.name);
               if(ans!=null){
                   return ans;
               }
        }
        return null;
    }



    public String getName() {
        return name;
    }
    public void setDistance(double d){this.distance=d;}
    public double getDistance(){return this.distance;}
    public double[] getDimensions(){return this.dimensions;}
    public void setType(int t){this.type=t;}


}
