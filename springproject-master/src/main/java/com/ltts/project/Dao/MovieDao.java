package com.ltts.project.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltts.project.model.Member;
import com.ltts.project.model.Movie;

@Repository
public class MovieDao {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private SessionFactory sf;
	
	public boolean InsertMovie(Movie m) {
		boolean b=false;
		Session s=null;
		try {
			s=sf.openSession();
			s.beginTransaction();
			
			s.save(m);
			//System.out.println(st);
			s.getTransaction().commit();
			
		}
		catch(Exception e) {
			System.out.println("Exception "+e);
			b=true;
		}
		/*
		 * finally { sf.close(); }
		 */
		return b;
	}
	
	
	public List<Movie> getAllMovies(){
		 Session session=sf.openSession();
	        session.beginTransaction();
	        
	        List<Movie> li=sf.openSession().createCriteria(Movie.class).list();
	        //List<ProductsModel> product=sessionFactory.openSession().createCriteria(ProductsModel.class).list();
	        
	        session.getTransaction().commit();
	        return li;
	}

}
