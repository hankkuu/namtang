package kosta.namtang.talkbook.model.domain.bill;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class BillKey {

	@Id
	private String billKey;
	@Column(name = "ISSUE_DATE", nullable = false)
	private Timestamp issueDate;
	@Column(name = "CANCEL_DATE", nullable = false)
	private Timestamp cancelDate;
	@Column(name = "UPDATE_DATE", nullable = false)
	private Timestamp updateDate;

}
