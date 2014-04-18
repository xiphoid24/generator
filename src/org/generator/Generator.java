package org.generator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.org.apache.bcel.*;

public class Generator implements Serializable {

	private JCodeModel cm;
	private JDefinedClass dc;
	private String packageName;
	private ClassMap clazz;

	public Generator(String packageName) {
		this.clazz = new ClassMap();
		this.cm = new JCodeModel();
		this.packageName = packageName;
	}
	
	
	
	public void initClass(String className) {
		try {
			this.dc = cm._class("src."+this.packageName+"."+className);
		} catch (JClassAlreadyExistsException e) {
			System.err.println("Class already exists");
		}
		try {
			cm.build(new File("."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void printClassMap() {
		clazz.stringify();
	}
	

	public void domainClass(String className, Map<String, String> fields) {
		try {
			this.dc = cm._class(this.packageName+".domain."+className);
		} catch (JClassAlreadyExistsException e) {
			System.err.println("Class already exists");
		}
		dc.annotate(javax.persistence.Entity.class);
		JFieldVar id = dc.field(JMod.PRIVATE, clazz.get("Long"), "id");
		id.annotate(javax.persistence.Id.class);
		JAnnotationUse idGen = id.annotate(javax.persistence.GeneratedValue.class);
		idGen.param("strategy", javax.persistence.GenerationType.AUTO);
		dc.constructor(JMod.PUBLIC);
		JMethod constructor = dc.constructor(JMod.PUBLIC);
		for (Map.Entry<String, String> field : fields.entrySet()) {
			Class varClass = clazz.get(field.getValue());
			if (varClass.equals(void.class)) {
				dc.field(JMod.PRIVATE, String.class, field.getKey()).javadoc().add("type could not be determined");
			} else {
				JFieldVar var = dc.field(JMod.PRIVATE, varClass, field.getKey());
				JMethod getter = dc.method(JMod.PUBLIC, varClass, getMethodSignature("get", field.getKey()));
				getter.body()._return(var);
				JMethod setter = dc.method(JMod.PUBLIC, Void.TYPE, getMethodSignature("set", field.getKey()));
				setter.param(varClass, field.getKey());
				setter.body().assign(JExpr._this().ref(field.getKey()), JExpr.ref(field.getKey()));
				constructor.param(varClass, field.getKey());
				constructor.body().assign(JExpr._this().ref(field.getKey()), JExpr.ref(field.getKey()));
			}
		}
	}
	
	public void daoClass(String className, Map<String, String> fields) {
		try {
			this.dc = cm._class(this.packageName+".data."+className+"Dao");
		} catch (JClassAlreadyExistsException e) {
			System.err.println("Class already exists");
		}
		dc.annotate(javax.ejb.Local.class);
		JFieldVar id = dc.field(JMod.PRIVATE, dc, "id");
		id.annotate(javax.persistence.Id.class);
		JAnnotationUse idGen = id.annotate(javax.persistence.GeneratedValue.class);
		idGen.param("strategy", javax.persistence.GenerationType.AUTO);
		dc.constructor(JMod.PUBLIC);
		JMethod constructor = dc.constructor(JMod.PUBLIC);
		for (Map.Entry<String, String> field : fields.entrySet()) {
			Class varClass = clazz.get(field.getValue());
			if (varClass.equals(void.class)) {
				dc.field(JMod.PRIVATE, String.class, field.getKey()).javadoc().add("type could not be determined");
			} else {
				JFieldVar var = dc.field(JMod.PRIVATE, varClass, field.getKey());
				JMethod getter = dc.method(JMod.PUBLIC, varClass, getMethodSignature("get", field.getKey()));
				getter.body()._return(var);
				JMethod setter = dc.method(JMod.PUBLIC, Void.TYPE, getMethodSignature("set", field.getKey()));
				setter.param(varClass, field.getKey());
				setter.body().assign(JExpr._this().ref(field.getKey()), JExpr.ref(field.getKey()));
				constructor.param(varClass, field.getKey());
				constructor.body().assign(JExpr._this().ref(field.getKey()), JExpr.ref(field.getKey()));
			}
		}
	}
	
	private String getMethodSignature(String prefix, String fieldName) {
		return prefix+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	
	public void build() {
		try {
			cm.build(new File("."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
