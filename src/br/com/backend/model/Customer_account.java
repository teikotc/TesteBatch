package br.com.backend.model;

public class Customer_account {

	private long id_customer;
	private String cpf_cpnj;
	private String nm_customer;
	private boolean is_active;
	private double vl_total;

	public long getId_customer() {
		return id_customer;
	}

	public void setId_customer(long id_customer) {
		this.id_customer = id_customer;
	}

	public String getCpf_cpnj() {
		return cpf_cpnj;
	}

	public void setCpf_cpnj(String cpf_cpnj) {
		this.cpf_cpnj = cpf_cpnj;
	}

	public String getNm_customer() {
		return nm_customer;
	}

	public void setNm_customer(String nm_customer) {
		this.nm_customer = nm_customer;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public double getVl_total() {
		return vl_total;
	}

	public void setVl_total(double vl_total) {
		this.vl_total = vl_total;
	}

}
