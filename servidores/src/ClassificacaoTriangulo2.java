
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class ClassificacaoTriangulo2 {

	private static int porta;
	
	
	
    public ClassificacaoTriangulo2(int porta) {
    		this.porta = porta;
    }
    
    public static String tipoTriangulo(double lado1,double lado2,double lado3) { 
    
    	String res="";
    	
    	if ((lado1 < lado2 + lado3) && (lado2 < lado1 + lado3) && (lado3 < lado1 + lado2)) {
            if (lado1 == lado2 && lado1 == lado3) {
                res= "Triangulo Equilatero";
            } else if ((lado1 == lado2) || (lado1 == lado3) || (lado2 == lado3)) {
                res="Triangulo Isosceles";
            } else
              res="Triângulo Escaleno";
        } else {
           res="Não é um triangulo!";
        }
       return res;     
    }
    

    public static void main(String args[]) throws IOException{

    	
    	double l1,l2,l3;
        String res="";
    	ServerSocket servidor = new ServerSocket (5564);
    	while(true) {
    	
    	Socket cliente = servidor.accept();
    	
    	ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream receber = new ObjectInputStream(cliente.getInputStream());
         
        l1 = receber.readDouble();
        l2 = receber.readDouble();
        l3 = receber.readDouble();
         
        res= tipoTriangulo(l1,l2,l3);
        
        enviar.writeUTF(res);       
        enviar.flush();
        
    }
       
    }
}
