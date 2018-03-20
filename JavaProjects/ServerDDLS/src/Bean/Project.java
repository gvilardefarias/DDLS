package Bean;

public class Project {
	private int projectId;
	private String title;
	private String referenceFiles;
	private int testbId;
	private String registryAdmin;

	public int getProjectId() {
		return projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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