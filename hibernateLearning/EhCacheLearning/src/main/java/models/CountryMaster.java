package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "CountryMaster")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "CountryMaster")
public class CountryMaster implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryMaster() {
    }

    public CountryMaster(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_id", nullable = false)
    private Integer          id;

    @Column(name = "cm_country_name", nullable = false, length = 256)
    private String           name;

    @Column(name = "cm_country_code", nullable = false, length = 2)
    private String           code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster")
    private Set<CityMaster>  cityMasters  = new HashSet<CityMaster>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "countryMaster")
    private Set<StateMaster> stateMasters = new HashSet<StateMaster>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<CityMaster> getCityMasters() {
        return cityMasters;
    }

    public void setCityMasters(Set<CityMaster> cityMasters) {
        this.cityMasters = cityMasters;
    }

    public Set<StateMaster> getStateMasters() {
        return stateMasters;
    }

    public void setStateMasters(Set<StateMaster> stateMasters) {
        this.stateMasters = stateMasters;
    }

    public String getDescriptiveName() {
        return "Country";
    }
}
