package br.com.lcsolution.testbackendjava.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Avengers {

	@SerializedName(value="vingadores")
	public List<AvengersCodeName> avengers;
	
	public List<AvengersCodeName> getAvengers() {
		return avengers;
	}
}
