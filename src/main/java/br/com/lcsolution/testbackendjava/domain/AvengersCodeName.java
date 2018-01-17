package br.com.lcsolution.testbackendjava.domain;

import com.google.gson.annotations.SerializedName;

public class AvengersCodeName {
		
		@SerializedName("codinome")
		private String codenome;

		public String getCodinome() {
			return codenome;
		}
}
