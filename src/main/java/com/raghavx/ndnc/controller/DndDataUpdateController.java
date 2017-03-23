package com.raghavx.ndnc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghavx.common.data.service.DataUpdateService;
import com.raghavx.common.data.uo.RefreshUO;
import com.raghavx.common.data.uo.DataUpdateUO;

/**
 * The Class DndDataUpdateController.
 */
@RestController
public class DndDataUpdateController {
	
	/** The dnd data update service. */
	@Autowired
	private DataUpdateService dndDataUpdateService;
	
	/** The update file path. */
	@Value("${ndnc.update.file.path}")
	private String updateFilePath;
	
	/** The number of update file. */
	@Value("${ndnc.update.file.count}")
	private int numberOfUpdateFile;
	
	/**
	 * Gets the last dnd data update status.
	 *
	 * @return the last dnd data update status
	 */
	@GetMapping("/lastdndupdate")
	public @ResponseBody DataUpdateUO getLastDndDataUpdateStatus() {
		return dndDataUpdateService.getLastDataUpdateStatus();
	}
	
	
	/**
	 * Ndnc refresh.
	 *
	 * @param nDNCRefreshUO the n DNC refresh UO
	 * @return the string
	 */
	@PostMapping("/dndrefresh")
	public String ndncRefresh(@ModelAttribute("nDNCRefreshUO") RefreshUO nDNCRefreshUO) {
		
		dndDataUpdateService.refreshData(nDNCRefreshUO.getFile());
		
		return "redirect:/";
	}
	
	
	/**
	 * Dnd data update.
	 *
	 * @param name the name
	 */
	@GetMapping("/dndupdate/file/{name}")
	public @ResponseBody void dndDataUpdate(@PathVariable("name") String name) {
		dndDataUpdateService.dataUpdate(updateFilePath,name,numberOfUpdateFile);
	}

}
