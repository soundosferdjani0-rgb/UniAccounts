import logging.AuditLogger;
import model.StudentAccount;
import repository.AccountRepository;

public class Main {
    public static void main(String[] args) {

        // Test 1 : compte valide
        StudentAccount a = new StudentAccount.Builder("2024001", "Ali Ben", "ali@univ.dz")
                .level("L2")
                .major("Informatique")
                .scholarship(true)
                .addCourse("BDD")
                .addCourse("Algo")
                .build();
        System.out.println(a);

        // Test 2 : email sans @ => erreur
        try {
            new StudentAccount.Builder("2024002", "Sara", "sara.univ.dz").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // Test 3 : level invalide => erreur
        try {
            new StudentAccount.Builder("2024003", "Omar", "omar@univ.dz")
                    .level("L9").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());

        }
        // Test 4 : Singleton => meme instance
        AccountRepository r1 = AccountRepository.getInstance();
        AccountRepository r2 = AccountRepository.getInstance();
        System.out.println("Meme instance ? " + (r1 == r2));

        // Test 5 : ajout + id duplique
        r1.add(a);
        System.out.println("Nombre de comptes (via r2) : " + r2.count());
        try {
            r2.add(a);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());

        }
        // Test 6 : Singleton AuditLogger
        AuditLogger l1 = AuditLogger.getInstance();
        AuditLogger l2 = AuditLogger.getInstance();
        l1.log("Compte cree : " + a.getId());
        System.out.println("Meme logger ? " + (l1 == l2));
        System.out.println("Nombre de logs (via l2) : " + l2.getLogs().size());

        // Test 7 : Prototype
        StudentAccount template = new StudentAccount.Builder("TEMPLATE", "Modele", "t@univ.dz")
                .level("L2").major("Informatique")
                .addCourse("BDD").addCourse("Algo")
                .build();

        StudentAccount s1 = template.cloneWith("2024010", "Yacine", "y@univ.dz");
        StudentAccount s2 = template.cloneWith("2024011", "Nadia", "n@univ.dz");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("Meme objet ? " + (s1 == s2));
    }
}
