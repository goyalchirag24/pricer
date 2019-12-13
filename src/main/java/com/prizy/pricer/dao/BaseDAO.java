
package com.prizy.pricer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class BaseDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Immediately loads the object using the given class identity
	 *
	 * @param entityClass class for object to load
	 * @param id          unique identifier of persistent object
	 * @return the persistent object
	 */
	public Object get(Class entityClass, Object id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * Immediately loads the object using the given class identity
	 *
	 * @param entityClass class for object to load
	 * @param id          unique identifier of persistent object
	 * @return the persistent object
	 */
	public Object load(Class entityClass, Object id) {
		return entityManager.getReference(entityClass, id);
	}

	/**
	 * Saves the Hibernate Entity.
	 *
	 * @param entity The entity to persist
	 */
	public void save(Object entity) {
		entityManager.persist(entity);
	}

	/**
	 * Delete / Remove the Hibernate Entity instance.
	 *
	 * @param entity The entity instance to remove
	 */
	public void delete(Object entity) {
		entityManager.remove(entity);
	}

	/**
	 * ****** HQL Related - start *******
	 */
	/**
	 * Executes the provided HQL query
	 *
	 * @param hql The query to execute
	 * @return List
	 */
	public List executeQuery(String hql) {
		return executeQueryPaging(hql, null, null);
	}

	/**
	 * Executes the provided HQL query after applying the provided parameter and
	 * returns the result
	 *
	 * @param hql   The query to execute
	 * @param param Query Parameter
	 * @return List
	 */
	public List executeQuery(String hql, Object param) {
		Object[] params = { param };
		return executeQueryPaging(hql, params, null);
	}

	/**
	 * Executes the provided HQL query after applying the provided parameters and
	 * returns the result
	 *
	 * @param hql    The query to execute
	 * @param params Query Parameters
	 * @return List
	 */
	public List executeQuery(String hql, Object[] params) {
		return executeQueryPaging(hql, params, null);
	}

	/**
	 * Executes the provided HQL query after applying the provided paging parameters
	 * and returns the result
	 *
	 * @param hql         The query to execute
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeQueryPaging(String hql, Integer[] pagingParam) {
		return executeQueryPaging(hql, null, pagingParam);
	}

	/**
	 * Executes the provided HQL query after applying the provided parameter and
	 * paging parameters and returns the result
	 *
	 * @param hql         The query to execute
	 * @param param       Query Parameter
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeQueryPaging(String hql, Object param, Integer[] pagingParam) {
		Object[] params = { param };
		return executeQueryPaging(hql, params, pagingParam);
	}

	/**
	 * Executes the provided HQL query after applying the provided parameters and
	 * paging parameters and returns the result
	 *
	 * @param hql         The query to execute
	 * @param params      Query Parameters
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeQueryPaging(String hql, Object[] params, Integer[] pagingParam) {
		Query query = entityManager.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		if (pagingParam != null) {
			query.setFirstResult(pagingParam[0]);
			query.setMaxResults(pagingParam[1]);
		}
		return (List) query.getResultList();
	}

	/**
	 * Executes an update query using the provided hql
	 *
	 * @param hql Query to execute
	 * @return int
	 */
	public int executeUpdate(String hql) {
		return executeUpdatePaging(hql, null, null);
	}

	/**
	 * Executes an update query using the provided hql and query parameter
	 *
	 * @param hql   Query to execute
	 * @param param Query Parameter
	 * @return int
	 */
	public int executeUpdate(String hql, Object param) {
		Object[] params = { param };
		return executeUpdatePaging(hql, params, null);
	}

	/**
	 * Executes an update query using the provided hql and query parameters
	 *
	 * @param hql    Query to execute
	 * @param params the query paramters
	 * @return int
	 */
	public int executeUpdate(String hql, Object[] params) {
		return executeUpdatePaging(hql, params, null);
	}

	/**
	 * Executes an update query using the provided hql and paging parameters
	 *
	 * @param hql         Query to execute
	 * @param pagingParam paging parameters
	 * @return int
	 */
	public int executeUpdatePaging(String hql, Integer[] pagingParam) {
		return executeUpdatePaging(hql, null, pagingParam);
	}

	/**
	 * Executes an update query using the provided hql, query parameter and paging
	 * parameters
	 *
	 * @param hql         Query to execute
	 * @param param       Query Parameter
	 * @param pagingParam paging parameters
	 * @return int
	 */
	public int executeUpdatePaging(String hql, Object param, Integer[] pagingParam) {
		Object[] params = { param };
		return executeUpdatePaging(hql, params, pagingParam);
	}

	/**
	 * Executes an update query using the provided hql, query parameters and paging
	 * parameters
	 *
	 * @param hql         Query to execute
	 * @param params      the query paramters
	 * @param pagingParam paging parameters
	 * @return int
	 */
	public int executeUpdatePaging(String hql, Object[] params, Integer[] pagingParam) {
		Query query = entityManager.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		if (pagingParam != null) {
			query.setFirstResult(pagingParam[0]);
			query.setMaxResults(pagingParam[1]);
		}
		return query.executeUpdate();
	}

	/**
	 * ****** HQL Related - end *******
	 */
	/**
	 * ****** SQL Related - start *******
	 */
	/**
	 * Executes the provided SQL query
	 *
	 * @param sql The query to execute
	 * @return List
	 */
	public List executeSQLQuery(String sql) {
		return executeSQLQueryPaging(sql, null, null);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameter and
	 * returns the result
	 *
	 * @param sql   The query to execute
	 * @param param Query Parameter
	 * @return List
	 */
	public List executeSQLQuery(String sql, Object param) {
		Object[] params = { param };
		return executeSQLQueryPaging(sql, params, null);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameters and
	 * returns the result
	 *
	 * @param sql    The query to execute
	 * @param params Query Parameters
	 * @return List
	 */
	public List executeSQLQuery(String sql, Object[] params) {
		return executeSQLQueryPaging(sql, params, null);
	}

	/**
	 * Executes the provided SQL query after applying the provided paging parameters
	 * and returns the result
	 *
	 * @param sql         The query to execute
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeSQLQueryPaging(String sql, Integer[] pagingParam) {
		return executeSQLQueryPaging(sql, null, pagingParam);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameter and
	 * paging parameters and returns the result
	 *
	 * @param sql         The query to execute
	 * @param param       Query Parameter
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeSQLQueryPaging(String sql, Object param, Integer[] pagingParam) {
		Object[] params = { param };
		return executeSQLQueryPaging(sql, params, pagingParam);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameters and
	 * paging parameters and returns the result
	 *
	 * @param sql         The query to execute
	 * @param params      Query Parameters
	 * @param pagingParam paging parameters
	 * @return List
	 */
	public List executeSQLQueryPaging(String sql, Object[] params, Integer[] pagingParam) {
		Query query = entityManager.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		if (pagingParam != null) {
			query.setFirstResult(pagingParam[0]);
			query.setMaxResults(pagingParam[1]);
		}
		return (List) query.getResultList();
	}

	/**
	 * Executes the provided SQL query
	 *
	 * @param sql The query to execute
	 * @return int
	 */
	public int executeSQLUpdate(String sql) {
		return executeSQLUpdate(sql, null);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameter and
	 * returns the result
	 *
	 * @param sql   The query to execute
	 * @param param Query Parameter
	 * @return int
	 */
	public int executeSQLUpdate(String sql, Object param) {
		Object[] params = { param };
		return executeSQLUpdate(sql, params);
	}

	/**
	 * Executes the provided SQL query after applying the provided parameters and
	 * returns the result
	 *
	 * @param sql    The query to execute
	 * @param params Query Parameters
	 * @return int
	 */
	public int executeSQLUpdate(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.executeUpdate();
	}

	/**
	 * ****** SQL Related - end *******
	 */
}
