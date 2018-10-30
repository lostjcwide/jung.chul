package com.websystique.db;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

public class DbConfig extends MapperScannerConfigurer{
	public DbConfig() {
		setBasePackage("/src/main/java/com/websystique/db");
	}
}
