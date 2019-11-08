import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleJavaServer implements Runnable {

	public Socket cliente;
	private static ArrayList<Integer> serverTriangulo = new ArrayList();
	private static ArrayList<Integer> serverSegundoGrau = new ArrayList();
	private static ArrayList<Integer> serverCPF = new ArrayList();
    private static ArrayList<Socket> listaDeClientes = new ArrayList<Socket>();

	
    public SimpleJavaServer(Socket cliente) {
    	 this.cliente = cliente;
    }

    public static void main(String args[]) throws IOException{

    	ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Porta 12345 aberta!");
        
        serverSegundoGrau.add(5560);
        serverSegundoGrau.add(5561);
        serverSegundoGrau.add(5562);
        
        serverTriangulo.add(5563);
        serverTriangulo.add(5564);
        serverTriangulo.add(5565);
        serverCPF.add(5566);
        serverCPF.add(5567);
        serverCPF.add(5568);

        // Aguarda alguém se conectar. A execução do servidor
        // fica bloqueada na chamada do método accept da classe
        // ServerSocket. Quando alguém se conectar ao servidor, o
        // método desbloqueia e retorna com um objeto da classe
        // Socket, que é uma porta da comunicação.
        System.out.println("Aguardando conexão do cliente...");

        while (true) {
            Socket cliente = servidor.accept();
            
           
            listaDeClientes.add(cliente);
            // Cria uma thread do servidor para tratar a conexão
            SimpleJavaServer tratamento = new SimpleJavaServer(cliente);
            Thread t = new Thread(tratamento);
            // Inicia a thread para o cliente conectado
            t.start();
        }
    }

    void servidorDisponivelSegundoGrau(int p) {
    	for(int i=0; i < serverSegundoGrau.size();i++) {
 		   if(serverSegundoGrau.get(i) == 0 ) { 			  
 			  serverSegundoGrau.set(i,p);
 			   break;
 		   }
 	   }
    }

   int servidorOcupadoSegundoGrau() {
	   int res=0;
	   for(int i=0; i < serverSegundoGrau.size();i++) {
		   if(serverSegundoGrau.get(i) != 0 ) {
			   res = serverSegundoGrau.get(i);
			   serverSegundoGrau.set(i,0);
			   break;
		   }
	   }
	return res; 
	   
   } 
   
   void servidorDisponivelTriangulo(int p) {
   	for(int i=0; i < serverTriangulo.size();i++) {
		   if(serverTriangulo.get(i) == 0 ) { 			  
			   serverTriangulo.set(i,p);
			   break;
		   }
	   }
   }

  int servidorOcupadoTriangulo() {
	   int res=0;
	   for(int i=0; i < serverTriangulo.size();i++) {
		   if(serverTriangulo.get(i) != 0 ) {
			   res = serverTriangulo.get(i);
			   serverTriangulo.set(i,0);
			   break;
		   }
	   }
	return res; 
	   
  } 
  void servidorDisponivelCPF(int p) {
	   	for(int i=0; i < serverCPF.size();i++) {
			   if(serverCPF.get(i) == 0 ) { 			  
				   serverCPF.set(i,p);
				   break;
			   }
		   }
	   }

	  int servidorOcupadoCPF() {
		   int res=0;
		   for(int i=0; i < serverCPF.size();i++) {
			   if(serverCPF.get(i) != 0 ) {
				   res = serverCPF.get(i);
				   serverCPF.set(i,0);
				   break;
			   }
		   }
		return res; 
		   
	  }
   
   
    
    
/* A classe Thread, que foi instancia no servidor, implementa Runnable.
Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'.
*/
public void run(){
 System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());


	
 String str;
 int codigo=0,v2=0,ocupado=0,porta=0;

 try {
    
                
     while (true) {
         
         ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream());
         ObjectInputStream receber = new ObjectInputStream(cliente.getInputStream());
         
         codigo = receber.readInt();
        
         
         if(codigo == 5560) {
        	 int a,b,c;
        	 double x1 = 0,x2=0;
        	 a = receber.readInt();
        	 b = receber.readInt();
        	 c = receber.readInt();       	 
        	
        	 porta = servidorOcupadoSegundoGrau();
        	 System.out.println(porta);
        	 Socket s = new Socket("127.0.0.1", porta); 
        	 
        	 ObjectOutputStream enviar2 = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream receber2 = new ObjectInputStream(s.getInputStream());
             enviar2.writeInt(a);
             enviar2.writeInt(b);
             enviar2.writeInt(c);
             enviar2.flush();

             x1 = receber2.readDouble();
             x2 = receber2.readDouble();
            
             enviar.writeDouble(x1);
             enviar.writeDouble(x2);
             servidorDisponivelSegundoGrau(porta);
             enviar.flush();
             
         }
         if(codigo == 5564) {
        	 double lado1 = 0,lado2=0,lado3=0;
        	 String res = "";
        	 
        	 lado1 = receber.readDouble();
        	 lado2 = receber.readDouble();
        	 lado3 = receber.readDouble();
        	
        	 porta = servidorOcupadoTriangulo();
             Socket s = new Socket("127.0.0.1", porta); 
        	 
        	 ObjectOutputStream enviar2 = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream receber2 = new ObjectInputStream(s.getInputStream());
             enviar2.writeDouble(lado1);
             enviar2.writeDouble(lado2);
             enviar2.writeDouble(lado3);
             enviar2.flush();

             
             res=receber2.readUTF();
             
             enviar.writeUTF(res);
             servidorDisponivelTriangulo(porta);
             enviar.flush();
         }
         if(codigo == 5566) {
        	 String cpf = "",res="";
        	 
        	 cpf = receber.readUTF();
        	 
        	 porta = servidorOcupadoCPF();
             Socket s = new Socket("127.0.0.1", porta); 
        	 
        	 ObjectOutputStream enviar2 = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream receber2 = new ObjectInputStream(s.getInputStream());
             enviar2.writeUTF(cpf);
             enviar2.flush();

             res = receber2.readUTF();
             enviar.writeUTF(res);
             servidorDisponivelCPF(porta);
             enviar.flush();
         }
         
        
     }
 }
 catch (Exception err){
     System.err.println(err);
 }
}
}


