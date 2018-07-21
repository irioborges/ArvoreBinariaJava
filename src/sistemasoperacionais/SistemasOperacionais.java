package sistemasoperacionais;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;
//import java.lang.Thread;
//import java.util.concurrent.*;

public class SistemasOperacionais {

    public static void main(String[] args) throws InterruptedException {
        Random rnd = new Random();
        Scanner Scanner_in = new Scanner(System.in);
        
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        
        SharedData sd = new SharedData();
        
        InsercaoParalela insercao1;
        InsercaoParalela insercao2;
        InsercaoParalela insercao3;
        InsercaoParalela insercao4;
               
        No no = new No(0);
        No no1 = new No(0);
        No no2 = new No(0);
        No no3 = new No(0);
        No no4 = new No(0);
        No no5 = new No(0);
        No no6 = new No(0);
                                
        ArvoreAvl arvoreavl1 = new ArvoreAvl();
        ArvoreAvl arvoreavl2 = new ArvoreAvl();
        ArvoreAvl arvoreavl3 = new ArvoreAvl();
        ArvoreAvl arvoreavl4 = new ArvoreAvl();
        ArvoreAvl arvoreavl5 = new ArvoreAvl();
        ArvoreAvl arvoreavl6 = new ArvoreAvl();
        ArvoreAvl arvoreavl7 = new ArvoreAvl();
                        
        long long_msBegin = 0,long_msEnd = 0,long_msTime = 0;
        int int_numNos, int_numThreads = 0;
        int int_i, int_rnd;
        int int_opcao = 0;
        int int_tpProg = 0;
        int int_valor;
        int int_temp, int_valorBuscar;
                      
        while(int_opcao != 6 ){
            System.out.println("Digite a opção desejada");
            System.out.println("1. Escolher o tipo de programação a ser utilizada na árvore");
            System.out.println("2. Inserir nós");
            System.out.println("3. Buscar nó");
            System.out.println("4. Balancear árvore");
            System.out.println("5. Imprimir árvore por nível");
            System.out.println("6. Sair");
            
            int_opcao = Scanner_in.nextInt();
            
            if(int_opcao == 1){
                System.out.println("Digite a opção desejada");
                System.out.println("1. Sequencial");
                System.out.println("2. Concorrente");
                                
                int_tpProg = Scanner_in.nextInt();
                
            }else if(int_opcao == 2){
                System.out.println("Digite o numero de nós que serão inseridos na árvore em KiloBytes: ");
                int_numNos = Scanner_in.nextInt();
                //int_numNos *= 1024 ;
                
                if(int_tpProg == 1){
                    long_msBegin = System.currentTimeMillis();
                    for(int_i = 0; int_i < int_numNos; int_i++){
                        int_rnd = rnd.nextInt(int_numNos * 2);
                        arvoreavl1.inserir(int_rnd);
                    }
                    long_msEnd = System.currentTimeMillis();
                    
                    System.out.println("O tempo demorado foi de: " + ((long_msEnd - long_msBegin)) + "(ms)");
                    
                }else if(int_tpProg == 2){
                    
                                
                    //sd.setInt_valor_inicial1(0);
                    //sd.setInt_valor_inicial2(int_numNos / 4);
                    //sd.setInt_valor_inicial3(int_numNos /2);
                    //sd.setInt_valor_inicial4((int_numNos * 3) / 4);
                    
                    //sd.setInt_valor_final1(int_numNos / 4);
                    //sd.setInt_valor_final2(int_numNos / 2);
                    //sd.setInt_valor_final3((int_numNos * 3) / 4);
                    //sd.setInt_valor_final4(int_numNos);
                    
                    insercao1 = new InsercaoParalela(sd, 0, int_numNos / 4);
                    insercao2 = new InsercaoParalela(sd, int_numNos / 4, (int_numNos) / 2);
                    insercao3 = new InsercaoParalela(sd, (int_numNos) / 2, (int_numNos * 3) / 4);
                    insercao4 = new InsercaoParalela(sd, (int_numNos * 3) / 4, int_numNos);
                    
                    
                    
                    threadExecutor.execute(insercao1);
                    threadExecutor.execute(insercao2);
                    threadExecutor.execute(insercao3);
                    threadExecutor.execute(insercao4);
                    
                    //long_msBegin = System.currentTimeMillis();
                    
                    threadExecutor.shutdown();
                    
                    long_msBegin = System.currentTimeMillis();
                    
                    while(threadExecutor.isTerminated() == false){
                        
                    }
                    
                    arvoreavl1.setRaiz(sd.getNo1());
                    arvoreavl2.setRaiz(sd.getNo2());
                    arvoreavl3.setRaiz(sd.getNo3());
                    arvoreavl4.setRaiz(sd.getNo4());
                    
                    System.out.println(sd.getTemp());
                    
                    //if(sd.getTemp() == 3){
                    //if(threadExecutor.isTerminated() == true){
                        long_msEnd = System.currentTimeMillis();
                        System.out.println(int_numNos + " Kilo elementos foram inseridos com sucesso!");
                        System.out.println("O tempo demorado foi de: " + ((long_msEnd - long_msBegin)) + "(ms)");
                    //}
                    
                    
                }
                
            }else if(int_opcao == 3){
                
                //no = arvoreavl1.getRaiz();
                //System.out.println(no.getChave());
                //System.out.println(arvoreavl1.altura(arvoreavl1.getRaiz()));
                
                System.out.println("Digite o valor do nó a ser procurado");
                int_valorBuscar = Scanner_in.nextInt();
                
                //arvoreavl1.buscaInorder(int_valorBuscar);
                //arvoreavl2.buscaInorder(int_valorBuscar);
                //arvoreavl3.buscaInorder(int_valorBuscar);
                //arvoreavl4.buscaInorder(int_valorBuscar);
                
            }else if(int_opcao == 4){
                System.out.println("Executando balanceamento...");
                            
                arvoreavl1.verificarBalanceamento(arvoreavl1.getRaiz());
            }else if(int_opcao == 5){
                
                arvoreavl1.imprimirArvore();
                
                arvoreavl2.imprimirArvore();
                
                arvoreavl3.imprimirArvore();
                
                arvoreavl4.imprimirArvore();
                
                /*
                no1.setDireita(arvoreavl1.getRaiz());
                no1.setEsquerda(arvoreavl2.getRaiz());
                
                no2.setDireita(arvoreavl3.getRaiz());
                no2.setEsquerda(arvoreavl4.getRaiz());
                
                no3.setDireita(no1);
                no3.setEsquerda(no2);
                
                arvoreavl5.setRaiz(no3);
                
                arvoreavl5.imprimirArvore(); */
                
            }
              
            
            
        }
        
        
        
        
        
    }
    
}
