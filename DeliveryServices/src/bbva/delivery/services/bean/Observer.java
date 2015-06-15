/**
 * $Id$
 * @author rony.villanueva
 * @date   05/09/2013 18:39:55
 *
 * Copyright (C) 2013 Scytl Secure Electronic Voting SA
 *
 * All rights reserved.
 *
 */
package bbva.delivery.services.bean;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Observer implements Serializable {

	private static final long serialVersionUID = 1719025368270873156L;

	 
	private Long id;

	 
	private Long event;

	 
	private String eventName;

	 
	private Person person;

	 
	private String solicitude;

	 
	private String type;

	 
	private String status;

	 
	private String training;

	 
	private String notification;

	 
	private String accreditation;

	 
	private Date enrollDate;

	 
	private Date approvalDate;

	 
	private Date rejectionDate;

	 
	private String responsibleUser;

	 
	private String approvedUser;

	 
	private String rejectedUser;

	 
	private Boolean hasResponsible;

	

	 
	private String notes;

	 
	private String fileNamePassport;

	 
	private String fileTypePassport;

	 
	private String fileTypePhoto;

	 
	private String fileNamePhoto;

	 
	private String fileNameCommitmentLetter;

	 
	private String fileTypeCommitmentLetter;

	 
	private String fileNameCitizenshipCard;

	 
	private String fileTypeCitizenshipCard;

	 
	private String fileTypeVotingCertificate;

	 
	private String fileNameVotingCertificate;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getEvent() {
		return event;
	}

	public void setEvent(final Long event) {
		this.event = event;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(final Person person) {
		this.person = person;
	}


	/**
	 * @return the solicitude
	 */
	public String getSolicitude() {
		return solicitude;
	}

	/**
	 * @param solicitude
	 *            the solicitude to set
	 */
	public void setSolicitude(final String solicitude) {
		this.solicitude = solicitude;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @param training
	 *            the training to set
	 */
	public void setTraining(final String training) {
		this.training = training;
	}

	/**
	 * @return the training
	 */
	public String getTraining() {
		return training;
	}

	/**
	 * @param accreditation
	 *            the accreditation to set
	 */
	public void setAccreditation(final String accreditation) {
		this.accreditation = accreditation;
	}

	/**
	 * @return the accreditation
	 */
	public String getAccreditation() {
		return accreditation;
	}

	/**
	 * @return Returns the enrollDate.
	 */
	public Date getEnrollDate() {
		return enrollDate;
	}

	/**
	 * @param enrollDate
	 *            The enrollDate to set.
	 */
	public void setEnrollDate(final Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	/**
	 * @return Returns the approvalDate.
	 */
	public Date getApprovalDate() {
		return approvalDate;
	}

	/**
	 * @param approvalDate
	 *            The approvalDate to set.
	 */
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * @return Returns the rejectionDate.
	 */
	public Date getRejectionDate() {
		return rejectionDate;
	}

	/**
	 * @param rejectionDate
	 *            The rejectionDate to set.
	 */
	public void setRejectionDate(final Date rejectionDate) {
		this.rejectionDate = rejectionDate;
	}

	/**
	 * @return Returns the responsibleUser.
	 */
	public String getResponsibleUser() {
		return responsibleUser;
	}

	/**
	 * @param responsibleUser
	 *            The responsibleUser to set.
	 */
	public void setResponsibleUser(final String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}

	/**
	 * @return Returns the approvedUser.
	 */
	public String getApprovedUser() {
		return approvedUser;
	}

	/**
	 * @param approvedUser
	 *            The approvedUser to set.
	 */
	public void setApprovedUser(final String approvedUser) {
		this.approvedUser = approvedUser;
	}

	/**
	 * @return Returns the rejectedUser.
	 */
	public String getRejectedUser() {
		return rejectedUser;
	}

	/**
	 * @param rejectedUser
	 *            The rejectedUser to set.
	 */
	public void setRejectedUser(final String rejectedUser) {
		this.rejectedUser = rejectedUser;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(final String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @param hasResponsible
	 *            the hasResponsible to set
	 */
	public void setHasResponsible(final Boolean hasResponsible) {
		this.hasResponsible = hasResponsible;
	}

	/**
	 * @return the hasResponsible
	 */
	public Boolean getHasResponsible() {
		return hasResponsible;
	}


	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            The notes to set.
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(final String notification) {
		this.notification = notification;
	}

	/**
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * @return Returns the fileNamePassport.
	 */
	public String getFileNamePassport() {
		return fileNamePassport;
	}

	/**
	 * @param fileNamePassport
	 *            The fileNamePassport to set.
	 */
	public void setFileNamePassport(final String fileNamePassport) {
		this.fileNamePassport = fileNamePassport;
	}

	/**
	 * @return Returns the fileTypePassport.
	 */
	public String getFileTypePassport() {
		return fileTypePassport;
	}

	/**
	 * @param fileTypePassport
	 *            The fileTypePassport to set.
	 */
	public void setFileTypePassport(final String fileTypePassport) {
		this.fileTypePassport = fileTypePassport;
	}

	/**
	 * @return Returns the fileNamePhoto.
	 */
	public String getFileNamePhoto() {
		return fileNamePhoto;
	}

	/**
	 * @param fileNamePhoto
	 *            The fileNamePhoto to set.
	 */
	public void setFileNamePhoto(final String fileNamePhoto) {
		this.fileNamePhoto = fileNamePhoto;
	}

	/**
	 * @return Returns the fileTypePhoto.
	 */
	public String getFileTypePhoto() {
		return fileTypePhoto;
	}

	/**
	 * @param fileTypePhoto
	 *            The fileTypePhoto to set.
	 */
	public void setFileTypePhoto(final String fileTypePhoto) {
		this.fileTypePhoto = fileTypePhoto;
	}

	/**
	 * @return Returns the fileNameCommitmentLetter.
	 */
	public String getFileNameCommitmentLetter() {
		return fileNameCommitmentLetter;
	}

	/**
	 * @param fileNameCommitmentLetter
	 *            The fileNameCommitmentLetter to set.
	 */
	public void setFileNameCommitmentLetter(final String fileNameCommitmentLetter) {
		this.fileNameCommitmentLetter = fileNameCommitmentLetter;
	}

	/**
	 * @return Returns the fileTypeCommitmentLetter.
	 */
	public String getFileTypeCommitmentLetter() {
		return fileTypeCommitmentLetter;
	}

	/**
	 * @param fileTypeCommitmentLetter
	 *            The fileTypeCommitmentLetter to set.
	 */
	public void setFileTypeCommitmentLetter(final String fileTypeCommitmentLetter) {
		this.fileTypeCommitmentLetter = fileTypeCommitmentLetter;
	}

	/**
	 * @return Returns the fileNameCitizenshipCard.
	 */
	public String getFileNameCitizenshipCard() {
		return fileNameCitizenshipCard;
	}

	/**
	 * @param fileNameCitizenshipCard
	 *            The fileNameCitizenshipCard to set.
	 */
	public void setFileNameCitizenshipCard(final String fileNameCitizenshipCard) {
		this.fileNameCitizenshipCard = fileNameCitizenshipCard;
	}

	/**
	 * @return Returns the fileTypeCitizenshipCard.
	 */
	public String getFileTypeCitizenshipCard() {
		return fileTypeCitizenshipCard;
	}

	/**
	 * @param fileTypeCitizenshipCard
	 *            The fileTypeCitizenshipCard to set.
	 */
	public void setFileTypeCitizenshipCard(final String fileTypeCitizenshipCard) {
		this.fileTypeCitizenshipCard = fileTypeCitizenshipCard;
	}

	/**
	 * @return Returns the fileNameVotingCertificate.
	 */
	public String getFileNameVotingCertificate() {
		return fileNameVotingCertificate;
	}

	/**
	 * @param fileNameVotingCertificate
	 *            The fileNameVotingCertificate to set.
	 */
	public void setFileNameVotingCertificate(final String fileNameVotingCertificate) {
		this.fileNameVotingCertificate = fileNameVotingCertificate;
	}

	/**
	 * @return Returns the fileTypeVotingCertificate.
	 */
	public String getFileTypeVotingCertificate() {
		return fileTypeVotingCertificate;
	}

	/**
	 * @param fileTypeVotingCertificate
	 *            The fileTypeVotingCertificate to set.
	 */
	public void setFileTypeVotingCertificate(final String fileTypeVotingCertificate) {
		this.fileTypeVotingCertificate = fileTypeVotingCertificate;
	}

}
