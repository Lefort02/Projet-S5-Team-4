import java.sql.Date;
import java.sql.Time;

public class RDV {
	
	private String numberRDV;
	private Doctor doctor;
	private Patient patient;
	private Date date;
	private Time time;
	private String informations;
	
	public RDV(Doctor doctor, Patient patient, Date date, Time time, String informations, String rdnNumber)
	{
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		this.informations = informations;
		this.time = time;
		this.numberRDV= rdnNumber;
		
	}
	
	public String getNumberRDV()
	{
		return this.numberRDV;
	}
	
	public Doctor getDoctor()
	{
		return this.doctor;
	}
	
	public Patient getPatient()
	{
		return this.patient;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public String getInformations()
	{
		return this.informations;
	}
	
	public Time getTime()
	{
		return this.time;
	}
	
	
	public void ToString()
	{
		System.out.println(this.numberRDV + "\n" + this.doctor.getName() + "\n" + this.patient.getName() + "\n" + this.date.toString() + "\n" + this.informations);
	}

}
