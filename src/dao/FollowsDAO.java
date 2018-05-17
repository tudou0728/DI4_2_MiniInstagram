package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Follows;

public class FollowsDAO 
{
	public List<Follows> getAll(EntityManager entityManager)
	{
		String sql="from Follows follows";
		Query query=entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public Follows create(EntityManager entityManager,Follows follow)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(follow);
		entityManager.getTransaction().commit();
		return follow;
	}
	
	public void delete(EntityManager entityManager,Follows follow)
	{
		entityManager.getTransaction().begin();
		entityManager.remove(follow);
		entityManager.getTransaction().commit();
	}
	
	//根据用户id 查找该用户的拥护者
	public List<Follows> getFollowersOfUser(EntityManager entityManager,int uId)
	{
		String sql="select follows from Follows follows where follows.follow.uId=:uId";
		Query query=entityManager.createQuery(sql);
		query.setParameter("uId", uId);
		return query.getResultList();
	}
	
	//根据用户id 查找该用户follow的人
	public List<Follows> getFollowsOfUser(EntityManager entityManager,int uId)
	{
		String sql="select follows from Follows follows where follows.follower.uId=:uId";
		Query query=entityManager.createQuery(sql);
		query.setParameter("uId", uId);
		return query.getResultList();
	}
	
	public List<Follows> getFollow(EntityManager entityManager,int uFollowId,int uFollowerId)
	{
		String sql="select follows from Follows follows where follows.follow.uId=:uFollowId and follows.follower.uId=:uFollowerId";
		Query query=entityManager.createQuery(sql);
		query.setParameter("uFollowId", uFollowId);
		query.setParameter("uFollowerId", uFollowerId);
		return query.getResultList();
	}
}
