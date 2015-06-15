/**
 *  $id$
 *
 *  @author rony.villanueva
 *  @date   10/09/2013 05:18:31    
 *
 *  Copyright (C) 2013 Scytl Secure Electronic Voting SA
 *
 *  All rights reserved.
 *
 */
package bbva.delivery.services.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Person implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = -5540634760537962672L;

	 
	private Long id;

	 
	private String name;

	 
	private String surname;

	 
	private String citizenshipCard;

	 
	private String passport;

	 
	private String kindPassport;

	 
	private Integer age;

	
	private String genre;

	 
	private String howApply;

	 
	private String kindPerson;

	 
	private String landline;

	 
	private String cellPhone;

	 
	private String email;

	 
	private String institutionalEmail;

	 
	private String country;

	 
	private String province;

	 
	private String city;

	 
	private String address;
	
	 
	private String currentOcupation;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the surname.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            The surname to set.
	 */
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	/**
	 * @return Returns the passport.
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * @param passport
	 *            The passport to set.
	 */
	public void setPassport(final String passport) {
		this.passport = passport;
	}

	/**
	 * @return Returns the age.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            The age to set.
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * @return Returns the kindPerson.
	 */
	public String getKindPerson() {
		return kindPerson;
	}

	/**
	 * @param kindPerson
	 *            The kindPerson to set.
	 */
	public void setKindPerson(final String kindPerson) {
		this.kindPerson = kindPerson;
	}

	/**
	 * @return Returns the landline.
	 */
	public String getLandline() {
		return landline;
	}

	/**
	 * @param landline
	 *            The landline to set.
	 */
	public void setLandline(final String landline) {
		this.landline = landline;
	}

	/**
	 * @return Returns the cellPhone.
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone
	 *            The cellPhone to set.
	 */
	public void setCellPhone(final String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            The address to set.
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * @return Returns the citizenshipCard.
	 */
	public String getCitizenshipCard() {
		return citizenshipCard;
	}

	/**
	 * @param citizenshipCard
	 *            The citizenshipCard to set.
	 */
	public void setCitizenshipCard(final String citizenshipCard) {
		this.citizenshipCard = citizenshipCard;
	}

	/**
	 * @return Returns the genre.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            The genre to set.
	 */
	public void setGenre(final String genre) {
		this.genre = genre;
	}

	/**
	 * @return Returns the howApply.
	 */
	public String getHowApply() {
		return howApply;
	}

	/**
	 * @param howApply
	 *            The howApply to set.
	 */
	public void setHowApply(final String howApply) {
		this.howApply = howApply;
	}

	/**
	 * @param institutionalEmail
	 *            The institutionalEmail to set.
	 */
	public void setInstitutionalEmail(final String institutionalEmail) {
		this.institutionalEmail = institutionalEmail;
	}

	/**
	 * @return Returns the institutionalEmail.
	 */
	public String getInstitutionalEmail() {
		return institutionalEmail;
	}

	/**
	 * @param kindPassport
	 *            The kindPassport to set.
	 */
	public void setKindPassport(final String kindPassport) {
		this.kindPassport = kindPassport;
	}

	/**
	 * @return Returns the kindPassport.
	 */
	public String getKindPassport() {
		return kindPassport;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            The province to set.
	 */
	public void setProvince(final String province) {
		this.province = province;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param currentOcupation
	 *            the currentOcupation to set
	 */
	public void setCurrentOcupation(String currentOcupation) {
		this.currentOcupation = currentOcupation;
	}

	/**
	 * @return the currentOcupation
	 */
	public String getCurrentOcupation() {
		return currentOcupation;
	}

}
