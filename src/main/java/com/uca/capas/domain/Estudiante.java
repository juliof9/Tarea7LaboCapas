package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="estudiante")
public class Estudiante {
	
	@Id
	@Column(name="c_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="estudiante_c_usuario_seq")
	private Integer c_usuario;
	
	@Column(name="nombre")
	@Size(min=4, max=25, message="El campo no puede quedar vacio y maximo de 25 caracteres")
	private String nombre;
	
	@Size(min=4, max=30, message ="El campo no puede quedar vacio y maximo de 30 caracteres")
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="carne")
	@Size(min=8, max=8, message="El campo no puede quedar vacio y 8 digitos exactos")
	private String carne;
	
	@Column(name="carrera")
	@Size(min=10, max=50, message="El campo no debe quedar vacio")
	private String carrera;	
	
	public Estudiante(){}
	
	public Integer getC_usuario() {
		return c_usuario;
	}
	public void setC_usuario(Integer c_usuario) {
		this.c_usuario = c_usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCarne() {
		return carne;
	}
	public void setCarne(String carne) {
		this.carne = carne;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

}
