import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor implements Runnable{
	private ServerSocket server;
	
	private List<Atendente> atendentes;
	
	private boolean inicializado;
	private boolean executando;
	
	private Thread thread;
	
	public Servidor(int porta) throws Exception {
		
		atendentes = new ArrayList<Atendente>();
		inicializado = false;
		executando = false;
		
		open(porta);
	}
	
	private void open(int porta) throws Exception{
		server = new ServerSocket(porta);
		inicializado = true;
	}
	
	private void close() {
		for (Atendente atendente : atendentes) {
			try {
				atendente.stop();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		try {
			server.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		server = null;
		
		inicializado = false;
		executando = false;
		
		thread = null;
	}
	
	public void start() {
		if (!inicializado || executando) {
			return;
		}
		
		executando = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception{
		executando = false;
		if (thread != null) {
			thread.join();
		}
	}
	
	public void run() {
		System.out.println("Iniciando servidor...");

		while (executando) {
			try {
				server.setSoTimeout(2500);
				
				Socket socket = server.accept();
				
				System.out.println("Conexão estabelecida.");
				
				Atendente atendente = new Atendente(socket);
				atendente.start();
				
				atendentes.add(atendente); 
			} catch (SocketTimeoutException e) {
				// ignorar
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
		}
		
		close();
	}
	
	
	public static void main(String[] args) throws Exception{
		System.out.println("Iniciando servidor...");
		
		Servidor servidor = new Servidor(2525);
		servidor.start();
		
		System.out.println("Pressione ENTER para encerrar o servidor...");
		new Scanner(System.in).nextLine();
		
		System.out.println("Encerrando servidor...");
		servidor.stop();
	}
}