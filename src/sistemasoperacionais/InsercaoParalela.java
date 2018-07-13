package sistemasoperacionais;

public class InsercaoParalela extends Thread{
   
    ArvoreAvl arvoreavl = new ArvoreAvl();    
    SharedData sd;
    
    private int numElementos;
    private int valorInicial;
    private int valorFinal;
    
    public InsercaoParalela(SharedData sd){
        this.sd = sd;
    }
    
    
    @Override
    public void run(){
         //sd.         
    }
}
