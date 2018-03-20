package Bean;

public class Dut {
	private int dutId;
	private String dutPath;
	private String dutDateUp;
	private boolean dutCheckTest;
	private String dutResultPath;
	private String registryUser;
	private int testbId;

	public int getDutId() {
		return dutId;
	}

	public String getDutPath() {
		return dutPath;
	}

	public void setDutPath(String dutPath) {
		this.dutPath = dutPath;
	}

	public String getDutDateUp() {
		return dutDateUp;
	}

	public boolean isDutCheckTest() {
		return dutCheckTest;
	}

	public void setDutCheckTest(boolean dutCheckTest) {
		this.dutCheckTest = dutCheckTest;
	}

	public String getDutResultPath() {
		return dutResultPath;
	}

	public void setDutResultPath(String dutResultPath) {
		this.dutResultPath = dutResultPath;
	}

	public String getRegistryUser() {
		return registryUser;
	}

	public void setRegistryUser(String registryUser) {
		this.registryUser = registryUser;
	}

	public int getTestbId() {
		return testbId;
	}

	public void setTestbId(int testbId) {
		this.testbId = testbId;
	}
}