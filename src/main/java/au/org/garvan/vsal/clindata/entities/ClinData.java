package au.org.garvan.vsal.clindata.entities;

import java.io.Serializable;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
@Entity
@Table(name="clinicalData")
public class ClinData implements Serializable {

	private static final long serialVersionUID = -8039686696076337074L;

	@Id
	@Column(name="id")
	private String id;

    @Column(name="SampleId")
    private String SampleId;

	@Column(name="gender")
	private String gender;

	/* year of birth */
	@Column(name="yob")
	private int yob;
	
	/* systolic blood pressure, mean */
	@Column(name="sbp")
	private int sbp;
	
	/* height, m */
	@Column(name="height_m")
	private float height;

	/* weight, kg */
	@Column(name="weight_kg")
	private float weight;

	/* ?? */
	@Column(name="AbdoCirc_cms")
	private int abdoCirc;

	/* ?? */
	@Column(name="Glc_mmolL")
	private float glc;

	@Column(name="amd")
	private String amd;

	public ClinData(){}

    public ClinData(String id, String sampleId, String gender, int yob, int sbp, float height, float weight, int abdoCirc, float glc, String amd) {
        this.id = id;
        SampleId = sampleId;
        this.gender = gender;
        this.yob = yob;
        this.sbp = sbp;
        this.height = height;
        this.weight = weight;
        this.abdoCirc = abdoCirc;
        this.glc = glc;
        this.amd = amd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleId() {
        return SampleId;
    }

    public void setSampleId(String sampleId) {
        SampleId = sampleId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public int getSbp() {
        return sbp;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAbdoCirc() {
        return abdoCirc;
    }

    public void setAbdoCirc(int abdoCirc) {
        this.abdoCirc = abdoCirc;
    }

    public float getGlc() {
        return glc;
    }

    public void setGlc(float glc) {
        this.glc = glc;
    }

    public String getAmd() {
        return amd;
    }

    public void setAmd(String amd) {
        this.amd = amd;
    }
}
