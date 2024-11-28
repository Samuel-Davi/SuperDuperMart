package model;

public class Admin {
	
	private String username, senha, papel;

	public Admin(String username, String senha, String papel) {
		this.senha = senha;
		this.username = username;
		this.papel = papel;
	}

	public String getPapel() {
        return papel;
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
