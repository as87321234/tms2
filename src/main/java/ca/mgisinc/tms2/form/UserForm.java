package ca.mgisinc.tms2.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserForm {

    private String firstname;
    private String username;
    private String password;
    private Boolean enabled;

}
