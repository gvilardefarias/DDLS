package Control;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable{
	private ServerSocket server;
	
	private List<Manager> atendentes;
	
	private boolean inicializado;
	private boolean executando;
	
	private Thread thread;
	
	// ---------------------------------- Construtor -----------------------------------
	
	public Server(int porta) throws Exception {
		
		atendentes = new ArrayList<Manager>();
		inicializado = false;
		executando = false;
		
		open(porta);
	}
	
	// ------------------------------ Funcoes De Execucao ------------------------------
	
	private void open(int porta) throws Exception{
		server = new ServerSocket(porta);
		inicializado = true;
	}
	
	private void close() {
		for (Manager atendente : atendentes) {
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
				
				System.out.println("Conexao estabelecida.");
				
				Manager atendente = new Manager(socket);
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
	
	// ------------------------------------ Metodos ------------------------------------

	
	
	// -------------------------------- Funcao Principal -------------------------------
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		System.out.println("Iniciando servidor...");
		
		Server servidor = new Server(2525);
		servidor.start();
		
		System.out.println("Pressione ENTER para encerrar o servidor...");
		new Scanner(System.in).nextLine();
		
		System.out.println("Encerrando servidor...");
		servidor.stop();
	}
}