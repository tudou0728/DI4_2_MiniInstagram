package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Follows;
import model.Publication;

public class FollowsDAO 
{
	public List<Follows> getAll()
	{
		Session session = HibernateUtil.currentSession();
		try {
			String sql="from Follows follows";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Follows create(Follows follow)
	{
		try {
			if(getByFid(follow.getfId())==null)
			{
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(follow);
				transaction.commit();
			}
			return follow;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}
	
	public void delete(Follows follow)
	{
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(follow);
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}
	
	//根据用户id 查找该用户的拥护者
	public List<Follows> getFollowersOfUser(int uId)
	{
		Session session = HibernateUtil.currentSession();
		try {
			String sql="select follows from Follows follows where follows.follow.uId=:uId";
			Query query = session.createQuery(sql);
			query.setParameter("uId", uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	//根据用户id 查找该用户follow的人
	public List<Follows> getFollowsOfUser(int uId)
	{
		Session session = HibernateUtil.currentSession();
		try {
			String sql="select follows from Follows follows where follows.follower.uId=:uId";
			Query query = session.createQuery(sql);
			query.setParameter("uId", uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Follows> getFollow(int uFollowId,int uFollowerId)
	{
		Session session = HibernateUtil.currentSession();
		try {
			String sql="select follows from Follows follows where follows.follow.uId=:uFollowId and follows.follower.uId=:uFollowerId";
			Query query = session.createQuery(sql);
			query.setParameter("uFollowId", uFollowId);
			query.setParameter("uFollowerId", uFollowerId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Publication getByFid(int fId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Publication.class, fId);
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
