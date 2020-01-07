package kosta.namtang.talkbook.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "STAT_SQC")
	@SequenceGenerator(sequenceName = "STAT_SQC", name = "STAT_SQC", allocationSize = 1)
	private int statIdx;
	private int salePrice;
	private String saleDate;
	private long userCount;

}
