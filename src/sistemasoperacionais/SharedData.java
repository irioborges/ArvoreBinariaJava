package sistemasoperacionais;

public class SharedData {
    volatile int i;
    volatile No no_raiz;
    volatile No no1;
    volatile No no2;
    volatile No no3;
    volatile No no4;
    volatile int temp = 0;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp2) {
        this.temp = temp2;
    }

    public void setNo1(No no1) {
        this.no1 = no1;
    }

    public void setNo2(No no2) {
        this.no2 = no2;
    }

    public void setNo3(No no3) {
        this.no3 = no3;
    }

    public void setNo4(No no4) {
        this.no4 = no4;
    }
    
    public No getNo1() {
        return no1;
    }
    
    public No getNo2(){
        return no2;
    }
    
    public No getNo3(){
        return no3;
    }
    
    public No getNo4(){
        return no4;
    }
    
    public int getI() {
        return i;
    }

    public void setI(int valor) {
        this.i = valor;
    }    

    public synchronized void incrementaTemp(){
        this.temp++;
    }
}
