package project.denis.spring.boot_security.rest.dao;


import project.denis.spring.boot_security.rest.models.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> getRoles(Long[] userRoles);

    void initRoles();
}
