package org.cimmyt.cril.ibwb.provider.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
    
    public Object callStoredProcedureForObject(
            final Object bean, 
            final String procedureName, 
            final String... params) {
        
        final String sql = buildSQLQuery(procedureName, params);
        Object result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.
                        createSQLQuery(sql).addEntity(bean.getClass());
                if(params!=null && params.length>0) {
	                for (String paramName : params) {
	                    try {
	                        Object obj = PropertyUtils.getProperty(bean,paramName);
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
        return result;
    }
    
    public List callStoredProcedureForListNew(
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
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.
                        createSQLQuery(sql).addEntity(beanClass);
                    if(parameters != null){
                        Iterator iterParam = parameters.keySet().iterator();
                        while(iterParam.hasNext()){
                            String paramName = (String)iterParam.next();
                            Object obj = parameters.get(paramName);
                            query.setParameter(paramName, obj);
                        }

                }
                return query.list();
            }
        });
        return result;
    }
    
    public List callStoredProcedureForListPaged(
    		final boolean paged,
    		final Object bean,
    		final int start,
    		final int pageSize,            
            final String procedureName, 
            final String... params) {
        
        final String sql = buildSQLQuery(procedureName, params);
        List result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.
                        createSQLQuery(sql).addEntity(bean.getClass());
                if(params!=null && params.length>0) {
	                for (String paramName : params) {
	                    try {
	                        Object obj = PropertyUtils.getProperty(bean,paramName);
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