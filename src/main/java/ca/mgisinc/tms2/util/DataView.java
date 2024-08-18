package ca.mgisinc.tms2.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class DataView {
	
	private List<Object> data;
	
	public DataView(List<Object> userList) {
		this.data = userList;
	}
	
}
