package ait.team.java.entity;
import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_processing_month", schema = "public")
public class ProcessingMonthEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * x
     */
    @Id
    @Column(name = "processing_month", nullable = false, length = 6)
    private String processingMonth;

	public String getProcessingMonth() {
		return processingMonth;
	}

	public void setProcessingMonth(String processingMonth) {
		this.processingMonth = processingMonth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}