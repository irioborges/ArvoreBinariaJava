package sistemasoperacionais;

public class InsercaoParalela implements Runnable{
   
    ArvoreAvl arvoreavl = new ArvoreAvl();    
    
    private int numElementos;
    private int valorInicial;
    private int valorFinal;
    
    public InsercaoParalela(){};
    
    public InsercaoParalela(int numElementos, int valorInicial, int valorFinal){
        this.numElementos = numElementos;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }
    
    @Override
    public void run(){
            System.out.println("teste");       
    }
}
