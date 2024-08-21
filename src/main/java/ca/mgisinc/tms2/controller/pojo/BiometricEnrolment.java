package ca.mgisinc.tms2.controller.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString

public class BiometricEnrolment implements Serializable {
	
	String biometricId;
	String enrolmentBase64;
	
}
