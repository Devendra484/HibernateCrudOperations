package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.lang.reflect.Field;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IllegalArgumentException, IllegalAccessException
    {
    	Employee emp1=new Employee();
    	emp1.setId(5L);
    	emp1.setName("Bob");
    	emp1.setEmail("Bob@Gmail.com");
    	emp1.setSalary(2200.25);
    	
    	Employee emp2=new Employee();
    	emp2.setId(4L);
    	emp2.setName("yash");
    	emp2.setEmail("yash@Gmail.com");
    	emp2.setSalary(2200.25);

    	Configuration config=new Configuration().configure().addAnnotatedClass(Employee.class);
    	SessionFactory sf=config.buildSessionFactory();

    	
    	// save object 1
    	//session1
    	Session session1=sf.openSession();
    	Transaction tx1=session1.beginTransaction();
    	session1.save(emp1);
    	tx1.commit();
    	session1.close();
    	
    	//session2 save object 2
    	Session session2=sf.openSession();
    	Transaction tx2=session2.beginTransaction();
    	session2.save(emp2);
    	tx2.commit();
    	session2.close();
    	
    	//fetch data
    	Session session3=sf.openSession();
    	Transaction tx3=session3.beginTransaction();
    	Employee emp=session3.get(Employee.class, 1L);
    	
    	Field[] fields =emp.getClass().getDeclaredFields();
    	for(Field field:fields) {
    		field.setAccessible(true);
    		Object value=field.get(emp);
    		System.out.println(field.getName()+" :"+value);
    	}
    	tx3.commit();
    	session3.close();
    	
    	//update data
    	Session session4=sf.openSession();
    	Transaction tx4=session4.beginTransaction();
    	emp1.setName("siva");
    	emp1.setEmail("siva@gmail.com");
    	session4.update(emp1);
    	tx4.commit();
    	session4.close();
    	
    	//delete Data
    	Session session5=sf.openSession();
    	Transaction tx5=session5.beginTransaction();
    	session5.remove(emp2);;
    	tx5.commit();
    	session5.close();
    }
    
    
}
