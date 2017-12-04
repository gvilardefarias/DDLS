package Control;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Bean.Admin;
import Bean.User;
import DAO.AdminDao;
import DAO.UserDao;

public class Metods {
	protected static String encrypt(String input) {
		final StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());

			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	protected static void addUser() {
		UserDao ud = new UserDao();
		ud.connect();
			
		User u = new User();
			
		Scanner scan = new Scanner(System.in);
			
		System.out.print("- Nome do(a) aluno(a): ");
		String scn = scan.nextLine();
		u.setNameUser(scn.toUpperCase());
			
		System.out.print("- Matricula do(a) aluno(a): ");
		scn = scan.nextLine();
		u.setRegistryUser(scn);
			
		System.out.print("- Senha do(a) aluno(a): ");
		scn = scan.nextLine();
		u.setPassUser(encrypt(scn));
			
		System.out.print("- Email do(a) aluno(a): ");
		scn = scan.nextLine();
		u.setEmailUser(scn.toUpperCase());
			
		System.out.print("- Curso do(a) aluno(a): ");
		scn = scan.nextLine();
		u.setCourseUser(scn.toUpperCase());
			
		System.out.print("- \"Ano.periodo\" de entrada do(a) aluno(a): ");
		scn = scan.nextLine();
		u.setClassUser(scn);
			
		System.out.print("- Grupo do(a) aluno(a): ");
		int scni = scan.nextInt();
		u.setNumberGp(scni);

		ud.save(u); //Fazer verificação antes de inserir no BD
			
		ud.verify(u.getRegistryUser());
		
		scan.close();
		ud.disconnect();
	}

	protected static void addAdmin() {
		AdminDao admd = new AdminDao();
		admd.connect();
		
		Admin adm = new Admin();
			
		Scanner scan = new Scanner(System.in);
			
		System.out.print("- Nome do(a) aluno(a): ");
		String scn = scan.nextLine();
		adm.setNameAdmin(scn.toUpperCase());
			
		System.out.print("- Matricula do(a) aluno(a): ");
		scn = scan.nextLine();
		adm.setRegistryAdmin(scn);
			
		System.out.print("- Senha do(a) aluno(a): ");
		scn = scan.nextLine();
		adm.setPassAdmin(encrypt(scn));
			
		System.out.print("- Email do(a) aluno(a): ");
		scn = scan.nextLine();
		adm.setEmailAdmin(scn.toUpperCase());
		
		admd.save(adm); //Fazer verificação antes de inserir no BD
		
		admd.verify(adm.getRegistryAdmin());
		
		scan.close();
		admd.disconnect();
	}

	protected static void delUser() {
		UserDao ud = new UserDao();
		ud.connect();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Matricula do(a) usuario(a) que deseja excluir: ");
		String registryUser = scan.nextLine();
		
		if (ud.verify(registryUser)) {
			System.out.println("O(A) usuario(a) da matricula" + registryUser + " existe e sera excluido(a)!");
			ud.delete(registryUser);
		} else {
			System.out.println("O(A) usuario(a) da matricula" + registryUser + " nao esta cadastrado(a)!\n");
			
			System.out.println("Deseja cadastra-lo(a)?");
			String r = scan.nextLine().toUpperCase();
			
			if (r == "S" || r == "SIM") {
				addUser();
			}
		}
		
		scan.close();
	}

	protected static void delAdmin() {
		AdminDao admd = new AdminDao();
		admd.connect();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Matricula do(a) administrador(a) que deseja excluir: ");
		String registryAdmin = scan.nextLine();
		
		if (admd.verify(registryAdmin)) {
			System.out.println("O(A) adminstrador(a) da matricula" + registryAdmin + " existe e sera excluido(a)!");
			admd.delete(registryAdmin);
		} else {
			System.out.println("O(A) adminstrador(a) da matricula" + registryAdmin + " nao esta cadastrado(a)!\n");
			
			System.out.println("Deseja cadastra-lo(a)?");
			String r = scan.nextLine().toUpperCase();
			
			if (r == "S" || r == "SIM") {
				addAdmin();
			}
		}
		
		scan.close();
	}
}