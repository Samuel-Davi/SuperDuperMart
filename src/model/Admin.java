package model;

public class Admin {
	
	private String username, senha;

	public Admin(String username, String senha) {
		this.senha = senha;
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSenha() {
		return this.senha;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSenha(String senha) {
        this.senha = senha;
    }

}
