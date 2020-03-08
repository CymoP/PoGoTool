package services;

import data_access.UserDA;

import java.sql.SQLException;
import java.util.List;

/**
 * RoleService is a service class for all Role business logic
 */
public class RoleService {

    private static RoleService instance;
    private List<String> roleList;
    private UserDA userDA = new UserDA();

    private RoleService() throws SQLException {
        createRoleList();
    }

    /**
     * Singleton implementation - If instance exists use that, if not create one
     *
     * @return Role service instance
     */
    public static RoleService getInstance() throws SQLException {
        if (instance == null) {
            instance = new RoleService();
        }

        return instance;
    }

    /**
     * Returns the list of roles
     *
     * @return the list of roles
     */
    public List<String> getRoleList() {
        return roleList;
    }

    private void createRoleList() throws SQLException {
        roleList = userDA.getRoleList();
    }
}
