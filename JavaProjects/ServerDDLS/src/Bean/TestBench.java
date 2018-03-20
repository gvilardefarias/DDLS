package Bean;

public class TestBench {
	private int testbId;
	private String referenceFiles;
	private String testbDateUp;
	private String registryAdmin;

	public String getTestbDateUp() {
		return testbDateUp;
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