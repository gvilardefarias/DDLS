package Bean;

public class TestBench {
	private int testbId;
	private String referenceFiles;
	private String testbDateServiceUp;
	//Configurar timestamp pelo bd com o now()
	private String registryAdmin;

	public String getTestbDateServiceUp() {
		return testbDateServiceUp;
	}

	public String getReferenceFiles() {
		return referenceFiles;
	}

	public void setReferenceFiles(String referenceFiles) {
		this.referenceFiles = referenceFiles;
	}

	public int getTestbId() {
		return testbId;
	}

	public String getRegistryAdmin() {
		return registryAdmin;
	}

	public void setRegistryAdmin(String registryAdmin) {
		this.registryAdmin = registryAdmin;
	}

}