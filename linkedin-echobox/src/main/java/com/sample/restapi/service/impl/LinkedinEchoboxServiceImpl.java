package com.sample.restapi.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.echobox.api.linkedin.client.DefaultLinkedInClient;
import com.echobox.api.linkedin.client.LinkedInClient;
import com.echobox.api.linkedin.client.Parameter;
import com.echobox.api.linkedin.connection.v2.OrganizationConnection;
import com.echobox.api.linkedin.connection.v2.ShareConnection;
import com.echobox.api.linkedin.types.ContentEntity;
import com.echobox.api.linkedin.types.Share;
import com.echobox.api.linkedin.types.ShareContent;
import com.echobox.api.linkedin.types.ShareText;
import com.echobox.api.linkedin.types.organization.Organization;
import com.echobox.api.linkedin.types.request.ShareRequestBody;
import com.echobox.api.linkedin.types.urn.URN;
import com.echobox.api.linkedin.version.Version;
import com.sample.restapi.properties.LinkedinCredProperties;
import com.sample.restapi.service.LinkedinEchoboxService;

@Service
public class LinkedinEchoboxServiceImpl implements LinkedinEchoboxService {

	@Autowired
	private LinkedinCredProperties linkedinCredProperties;

	@Override
	public ResponseEntity<String> createLinkedinShare(String code, String entityType, String id) {
		Share share;
		try {
			ShareConnection shareConnection = new ShareConnection(
					new DefaultLinkedInClient(getAccessToken(code).getAccessToken()));

			ShareRequestBody shareRequestBody = new ShareRequestBody(new URN(entityType, id));

			ShareContent shareContent = new ShareContent();

			ContentEntity contentEntity = new ContentEntity();
			contentEntity.setEntityLocation("https://www.example.com/content.html");
			shareContent.setContentEntities(Arrays.asList(contentEntity));
			shareContent.setTitle("Test Share with Content");
			shareRequestBody.setContent(shareContent);
			shareRequestBody.setSubject("Test share subject");
			ShareText shareText = new ShareText();
			shareText.setText("test share");
			shareRequestBody.setText(shareText);
			share = shareConnection.postShare(shareRequestBody);

			return ResponseEntity.status(HttpStatus.OK)
					.body("Share: " + share + " - " + share.getOriginalShare() + " - " + share.getSubject());
		} catch (GeneralSecurityException | IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}

	}

	@Override
	public ResponseEntity<String> retrieveLinkedinOrganization(String code, String entityType, String id) {
		try {
			OrganizationConnection organizationConnection = new OrganizationConnection(
					new DefaultLinkedInClient(getAccessToken(code).getAccessToken()));

			URN urn = new URN(entityType, id);
			String urnString = "urn:li:" + urn.getEntityType() + ":" + urn.getId();

			Organization organization = organizationConnection.retrieveOrganization(urn,
					Parameter.with("projection", "(elements*(*,roleAssignee~(localizedFirstName, localizedLastName),"
							+ "organizationalTarget~(localizedName)))"));

			return ResponseEntity.status(HttpStatus.OK).body("urn: " + urnString + "\n- Organization: " + organization
					+ "\n- OrganizationLocalizedDescription: " + organization.getLocalizedDescription());
		} catch (GeneralSecurityException | IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}

	}

	@Override
	public ResponseEntity<String> organizationConnectionExamples(String code, String entityType, String id) {
		try {
			OrganizationConnection organizationConnection = new OrganizationConnection(
					new DefaultLinkedInClient(getAccessToken(code).getAccessToken()));

			URN urn = new URN(entityType, id);
			String urnString = "urn:li:" + urn.getEntityType() + ":" + urn.getId();

			// java.lang.UnsupportedOperationException: Operation is not implemented yet.
			// List<Organization> organizations =
			// organizationConnection.searchForOrganizations("weareyper");

			Long organizationFollowerCount = organizationConnection.retrieveOrganizationFollowerCount(urn);

			Organization organization = organizationConnection.retrieveOrganization(new URN(entityType, id),
					Parameter.with("projection", "(elements*(*,roleAssignee~(localizedFirstName, localizedLastName),"
							+ "organizationalTarget~(localizedName)))"));

			return ResponseEntity.status(HttpStatus.OK)
					.body("urn: " + urnString + "\n- Organizations: NaN" + "\n- URN OrganizationFollowerCount: "
							+ organizationFollowerCount + "\n- URN Organization: " + organization);
		} catch (GeneralSecurityException | IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}

	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	private LinkedInClient.AccessToken getAccessToken(String code) {
		DefaultLinkedInClient client = new DefaultLinkedInClient(Version.DEFAULT_VERSION);
		return client.obtainUserAccessToken(linkedinCredProperties.clientId, linkedinCredProperties.clientSecret,
				linkedinCredProperties.redirectUrl, code);
	}

}
