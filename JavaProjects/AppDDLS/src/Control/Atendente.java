package Control;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import DAO.AdminDao;
import DAO.UserDao;

public class Atendente implements Runnable{
	private Socket socket;
	
	private BufferedReader in;
	private PrintStream out;
	
	private boolean started;
	private boolean running;
	
	private Thread thread;
	
	// ---------------------------------- Construtor -----------------------------------

	public Atendente(Socket socket) throws Exception{
		this.socket = socket;
		
		this.started = false;
		this.running = false;
		
		open();
	}
	
	// ------------------------------ Funcoes De Execucao ------------------------------

	private void open() throws Exception{
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			started = true;	
		} catch (Exception e) {
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
		
		try {
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
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
	
	public void stop() throws Exception{
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
				
				System.out.println("Mensagem recebida do cliente [" + socket.getInetAddress().getHostName() + ":" + socket.getPort() + "]: " + mensage);
				
	// -----------------------------------------------------------------------------
				
				/*switch (mensage.split(" ")[1].toUpperCase()) {
				case "LOGIN":
					Scanner scan = new Scanner(System.in);
					String scn = new String();	
					
					switch (mensage.split(" ")[2].toUpperCase()) {
						case "-U":
							UserDao ud = new UserDao();
							ud.connect();
							
							if (ud.verify(mensage.split(" ")[3])) {
								System.out.print("Password: ");
								//Esconder senha
								scn = scan.nextLine();
								if (true /*scn != ud.search() --> search precisa retornar senha ou as infos do user) {
									//Entra no ambiente do user
								} else {
									System.out.println("Erro: Senha invalida!");
								}
							} else {
								System.out.println("Erro: Matricula nao cadastrada!");
							}
							
							ud.disconnect();
							break;
							
						case "-A":
							AdminDao ad = new AdminDao();
							ad.connect();
							
							if (ad.verify(mensage.split(" ")[3])) {
								System.out.print("Password: ");
								//Esconder senha
								scn = scan.nextLine();
								if (true /*scn != ad.search() --> search precisa retornar senha ou as infos do admin) {
									//Entra no ambiente do admin
								} else {
									System.out.println("Erro: Senha invalida!");
								}
							} else {
								System.out.println("Erro: Matricula nao cadastrada!");
							}
								
							ad.disconnect();
							break;
					}
					break;
			
				case "LOGGOUT":
					break;

				default:
					break;
			}*/
				
	// -----------------------------------------------------------------------------
				
				if ("DDLS EXIT".equals(mensage.toUpperCase())) {
					break;
				}
			
				out.println(mensage);
			} catch (SocketTimeoutException e) {
				// ignorar
			} catch (Exception e){
				System.out.println(e);
				break;
			}
		}
		
		System.out.println("Encerrando conexao...");
		
		close();
	}
}