package sistemasoperacionais;

import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;

public class SistemasOperacionais {

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner Scanner_in = new Scanner(System.in);
        Thread thread[];
        No no = new No(0);
                        
        ArvoreAvl arvoreavl = new ArvoreAvl();
                        
        long long_msBegin = 0,long_msEnd = 0,long_msTime = 0;
        int int_numNos, int_numThreads = 0;
        int int_i, int_rnd;
        int int_opcao = 0;
        int int_tpProg = 0;
        int int_valor;
                      
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
                System.out.println("2. Paralela");
                                
                int_tpProg = Scanner_in.nextInt();
                
                if(int_tpProg == 2){
                    System.out.println("Digite o número de threads que serão utilizadas");
                    int_numThreads = Scanner_in.nextInt();
                }
                
            }else if(int_opcao == 2){
                System.out.println("Digite o numero de nós que serão inseridos na árvore em KiloBytes: ");
                int_numNos = Scanner_in.nextInt() * 1024;
                
                if(int_tpProg == 1){
                    long_msBegin = System.currentTimeMillis();
                    for(int_i = 0; int_i < int_numNos; int_i++){
                        int_rnd = rnd.nextInt(int_numNos * 2);
                        arvoreavl.inserir(int_rnd);
                    }
                    long_msEnd = System.currentTimeMillis();
                }else if(int_tpProg == 2){
                    thread = new Thread[int_numThreads];
                    
                    System.out.println("Numero de threads a serem criadas " + int_numThreads);
                    
                    long_msBegin = System.currentTimeMillis();
                    for(int_i = 0; int_i < int_numThreads; int_i++){
                        thread[int_i] = new Thread(new InsercaoParalela());
                        thread[int_i].start();
                    }
                    try{
                        for(int_i = 0; int_i < int_numThreads; int_i++){
                            thread[int_i].join();
                        }
                    }catch(InterruptedException x) { }
                                                          
                    long_msEnd = System.currentTimeMillis();
                    
                    
                }
                System.out.println(int_numNos + " Kilo elementos foram inseridos com sucesso!");
                System.out.println("O tempo demorado foi de: " + ((long_msEnd - long_msBegin)) + "(ms)");
            }else if(int_opcao == 3){
                
                no = arvoreavl.getRaiz();
                System.out.println(no.getChave());
                System.out.println(arvoreavl.altura(arvoreavl.getRaiz()));
                
            }else if(int_opcao == 4){
                System.out.println("Executando balanceamento...");
                            
                arvoreavl.verificarBalanceamento(arvoreavl.getRaiz());
            }else if(int_opcao == 5){
                
            }
              
            
            
        }
        
        
        
        
        
    }
    
}
