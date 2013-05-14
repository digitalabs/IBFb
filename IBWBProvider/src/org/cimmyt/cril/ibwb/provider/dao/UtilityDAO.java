package org.cimmyt.cril.ibwb.provider.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/*
 * used in calling Views, Stored procedures, etc.
 */
public class UtilityDAO extends HibernateDaoSupport {

    private static Logger log = Logger.getLogger(UtilityDAO.class);
    private String accessType;
    
    public UtilityDAO() {
	
    }

    /**
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
    
    @SuppressWarnings("rawtypes")
	public <T> T callStoredProcedureForObject(
    		final T bean, 
            final String procedureName, 
            final String... params) {
        
        final String sql = buildSQLQuery(procedureName, params);
        System.out.println("sql = "+sql);
        Object result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.
                        createSQLQuery(sql);
                query.setResultTransformer(Transformers.aliasToBean(bean.getClass()));
                if(params!=null && params.length>0) {
	                for (String paramName : params) {
	                    try {
	                    	Object obj = PropertyUtils.getProperty(bean,paramName);
	                    	Class type = PropertyUtils.getPropertyType(bean,paramName);
	                    	if(type.getSimpleName().equals("Integer")) {
	                    		query.addScalar(paramName,Hibernate.INTEGER);
	                    	} else if(type.getSimpleName().equals("Double")) {
	                    		query.addScalar(paramName,Hibernate.DOUBLE);
	                    	} else {
	                    		query.addScalar(paramName);
	                    	}
	                    	System.out.println(paramName + " = "+obj);
	                        query.setParameter(paramName, obj);
	                    } catch (IllegalAccessException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (InvocationTargetException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (NoSuchMethodException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    }
	                }
                }
                return query.uniqueResult();
            }
        });
        

        return (T)result;
    }
    
    @SuppressWarnings("rawtypes")
	public List callStoredProcedureForList(
            final Class beanClass,
            final String procedureName, 
            final HashMap parameters) {

        String params[] = new String[parameters.keySet().size()];
        Iterator iter = parameters.keySet().iterator();
        int i = 0 ;
        while(iter.hasNext()){
            params[i++] = (String)iter.next();
        }
        final String sql = buildSQLQuery(procedureName, params);
        System.out.println("sql = "+sql);
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.
                        createSQLQuery(sql);
                query.setResultTransformer(Transformers.aliasToBean(beanClass));
                    if(parameters != null){
                        Iterator iterParam = parameters.keySet().iterator();
                        while(iterParam.hasNext()){
                            String paramName = (String)iterParam.next();
                            Object obj = parameters.get(paramName);
							try {
								Class type = PropertyUtils.getPropertyType(beanClass, paramName);
								if(type.getSimpleName().equals("Integer")) {
		                    		query.addScalar(paramName,Hibernate.INTEGER);
		                    	} else if (type.getSimpleName().equals("Double")) {
		                    		query.addScalar(paramName,Hibernate.DOUBLE);
	                            }else {
		                    		query.addScalar(paramName);
		                    	}
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}
							System.out.println(paramName + " = "+obj);
                            query.setParameter(paramName, obj);
                        }
                }
                return query.list();
            }
        });
        return result;
    }
    
    public <T> List<T> callStoredProcedureForList(
			final T bean,
    		final String procedureName, 
            final String... params) {
    	return callStoredProcedureForListPaged(bean,false,0,0,procedureName,params); 
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> callStoredProcedureForListPaged(
			final T bean,
    		final boolean paged,
    		final int start,
    		final int pageSize,            
            final String procedureName, 
            final String... params) {
        
        final String sql = buildSQLQuery(procedureName, params);
        System.out.println("sql = "+sql);
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.
                        createSQLQuery(sql);
                query.setResultTransformer(Transformers.aliasToBean(bean.getClass()));
                if(params!=null && params.length>0) {
	                for (String paramName : params) {
	                    try {
	                    	Object obj = PropertyUtils.getProperty(bean,paramName);
	                    	Class type = PropertyUtils.getPropertyType(bean,paramName);
	                    	if(type.getSimpleName().equals("Integer")) {
	                    		query.addScalar(paramName,Hibernate.INTEGER);
	                    	} else if(type.getSimpleName().equals("Double")) {
	                    		query.addScalar(paramName,Hibernate.DOUBLE);
	                    	} else {
	                    		query.addScalar(paramName);
	                    	}
	                    	System.out.println(paramName + " = "+obj);
	                        query.setParameter(paramName, obj);
	                    } catch (IllegalAccessException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (InvocationTargetException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (NoSuchMethodException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    }
	                }
                }
                if(paged) {
                	query.setFirstResult(start);
                	query.setMaxResults(pageSize);
                }
                return query.list();
            }
        });
        return result;
    }
    
    public int callStoredProcedureForUpdate(
            final Object bean, 
            final String procedureName, 
            final String... params) {
        final String sql = buildSQLQuery(procedureName, params);
        System.out.println("sql = "+sql);
        int result = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.
                        createSQLQuery(sql);
                if(params!=null && params.length>0) {
	                for (String paramName : params) {
	                    try {
	                    	Object obj = PropertyUtils.getProperty(bean,paramName);
	                        query.setParameter(paramName, obj);
	                        System.out.println(paramName+" = "+obj);
	                    } catch (IllegalAccessException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (InvocationTargetException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    } catch (NoSuchMethodException e) {
	                        log.error("error in setting update parameters " + e.getMessage());
	                    }
	                }
                }
                return query.executeUpdate();
            }
        });
        return result;
    }
    
    private String buildSQLQuery(String procedureName, String... params) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL ");
        sql.append(procedureName);
        sql.append("(");
        if(params!=null && params.length>0) {
	        boolean start = true;
	        for (String paramName : params) {
	            if(!start) {
	                    sql.append(", ");
	            } else {
	                    start = false;
	            }
	            sql.append(":");
	            sql.append(paramName);
	        }
        }
        sql.append(")");
       return sql.toString();
    }
    
}