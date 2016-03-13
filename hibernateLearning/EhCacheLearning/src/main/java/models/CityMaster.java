package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CityMaster")
public class CityMaster implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8988419485542908456L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tm_id", nullable = false)
    private Integer           id;

    @Column(name = "tm_city_name", nullable = false, length = 256)
    private String            cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_state_id")
    private StateMaster       stateMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_country_id", nullable = false)
    private CountryMaster     countryMaster;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public StateMaster getStateMaster() {
        return stateMaster;
    }

    public void setStateMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
    }

    public CountryMaster getCountryMaster() {
        return countryMaster;
    }

    public void setCountryMaster(CountryMaster countryMaster) {
        this.countryMaster = countryMaster;
    }

    public String getDescriptiveName() {
        return "City";
    }

    
}
