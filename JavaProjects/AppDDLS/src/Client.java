import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Control.Metods;
import DAO.UserDao;

public class Client extends Metods implements Runnable {
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

	public boolean isExecutando() {
		return running;
	}
	
	public void send(String mensage) {
		out.println(mensage);
	}
	
	// -------------------------------- Funcao Principal -------------------------------

	public static void main(String[] args) throws Exception {
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
			
			//-------------------------------------------------------------------------------
			
			switch (encrypt(mensage.split(" ")[0].toUpperCase())) {
				case "d4fc4761f015d39c1d3bd6424c485e8c1b23849c": //case "LOGIN":
					if (mensage.length() == 3) {
						String[] usr = new String[4];
						
						switch (mensage.split(" ")[1].toUpperCase()) {
							case "-U":
								usr[0] = mensage.split(" ")[0].toUpperCase();
								usr[1] = mensage.split(" ")[1].toUpperCase();
								usr[2] = mensage.split(" ")[2].toUpperCase();
								
								System.out.print("Password: "); //Esconder senha
								
								usr[3] = new Scanner(System.in).nextLine().toUpperCase();
								
								mensage = usr[0] + " " + usr[1] + " " + usr[2] + " " + usr[3];
								
								client.send(mensage);
								break;
								
							case "-A":
								
								System.out.print("Password: "); //Esconder senha
								
								
								if (true /*scn != ad.search() --> search precisa retornar senha ou as infos do admin*/) {
									//Entra no ambiente do admin
								} else {
									System.out.println("Erro: Senha invalida!");
								}
								break;
						}
						break;
					} else {
						System.out.println("Erro: Comando(\"login -parameter matricula\") incorreto!");
					}
				
				case "cc7fa036fec03ddbcc492c82e1c55dd15127e960": //case "LOGGED":
					if (true /*SUCCES*/) {
						UserDao ud = new UserDao();
						ud.connect();
						
					} else {
						System.out.println("Erro: Matricula ou senha incorretos!");
					}
					
					break;
					
				case "82915c102aa0e9bf54b8c04c4b4737c8f3fe4a36": //case "LOGGOUT":
					break;
	
				default:
					break;
			}
			
			/*if ("DDLS LOGGOUT".equals(mensage.toUpperCase())){
				break;
			}*/
		}

		System.out.println("Encerrando cliente...");
		
		client.stop();
		
		scanner.close();
	}
}