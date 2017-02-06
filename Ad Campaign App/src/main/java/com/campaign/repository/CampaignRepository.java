package com.campaign.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;
import com.campaign.domain.CampaignDetails;

@Repository
public class CampaignRepository {

	HashMap<String, List<CampaignDetails>> hmCampaign = new HashMap<String, List<CampaignDetails>>();
	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	public HashMap<String, List<CampaignDetails>> getAllCampaigns (){
		return hmCampaign;
	}

	public void addCampaignDetails(CampaignDetails campaigndetail){
		DateTime dateTime = new DateTime();

		campaigndetail.setStartTime(formatter.print(dateTime));
		campaigndetail.setEndTime(formatter.print(dateTime.plusSeconds(campaigndetail.getDuration())));
		if(hmCampaign.containsKey(campaigndetail.getPartner_id())){
			System.out.println("Existing Campaign");
			List<CampaignDetails> lsExistingList = (LinkedList<CampaignDetails>) hmCampaign.get(campaigndetail.getPartner_id());
			lsExistingList.add(campaigndetail);
			hmCampaign.put(campaigndetail.getPartner_id(), lsExistingList);
		} else {
			System.out.println("New Campaign");
			List<CampaignDetails> lsNewList = new LinkedList<CampaignDetails>();
			lsNewList.add(campaigndetail);
			hmCampaign.put(campaigndetail.getPartner_id(), lsNewList);
		}
	}

	public List<Object> getpartnerCampaignDetails (String partner_id){

		List<Object> activeCmpaign = new LinkedList<Object>();
		System.out.println("partnerdetails" + partner_id);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dateTime2 = new DateTime();
		if(hmCampaign.containsKey(partner_id)){
			List<CampaignDetails> lsPartnerCampaign = (LinkedList<CampaignDetails>) hmCampaign.get(partner_id); ;


			for(int i=0;i<lsPartnerCampaign.size();i++){
				CampaignDetails cmdetails = (CampaignDetails) lsPartnerCampaign.get(i);
				dateTime2 = formatter.parseDateTime(formatter.print(dateTime2));
				DateTime endtime = formatter.parseDateTime(cmdetails.getEndTime());
				System.out.println("datetime Campaign"+formatter.print(dateTime2));
				System.out.println("End Time Campaign"+formatter.print(endtime));

				if(dateTime2.isBefore(endtime)){
					System.out.println("ACtive Campaign");
					activeCmpaign.add(cmdetails);
				} 
			}

			if(activeCmpaign.isEmpty()){
				activeCmpaign.add("No Active Cmpaigns for this Partner");
			} 
		} else{
			activeCmpaign.add("No Partner is availalbe");
		}

		return activeCmpaign;
	}
}
