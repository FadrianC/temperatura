package temperatura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temperatura")

public class Temperatura {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int temp_id;
	@Column
	private String temp_temper;
    @Column
	private String temp_hum;

	public int getTemp_id() {
		return temp_id;
	}

	public String getTemp_temper() {
		return temp_temper;
	}

	public void setTemp_temper(String temp_temper) {
		this.temp_temper = temp_temper;
	}
        
	public String getTemp_hum() {
		return temp_hum;
	}

	public void setTemp_hum(String temp_hum) {
		this.temp_hum = temp_hum;
	}

}