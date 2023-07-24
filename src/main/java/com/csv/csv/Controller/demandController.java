package com.csv.csv.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csv.csv.CSVHelper.csvHelperDemand;
import com.csv.csv.Model.demandModel;
import com.csv.csv.Model.onHandModel;
import com.csv.csv.Service.demandService;



@RestController
@RequestMapping("/csvDemand")
public class demandController {
	
	@Autowired
	private demandService demandS;

	
	@PostMapping(value="/uploadDemand", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<demandModel> savedemand(@RequestParam("file") MultipartFile file) {
		try {
			demandS.saveMany(csvHelperDemand.csvToDemand(file.getInputStream(), ""));
			return csvHelperDemand.csvToDemand(file.getInputStream(), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/all")
	public List<demandModel> getAlldemands() {
		
		return demandS.recover();
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = "demand.csv";
		InputStreamResource file = new InputStreamResource(csvHelperDemand.load(demandS.recover()));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);

	}
	
	@PostMapping("/saveDemand")
	public List<String> savedemand(@RequestBody demandModel dm) {
		List<String> errors = new ArrayList<String>();
		if (dm.getId() != null) {
			errors.add("¡No se requiere id!");
		}
		if (dm.getPartNo() == null) {
			errors.add("Se requiere Item_Description");
		}
		if (dm.getPartDescription() == null) {
			errors.add("Se requiere Item_Number");
		}
		if (dm.getCategory() == null) {
			errors.add("Se requiere demand");
		}
		if (dm.getMultiplier() <= 0) {
			errors.add("Se requiere demand");
		}
		if (dm.getItemCost() <= 0) {
			errors.add("Se requiere demand");
		}
		if (dm.getMakeOrBuy() == null) {
			errors.add("Se requiere demand");
		}
		if (dm.getItemCost() <= 0) {
			errors.add("Se requiere demand");
		}
		if (dm.getLeadTime() <= 0) {
			errors.add("Se requiere demand");
		}
		if (dm.getSupplyDays() <= 0) {
			errors.add("Se requiere demand");
		}
		if (dm.getGrossDemand() <= 0) {
			errors.add("Se requiere demand");
		}
		if (errors.isEmpty()) {
			demandS.save(dm);
			return errors;
		} else {
			return errors;
		}
	}

}
