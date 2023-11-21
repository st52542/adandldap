package fei.upce.adandldap.workWithUser;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;


/*
call it + modify it
        ModifyUser modifyUser = new ModifyUser();

        // Set your LDAP configuration parameters here
        String userDN = "uid=user1,ou=people,dc=fei,dc=upce";
        String newPassword = "newpassword";

        modifyUser.modifyUserPassword(userDN, newPassword);
 */
public class ModifyUser {
    public void modifyUserPassword(String userDN, String newPassword) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=fei,dc=upce");
        env.put(Context.SECURITY_CREDENTIALS, "adminpassword");

        DirContext context = null;

        try {
            context = new InitialDirContext(env);

            ModificationItem[] mods = new ModificationItem[1];
            Attribute mod = new BasicAttribute("userPassword", newPassword);
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod);

            context.modifyAttributes(userDN, mods);
            System.out.println("Password changed successfully");
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
