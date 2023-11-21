package fei.upce.adandldap.workWithUser;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;


/*
call it + modify it
        DeleteUser deleteUser = new DeleteUser();

        // Set your LDAP configuration parameters here
        String userDN = "uid=newuser,ou=people,dc=fei,dc=upce";
        String userPassword = "userpassword";

        deleteUser.deleteUserFromLDAP(userDN);
 */
public class DeleteUser {
    public void deleteUserFromLDAP(String userDN) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=fei,dc=upce");
        env.put(Context.SECURITY_CREDENTIALS, "adminpassword");

        DirContext context = null;

        try {
            context = new InitialDirContext(env);
            context.destroySubcontext(userDN);
            System.out.println("User removed successfully");
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
