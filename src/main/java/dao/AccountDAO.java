package dao;

import model.Account;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountDAO extends BaseDAO<Account> {

    public AccountDAO(EntityManager em) {
        super(em);
    }

    @Override
    public boolean Add(Account element){
        try{
            _em.getTransaction().begin();
            _em.persist(element);
            _em.getTransaction().commit();
            return true;
        }catch (Exception e){
            if (_em.getTransaction().isActive()){
                _em.getTransaction().rollback();
            }
            return false;
        }
        finally {
            _em.close();
        }
    }

    @Override
    public boolean delete(int id) {
        try{
            Account account = _em.find(Account.class,id);
            if(account != null){
                _em.getTransaction().begin();
                _em.remove(account);
                _em.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if (_em.getTransaction().isActive()){
                _em.getTransaction().rollback();
            }
            return false;
        }
        finally {
            _em.close();
        }
    }

    @Override
    public Account findById(int id){
        Account account = null;
        account = _em.find(Account.class,id);
        _em.close();
        return account;
    }

    @Override
    public List<Account> findAll(){
        List<Account> accounts = null;
        accounts = _em.createQuery("select a from Account a", Account.class).getResultList();
        _em.close();
        return accounts;
    }
}
