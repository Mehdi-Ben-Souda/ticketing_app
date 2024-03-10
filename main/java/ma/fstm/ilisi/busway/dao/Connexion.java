package ma.fstm.ilisi.busway.dao;

import org.neo4j.driver.*;

public class Connexion {

    private static Session session;
    private Connexion() {}

    public static Session getSession() {
        if(session == null) {
            try {
                final String dbUri = "bolt://localhost:7687";
                final String dbUser = "neo4j";
                final String dbPassword = "12345678";
                Driver driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));
                session = driver.session(SessionConfig.builder().withDatabase("neo4j").build());
            } catch (Exception e) {
                session = null;
                e.printStackTrace();
            }
        }
        return session;
    }

    public static void closeSession() {
        if(session != null) {
            session.close();
            session = null;
        }
    }

}
