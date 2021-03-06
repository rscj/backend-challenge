package com.invillia.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "store")
@NamedQueries({@NamedQuery(name = "Store.getCountStoreByName", query = "select count(s) from Store s where s.name=:name"), 
	           @NamedQuery(name = "Store.getCountOtherStoresByName", query = "select count(s) from Store s where s.name=:name and s.id <>:id"),
	           @NamedQuery(name = "Store.getStoreById", query = "select s from Store s left join s.addresses where s.id=:id"),
	           @NamedQuery(name = "Store.getStoreCountById", query = "select count(s) from Store s where s.id=:id")})
public class Store {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "The name is required.")
	@Column(name = "name", length = 255, nullable = false, unique = true)
	private String name;	
	@JsonIgnoreProperties("store")
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> addresses;
		
	public Store() {
		
	}
	
	public Store(Long id) {
		super();
		this.id = id;
	}

	public Store(String name) {
		this.name = name;
	}

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
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}