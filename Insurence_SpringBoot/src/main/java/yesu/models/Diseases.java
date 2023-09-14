package yesu.models;

public class Diseases {

	int disease_id;
	String disease_name;
	String disease_code;
	String disease_desc;
	String disease_status;

	public int getDisease_id() {
		return disease_id;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public String getDisease_code() {
		return disease_code;
	}

	public String getDisease_desc() {
		return disease_desc;
	}

	public String getDisease_status() {
		return disease_status;
	}

	public void setDisease_id(int disease_id) {
		this.disease_id = disease_id;
	}

	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}

	public void setDisease_code(String disease_code) {
		this.disease_code = disease_code;
	}

	public void setDisease_desc(String disease_desc) {
		this.disease_desc = disease_desc;
	}

	public void setDisease_status(String disease_status) {
		this.disease_status = disease_status;
	}

	public Diseases() {

	}

}