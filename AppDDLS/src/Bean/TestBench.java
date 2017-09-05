package Bean;

import java.security.Timestamp;

public class TestBench {
	private int testbId;
	// private arquivo testbFile;
	// configurar timestamp pelo bd com o now()
	private String registryAdmin;

	public int getTestbId() {
		return testbId;
	}

	public void setTestbId(int testbId) {
		this.testbId = testbId;
	}

	public String getRegistryAdmin() {
		return registryAdmin;
	}

	public void setRegistryAdmin(String registryAdmin) {
		this.registryAdmin = registryAdmin;
	}

}