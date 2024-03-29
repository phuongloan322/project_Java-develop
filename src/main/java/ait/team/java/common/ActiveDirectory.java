package ait.team.java.common;

import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Query Active Directory using Java
 * 
 * @filename ActiveDirectory.java
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
 * @copyright &copy; 2010-2012 www.myjeeva.com
 */
public class ActiveDirectory {
    // Logger
    private static final Logger LOG = Logger.getLogger(ActiveDirectory.class.getName());

    //required private variables   
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    private String[] returnAttributes = { "sAMAccountName", "givenName", "cn", "mail" };
    private String domainBase;
    private String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

    /**
     * constructor with parameter for initializing a LDAP context
     * 
     * @param username a {@link java.lang.String} object - username to establish a LDAP connection
     * @param password a {@link java.lang.String} object - password to establish a LDAP connection
     * @param domainController a {@link java.lang.String} object - domain controller name for LDAP connection
     */
    @SuppressWarnings("unchecked")
	public ActiveDirectory(String username, String password, String domainController) {
        properties = new Properties();        

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
        properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
        properties.put(Context.SECURITY_CREDENTIALS, password);
//        @SuppressWarnings("rawtypes")
//		Hashtable env = new Hashtable();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, 
//            "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL, "ldap://" + domainController);
//        env.put(Context.SECURITY_AUTHENTICATION, "simple");
//        env.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
//        env.put(Context.SECURITY_CREDENTIALS, password);
//        initializing active directory LDAP connection
        try {
            dirContext = new InitialDirContext(properties);
        } catch (NamingException e) {
            LOG.severe(e.getMessage());
        }
        
        //default domain base for search
        domainBase = getDomainBase(domainController);
        
        //initializing search controls
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnAttributes);

    }
    
    public boolean checkConnect(String username, String password, String domainController) {
    	properties = new Properties();        

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
        properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
        
        //initializing active directory LDAP connection
        try {
            dirContext = new InitialDirContext(properties);
            return true;
        } catch (NamingException e) {
            LOG.severe(e.getMessage());
        }
    	return false;
    }
    
    /**
     * search the Active directory by username/email id for given search base
     * 
     * @param searchValue a {@link java.lang.String} object - search value used for AD search for eg. username or email
     * @param searchBy a {@link java.lang.String} object - scope of search by username or by email id
     * @param searchBase a {@link java.lang.String} object - search base value for scope tree for eg. DC=myjeeva,DC=com
     * @return search result a {@link javax.naming.NamingEnumeration} object - active directory search result
     * @throws NamingException
     */
    public NamingEnumeration<SearchResult> searchUser(String searchValue, String searchBy, String searchBase) throws NamingException {
        String filter = getFilter(searchValue, searchBy);        
        String base = (null == searchBase) ? domainBase : getDomainBase(searchBase); // for eg.: "DC=myjeeva,DC=com";
        
        return this.dirContext.search(base, filter, this.searchCtls);
    }

    /**
     * closes the LDAP connection with Domain controller
     */
    public void closeLdapConnection(){
        try {
            if(dirContext != null)
                dirContext.close();
        }
        catch (NamingException e) {
            LOG.severe(e.getMessage());            
        }
    }
    
    /**
     * active directory filter string value
     * 
     * @param searchValue a {@link java.lang.String} object - search value of username/email id for active directory
     * @param searchBy a {@link java.lang.String} object - scope of search by username or email id
     * @return a {@link java.lang.String} object - filter string
     */
    private String getFilter(String searchValue, String searchBy) {
        String filter = this.baseFilter;        
        if(searchBy.equals("email")) {
            filter += "(mail=" + searchValue + "))";
        } else if(searchBy.equals("username")) {
            filter += "(samaccountname=" + searchValue + "))";
        }
        return filter;
    }
    
    /**
     * creating a domain base value from domain controller name
     * 
     * @param base a {@link java.lang.String} object - name of the domain controller
     * @return a {@link java.lang.String} object - base name for eg. DC=myjeeva,DC=com
     */
    private static String getDomainBase(String base) {
        char[] namePair = base.toUpperCase().toCharArray();
        String dn = "DC=";
        for (int i = 0; i < namePair.length; i++) {
            if (namePair[i] == '.') {
                dn += ",DC=" + namePair[++i];
            } else {
                dn += namePair[i];
            }
        }
        return dn;
    }
}
