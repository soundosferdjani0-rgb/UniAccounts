package repository;

import model.StudentAccount;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Pattern Singleton - role: Singleton
 * Une seule instance du repository pour tout le programme.
 */
public class AccountRepository {

    // L'unique instance
    private static AccountRepository instance;

    // Stockage : cle = id de l'etudiant
    private final Map<String, StudentAccount> accounts = new LinkedHashMap<>();

    // Constructeur prive : interdit "new" depuis l'exterieur
    private AccountRepository() { }

    // Point d'acces global
    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public void add(StudentAccount account) {
        if (accounts.containsKey(account.getId()))
            throw new IllegalArgumentException("id deja existant : " + account.getId());
        accounts.put(account.getId(), account);
    }

    public StudentAccount findById(String id) {
        return accounts.get(id);
    }

    public List<StudentAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public int count() {
        return accounts.size();
    }
}