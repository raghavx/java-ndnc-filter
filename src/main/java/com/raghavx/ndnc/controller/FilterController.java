package com.raghavx.ndnc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.raghavx.common.constant.ProcessingStatus;
import com.raghavx.common.data.model.UserFile;
import com.raghavx.common.data.service.FileService;
import com.raghavx.common.data.uo.AgregateAnalyticUO;
import com.raghavx.common.data.uo.FilterUO;
import com.raghavx.common.filter.service.FilterService;
import com.raghavx.ndnc.auth.model.User;
import com.raghavx.ndnc.auth.service.UserService;

/**
 * The Class NdncController.
 */
@Controller
public class FilterController {

	/** The dnd phone filter service. */
	@Autowired
	private FilterService filterService;

	/** The userservice. */
	@Autowired
	private UserService userservice;

	/** The file service. */
	@Autowired
	private FileService fileService;
	
	/**
	 * Ndnc filter.
	 *
	 * @param model            the model
	 * @param auth the auth
	 * @return the string
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */

	@GetMapping("/filter")
	public String ndncFilter(Model model, Authentication auth) throws InterruptedException, ExecutionException {
		model.addAttribute("filterUO", new FilterUO());

		User user = (User) auth.getPrincipal();

		user = userservice.findByUsername(user.getUsername());

		List<UserFile> allfiles = fileService.findByUserOrderByUploadedOnDesc(user);

		model.addAttribute("allfiles", allfiles);

		AgregateAnalyticUO agregateAnalyticUO = new AgregateAnalyticUO();
		agregateAnalyticUO.setTotalFiles(allfiles.size());
		long total = 0, totaldnd = 0, totalnondnd = 0;
		for(UserFile uf : allfiles){
			total += uf.getTotalNumbers();
			totaldnd += uf.getDndNumbers();
			totalnondnd += uf.getNonDndNumbers();
		}
		agregateAnalyticUO.setTotalRecords(total);
		agregateAnalyticUO.setTotalDNDRecords(totaldnd);
		agregateAnalyticUO.setTotalNonDndRecords(totalnondnd);
		model.addAttribute("agregateAnalyticUO", agregateAnalyticUO);

		return "filter";
	}

	/**
	 * Ndnc filter.
	 *
	 * @param filterUO the filter UO
	 * @param auth the auth
	 * @return the string
	 */
	@PostMapping("/filter")
	public String ndncFilter(@ModelAttribute("filterUO") FilterUO filterUO, Authentication auth) {

		User user = null;
		if (Objects.nonNull(auth) && auth.isAuthenticated()) {
			user = (User) auth.getPrincipal();
		}

		user = userservice.findById(user.getUserId());

		File rawFile = null;
		try {
			rawFile = File.createTempFile("raw", "data");
			filterUO.getFile().transferTo(rawFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserFile file = new UserFile();
		Date ts = new Date();
		file.setUser(user);
		file.setUploadedOn(ts);
		file.setDndNumbers(0L);
		file.setNonDndNumbers(0L);
		file.setTotalNumbers(0L);
		file.setUpdatedOn(ts);
		file.setStatus(ProcessingStatus.IN_PROGRESS);
		file = fileService.save(file);
		
		filterService.filterFile(file.getFileId(), user.getUserId(), rawFile);

		return "redirect:/filter";
	}

}
