package Control;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

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

	@SuppressWarnings("unused")
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
					if (mensage.split(" ").length == 3) {
						String[] aux = new String[4];
						String e = new String();
						
						switch (mensage.split(" ")[1].toUpperCase()) {
							case "-U":
								aux[0] = mensage.split(" ")[0].toUpperCase();
								aux[1] = mensage.split(" ")[1].toUpperCase();
								aux[2] = mensage.split(" ")[2].toUpperCase();
								
								System.out.print("Password: "); //Esconder senha
								
								aux[3] = new Scanner(System.in).nextLine().toUpperCase();
								
								mensage = aux[0] + " " + aux[1] + " " + aux[2] + " " + aux[3];
								
								client.send(mensage);
								break;
								
							case "-A":
								
								aux[0] = mensage.split(" ")[0].toUpperCase();
								aux[1] = mensage.split(" ")[1].toUpperCase();
								aux[2] = mensage.split(" ")[2].toUpperCase();
								
								System.out.print("Password: "); //Esconder senha
								
								aux[3] = new Scanner(System.in).nextLine().toUpperCase();
								
								mensage = aux[0] + " " + aux[1] + " " + aux[2] + " " + aux[3];
								
								client.send(mensage);
								
								
								if (true /*scn != ad.search() --> search precisa retornar senha ou as infos do admin*/) {
									//Entra no ambiente do admin
								} else {
									System.out.println("Erro: Senha invalida!");
								}
								break;
							
							default:
								System.out.println("Erro: Parametro inexistente!");
								break;
						}
						
						break;
					} else {
						System.out.println("Erro: Comando(\"login -parameter matricula\") incorreto!");
					}
					break;
				
				case "cc7fa036fec03ddbcc492c82e1c55dd15127e960": //case "LOGGED":
					if (true /*SUCCES*/) {
						UserDao ud = new UserDao();
						ud.connect();
						
					} else {
						System.out.println("Erro: Matricula ou senha incorretos!");
					}
					
					break;
	
				default:
					break;
			}
			
			if ("82915c102aa0e9bf54b8c04c4b4737c8f3fe4a36".equals(encrypt(mensage.split(" ")[0].toUpperCase()))) {
				break;
			}
		}
		
		
		
		
		//----------------------------------------------------------------------------------------------
		
		// SE O USUARIO LOGGAR COMO ADMIN
		/*	
			Scanner scan = new Scanner(System.in);
			String scn = scan.nextLine().toUpperCase();
					
			switch (scn) {
				case "ADDUSER":
					addUser();
					break;
				case "ADDADMIN":
					addAdmin();
					break;
				case "DELUSER":
					delUser();
					break;
				default:
					break;
			}
					
			scan.close();
		*/	
		//----------------------------------------------------------------------------------------------
		
		
		
		
		System.out.println("Cliente encerrado com sucesso!");
		
		client.stop();
		
		scanner.close();
	}
}