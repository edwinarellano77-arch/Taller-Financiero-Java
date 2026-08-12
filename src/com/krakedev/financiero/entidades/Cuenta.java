package com.krakedev.financiero.entidades;

public class Cuenta {

	private String id;
	private double saldoActual;
	private String tipo;

	public Cuenta(String id) {
		this.id = id;
		this.saldoActual = 0;
		this.tipo = "A";

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void print() {
		String mensaje;

		mensaje = "ID: " + id + " , Saldo Actual:  " + saldoActual + " ,  Tipo: " + tipo;

		System.out.println(mensaje);

	}

}
