package ca.mgisinc.tms2.tableview;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class UserTableView
{
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private boolean tokenExpired;
    private Set<String> roles;
    private Set<String> privileges;


}
