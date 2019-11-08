

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class ServerVerificaCPF3 {

	private static int porta;
	
	
	
    public ServerVerificaCPF3(int porta) {
    		this.porta = porta;
    }
    
       

    public static void main(String args[]) throws IOException{

    	
    	
        String cpf="",res="";
        boolean valido;
    	ServerSocket servidor = new ServerSocket (5568);
    	while(true) {
    	
    	Socket cliente = servidor.accept();
    	
    	ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream receber = new ObjectInputStream(cliente.getInputStream());
         
        cpf = receber.readUTF();
        ValidaCPF validaCPF = new ValidaCPF();
        
         
        valido = validaCPF.isCPF(cpf); 
        
        if(valido) {
        	res = "CPF VÁLIDO";
        }else {
        	res = "CPF INVÁLIDO";
        }
        
        enviar.writeUTF(res);       
        enviar.flush();
        
    }
       
    }
}