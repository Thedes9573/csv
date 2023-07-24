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


import com.csv.csv.CSVHelper.csvHelperTarget;
import com.csv.csv.Model.targetModel;
import com.csv.csv.Model.onHandModel;
import com.csv.csv.Service.targetService;



@RestController
@RequestMapping("/csvTarget")
public class targetController {
	
	@Autowired
	private targetService targetS;

	
	@PostMapping(value="/uploadTarget", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<targetModel> saveTarget(@RequestParam("file") MultipartFile file) {
		try {
			targetS.saveMany(csvHelperTarget.csvToTarget(file.getInputStream(), ""));
			return csvHelperTarget.csvToTarget(file.getInputStream(), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/all")
	public List<targetModel> getAllTargets() {
		
		return targetS.recover();
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = "target.csv";
		InputStreamResource file = new InputStreamResource(csvHelperTarget.load(targetS.recover()));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);

	}
	
	@PostMapping("/saveTarget")
	public List<String> saveTarget(@RequestBody targetModel im) {
		List<String> errors = new ArrayList<String>();
		if (im.getId() != null) {
			errors.add("¡No se requiere id!");
		}
		if (im.getItemDescription() == null) {
			errors.add("Se requiere Item_Description");
		}
		if (im.getItemNumber() == null) {
			errors.add("Se requiere Item_Number");
		}
		if (im.getTarget() <= 0) {
			errors.add("Se requiere Target");
		}
		if (errors.isEmpty()) {
			targetS.save(im);
			return errors;
		} else {
			return errors;
		}
	}

}
