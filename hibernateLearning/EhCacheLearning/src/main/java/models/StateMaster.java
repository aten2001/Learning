package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "StateMaster")
public class StateMaster implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3503369806194113812L;

	public StateMaster() {
    }

    public StateMaster(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sm_id", nullable = false)
    private Integer         id;

    @Column(name = "sm_state_name", nullable = false, length = 256)
    private String          stateName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sm_country_id", nullable = false)
    private CountryMaster   countryMaster;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stateMaster")
    private Set<CityMaster> cityMasters = new HashSet<CityMaster>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public CountryMaster getCountryMaster() {
        return countryMaster;
    }

    public void setCountryMaster(CountryMaster countryMaster) {
        this.countryMaster = countryMaster;
    }

    public Set<CityMaster> getCityMasters() {
        return cityMasters;
    }

    public void setCityMasters(Set<CityMaster> cityMasters) {
        this.cityMasters = cityMasters;
    }

    public String getDescriptiveName() {
        return "State";
    }

    

}
