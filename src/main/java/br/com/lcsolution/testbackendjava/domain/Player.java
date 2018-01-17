package br.com.lcsolution.testbackendjava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private String telephone;
	
	@Column
	private String codename;
	
	@Column
	private String playerGroup;
	
	@Enumerated(EnumType.STRING)
	private StatusPlayer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getPlayerGroup() {
		return playerGroup;
	}
	
	public boolean isAvangers(){
		return this.getPlayerGroup().equals("avangers");
	}

	public void setPlayerGroup(String playerGroup) {
		this.playerGroup = playerGroup;
	}

	public StatusPlayer getStatus() {
		return status;
	}

	public void setStatus(StatusPlayer status) {
		this.status = status;
	}
}
