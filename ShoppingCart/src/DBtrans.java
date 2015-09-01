import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import customTools.DBUtils;

import model.Product;

public class DBtrans {

	
	
	public static List<Product> selectProdAll(){
		
		EntityManager em = DBUtils.getEmFactory().createEntityManager();
		
		String jpa_sql = "select p from Product p";   
				
		TypedQuery<Product> mq = em.createQuery(jpa_sql, Product.class);
		List<Product> prods;
		
		try{
			prods = mq.getResultList();
			if(prods==null || prods.isEmpty()){
				prods=null;
			}
		}finally {
			em.close();
		}
		return prods;
		}
	
public static List<Product> selectProdOne(String product_name){
		
		EntityManager em = DBUtils.getEmFactory().createEntityManager();
		
		String jpa_sql = "select p from Product p where p.prodName = :prod_name";   
				
		TypedQuery<Product> mq = em.createQuery(jpa_sql, Product.class);
		
		mq.setParameter("prod_name", product_name);
		List<Product> prods;
		
		try{
			prods = mq.getResultList();
			if(prods==null || prods.isEmpty()){
				prods=null;
			}
		}finally {
			em.close();
		}
		return prods;
		}
	
}
