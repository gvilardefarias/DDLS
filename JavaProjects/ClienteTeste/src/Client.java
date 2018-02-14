import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client implements Runnable{
	private Socket socket;
	
	private BufferedReader in;
	private PrintStream out;
	
	private boolean started;
	private boolean running;
	
	private Thread thread;
	
	// ---------------------------------- Construtor -----------------------------------

	public Client(String adrress, int porta) throws Exception {
		started = false;
		running = false;
		
		open(adrress, porta);
	}
	
	// ------------------------------ Funcoes De Execucao ------------------------------

	private void open(String adrress, int porta) throws Exception {
		try {
			socket = new Socket(adrress, porta);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			
			started = true;
		} catch (Exception e) {
			System.out.println(e);
			close();
			throw e;
		}
	}
	
	private void close() {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		if (socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		in = null;
		out = null;
		
		socket = null;
		
		started = false;
		running = false;
		
		thread = null;
	}
	
	public void start() {
		if (!started || running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws Exception {
		running = false;
		
		if (thread != null) {
			thread.join();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				socket.setSoTimeout(2500);
				
				String mensage = in.readLine();
				
				if (mensage == null) {
					break;
				}
				
				System.out.println("Mensagem enviada pelo servidor: " + mensage);
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

	public boolean isExecutando() {
		return running;
	}
	
	public void send(String mensage) {
		out.println(mensage);
	}
	
	// -------------------------------- Funcao Principal -------------------------------

	public static void main(String[] args) throws Exception{
		System.out.println("Iniciando cliente...");
		
		System.out.println("Iniciando conexao com o servidor...");
		
		Client client = new Client("localhost", 2525);
		
		System.out.println("Conexao estabelecida com sucesso...");
		
		client.start();
		
		Scanner scanner = new Scanner(System.in);
		
		while (true){
			String mensage = scanner.nextLine();
			
			if (!client.isExecutando()) {
				break;
			}
			
			client.send(mensage);
			
			if ("DDLS LOGGOUT".equals(mensage.toUpperCase())){
				break;
			}
		}

		System.out.println("Encerrando cliente...");
		
		client.stop();
		
		scanner.close();
	}
}