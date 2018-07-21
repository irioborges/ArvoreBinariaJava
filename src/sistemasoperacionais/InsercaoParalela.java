package sistemasoperacionais;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsercaoParalela implements Runnable{
   
    ArvoreAvl arvoreavl = new ArvoreAvl();    
    SharedData shareddata;
    
    private int numElementos;
    private int valorInicial;
    private int valorFinal;
    private int i;
    private No no;
    private int num_Thread;
    private int j;
    private int cont1 = 0;
    private int cont2;
    private int cont3;
    private int cont4;
    private int teste;
    private int temp;
    public boolean ocupado = false;
    
    private Random generator = new Random();
    
    public InsercaoParalela(SharedData sd, int valorInicial, int valorFinal){
        shareddata = sd;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }
           
    @Override
    public void run() {
                 
        try {
            for(j=valorInicial; j < valorFinal; j++){
                arvoreavl.inserir(j);
            }
          
            
            
            Thread.sleep(generator.nextInt(3));          
            
            depositaNo(arvoreavl.getRaiz());
            
        } catch (InterruptedException ex) {
            System.out.println("erro " + ex);
        }
    }
           
    synchronized void depositaNo(No raiz) {
        
        if(shareddata.getTemp() == 0){
            shareddata.setNo1(raiz);
            System.out.println("Primeiro no depositado");
        }else if(shareddata.getTemp() == 1){
            shareddata.setNo2(raiz);
            System.out.println("Segundo no depositado");
        }else if(shareddata.getTemp() == 2){
            shareddata.setNo3(raiz);
            System.out.println("Terceiro no depositado");
        }else if(shareddata.getTemp() == 3){
            shareddata.setNo4(raiz);
            System.out.println("Quarto no depositado");
        }
               
        //temp = shareddata.getTemp();
        //temp++;
        //shareddata.setTemp(temp);
        shareddata.incrementaTemp();
        System.out.println("Depositou a thread " + shareddata.getTemp());
        //arvoreavl.imprimirArvore();
        //shareddata.setI((shareddata.getI() + 1);
        
    }
    
}
