package fei.upce.adandldap.workWithUser;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;


/*
call it + modify it
        AddUser addUser = new AddUser();

        // Set your LDAP configuration parameters here
        String userDN = "uid=newuser,ou=people,dc=fei,dc=upce";
        String userPassword = "userpassword";

        addUser.addUserToLDAP(userDN, userPassword);
 */
public class AddUser {
    public void addUserToLDAP(String userDN, String userPassword) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=fei,dc=upce");
        env.put(Context.SECURITY_CREDENTIALS, "adminpassword");

        DirContext context = null;

        try {
            context = new InitialDirContext(env);

            Attributes attributes = new BasicAttributes();
            Attribute objClass = new BasicAttribute("objectClass");
            objClass.add("inetOrgPerson");
            attributes.put(objClass);
            attributes.put(new BasicAttribute("uid", "newuser"));
            attributes.put(new BasicAttribute("cn", "New User"));
            attributes.put(new BasicAttribute("sn", "User"));
            attributes.put(new BasicAttribute("userPassword", userPassword));

            context.createSubcontext(userDN, attributes);
            System.out.println("User added successfully");
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
