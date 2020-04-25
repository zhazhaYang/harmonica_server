package com.springboot.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
