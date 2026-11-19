package logging;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern Singleton - role: Singleton
 * Un seul journal d'audit pour tout le programme.
 */
public class AuditLogger {

    // L'unique instance
    private static AuditLogger instance;

    // Journal : liste des messages
    private final List<String> logs = new ArrayList<>();

    // Constructeur prive
    private AuditLogger() { }

    // Point d'acces global
    public static AuditLogger getInstance() {
        if (instance == null) {
            instance = new AuditLogger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("[AUDIT] " + message);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}