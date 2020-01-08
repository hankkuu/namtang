package kosta.namtang.talkbook.model.domain.bill;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class BillKey {

	@Id
	private String billKey;
	private Date issueDate;
	private Date cancelDate;
	private Date updateDate;

}
