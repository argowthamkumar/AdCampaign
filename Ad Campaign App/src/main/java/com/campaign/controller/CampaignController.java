package com.campaign.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.campaign.domain.CampaignDetails;
import com.campaign.repository.CampaignRepository;

@RestController
@RequestMapping(value="/ad")
public class CampaignController {

	@Autowired 
	CampaignRepository campaignRepository;

	@RequestMapping(value="/allCampaigns", method = RequestMethod.GET, produces="application/json")
	public   HashMap<String, List<CampaignDetails>>  getAllCampaigns () {
		System.out.println("Inside getCampaignDetails");
		return campaignRepository.getAllCampaigns();

	}

	@RequestMapping(value = "",
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	public   void addCampaign (@RequestBody CampaignDetails campaigndetails,
			HttpServletRequest request,HttpServletResponse response) {
		System.out.println("Inside controller");
		campaignRepository.addCampaignDetails(campaigndetails);
	}

	@RequestMapping(value="{partner_id}", method = RequestMethod.GET, produces="application/json")
	public  List<Object> getpartnerCampaignDetails (@PathVariable("partner_id") String partner_id) {
		System.out.println("Inside getpartnerCampaignDetails");
		return campaignRepository.getpartnerCampaignDetails(partner_id);

	}



}
