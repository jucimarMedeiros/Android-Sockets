import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class ServidorEquacaoSegundoGrau3 {

	private static int porta;
	
	
	
    public ServidorEquacaoSegundoGrau3(int porta) {
    		this.porta = porta;
    }
    
    public static double delta(int a,int b,int c) {    	    	
    	return (b * b) + (-4 * (a * c));   	    	
    }
    private static double calculaX1(int a,int b,int c,double delta) {  
        return (double) ((-(b) + Math.sqrt(delta)) / 2 * a);  
    }
    private static double calculaX2(int a,int b,int c,double delta) {   	   	 
   	 return (double) ((-(b) - Math.sqrt(delta)) / 2 * a); 
   }

    public static void main(String args[]) throws IOException{

    	int v1=0;
    	int a,b,c;
        double delta=0,x1 = 0,x2=0;
    	ServerSocket servidor = new ServerSocket (5562);
    	while(true) {
    	
    	Socket cliente = servidor.accept();
    	
    	ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream receber = new ObjectInputStream(cliente.getInputStream());
         
        a = receber.readInt();
        b = receber.readInt();
        c = receber.readInt();
         
        delta = delta(a,b,c);
        x1 = calculaX1(a,b,c,delta);
        x2 = calculaX2(a,b,c,delta);
        
        enviar.writeDouble(x1);
        enviar.writeDouble(x2);
        enviar.flush();
        
    }
       
    }
}