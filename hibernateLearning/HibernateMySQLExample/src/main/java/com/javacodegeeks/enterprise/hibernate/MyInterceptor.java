package com.javacodegeeks.enterprise.hibernate;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1250282841537284638L;

	@Override
	public void onDelete(Object entity, Serializable id,
			Object[] state, String[] propertyNames, Type[] types){
			System.out.println("delete event");
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] prevState, String[] propertyNames,
			Type[] types){
		if(entity instanceof Student){
			System.out.println("Student update operation");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onLoad(Object entity, Serializable id, 
			Object[] state, String[] propertyNames, Type[] types){
		System.out.println("Load operation");
		return true;
	}
	
	@Override
	public boolean onSave(Object entity, Serializable id, 
			Object[] state, String[] propertyNames, Type[] types){
		if(entity instanceof Student){
			System.out.println("Student Create operation");
			return true;
		}
		return false;
	}
	
	@Override
	public void preFlush(Iterator itr){
		System.out.println("call before commiting to Db");
	}
	
	@Override
	public void postFlush(Iterator itr){
		System.out.println("called aster commiting to DB");
	}
}
