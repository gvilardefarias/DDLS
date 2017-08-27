package Controle;

import Bean.Admin;
import Bean.Aluno;
import DAO.AdminDao;
import DAO.AlunoDao;

public class Start extends Metodos{
	public static void main(String[] args) {
		Aluno a = new Aluno();

		a.setNome("Phulano");
		a.setMatriculaAluno("201610010012");
		a.setSenha(encrypt("phulano12345"));
		a.setEmail("phulano@gmail.com");
		a.setCurso("Engenharia Da Computação");
		a.setPeriodo(4);

		AlunoDao ad = new AlunoDao();
		AdminDao admd = new AdminDao();
		
		ad.conectar();
		admd.conectar();
		
		ad.salvar(a);
		ad.buscar(a.getMatriculaAluno());
		//ad.apagar(a);
		
		
		Admin adm = new Admin();

		adm.setNome("Admin");
		adm.setMatriculaAdmin("201610010022");
		adm.setSenha(encrypt("admin12345"));
		adm.setEmail("admin@gmail.com");
		
		admd.salvar(adm);
		admd.buscar(adm.getMatriculaAdmin());
		//admd.apagar(adm);
		
		ad.desconectar();
		admd.desconectar();
	}
}