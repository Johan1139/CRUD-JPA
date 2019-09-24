package com.ecodeup.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


//HIBERNATE RECONOCE TODAS LAS TABLAS DE LA BD MEDIANTE ANOTACIONES
//ENTIDAD QUE SE VA A RELACIONAR EN UNA BD
@Entity

//LA CLASE Producto se almacena en la tabla de la BD productos
@Table(name="empleado")
public class Empleados {
	/*
	 * RECONOCE QUE ESTE CAMPO DE LA BD ES AUTO INCREMENTAR
	 * import javax.persistence.GeneratedValue;
	 * import javax.persistence.GenerationType;
	 * 
	 * @GeneratedValue(strategy=GenerationType.IDENTITY)
	*/
	
	//RECONOCE QUE EL ID es el id a la tabla que se mapea el objeto
	@Id
	private String documento;
	
	//CAMPOS DE LA BD
	@Column
	private String nombres;
	@Column
	private String apellidos;
	@Column
	private String cargo;
	@Column
	private int salario;
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	@Override
	public String toString(){
		return "Empleado [id=" + documento + ", nombre=" + nombres + ", apellidos" + apellidos + 
				", cargo=" + cargo + ", salario=" + salario + "]";
	}
}
