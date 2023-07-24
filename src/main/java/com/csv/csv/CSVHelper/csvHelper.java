package com.csv.csv.CSVHelper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.csv.csv.Model.targetModel;
import com.csv.csv.Model.onHandModel;
import com.csv.csv.Repository.onHandRepository;
import com.csv.csv.Service.onHandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class csvHelper {
	@Autowired
	private onHandService inventoryS;

	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Subinventory", "Item", "Item_Description", "Customer", "Sanmina_Stock_Locators",
			"Quantity", "Target_Cost", "Extended_Target_Cost", "STD_Cost", "Extended_STD_Cost" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<onHandModel> csvToSalesOrders(InputStream is, String localhost) {

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<onHandModel> inventories = new ArrayList<onHandModel>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//			date = calendar.getTimeInMillis();

			for (CSVRecord csvRecord : csvRecords) {
//			
//				serial = csvRecord.get("serial").trim();
//				project = csvRecord.get("project");

//				String url = localhost + "/PrintSOLabel/api/salesorder/serial/" + serial;
//                JSONObject response = getJson(url);
//                JSONArray data = response.getJSONArray("data");
//                
//                if (!data.isEmpty()) {
//                    for (Object obj : data) {
//                        JSONObject jsonObject = (JSONObject) obj;
//                        boolean isDeleted = (boolean) jsonObject.get("deleted");
//                        if (!isDeleted) {
//                            throw new RuntimeException("el serial: " + serial + " ya existe");
//                        }
//                    }
//                }
//				System.out.println(csvRecord.get("Subinventory"));
//				System.out.println(csvRecord.get("Item"));
//				System.out.println(csvRecord.get("Item_Description"));
//				System.out.println(csvRecord.get("Customer"));
//				System.out.println(csvRecord.get("Sanmina_Stock_Locators"));
//				System.out.println(csvRecord.get("Quantity")); 
//				System.out.println(csvRecord.get("Target_Cost").replaceAll("\"", " "));
//				System.out.println(csvRecord.get("Extended_Target_Cost"));
//				System.out.println(csvRecord.get("STD_Cost"));
//				System.out.println(csvRecord.get("Extended_STD_Cost"));	
					
				
				//On Hand Model 
				onHandModel inventory = new onHandModel();
				inventory.setSubInventory(csvRecord.get("Subinventory"));
				inventory.setItem(csvRecord.get("Item"));
				inventory.setItem_Description(csvRecord.get("Item_Description"));
				inventory.setCustomer(csvRecord.get("Customer"));
				inventory.setSanmina_Stock_Locators(csvRecord.get("Sanmina_Stock_Locators"));
				inventory.setQuantity(Float.parseFloat(csvRecord.get("Quantity")));
				inventory.setTarget_Cost(Float.parseFloat(csvRecord.get("Target_Cost")));
				inventory.setExtended_Target_Cost(Float.parseFloat(csvRecord.get("Extended_Target_Cost")));
				if (!csvRecord.get("STD_Cost").isEmpty())
				inventory.setsTD_Cost(Float.parseFloat(csvRecord.get("STD_Cost")));
//				System.out.println(inventory.getsTD_Cost());
				if (!csvRecord.get("Extended_STD_Cost").isEmpty()) {
				inventory.setExtended_STD_Cost(Float.parseFloat(csvRecord.get("Extended_STD_Cost")));
//				System.out.println(inventory.getExtended_STD_Cost());
//				System.out.println("entre");
				}
//				System.out.println(csvRecord.get("Extended_STD_Cost"));
				inventories.add(inventory);
			}
			return inventories;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream load(List<onHandModel> inventories) {

		ByteArrayInputStream in = InventoryToCSV(inventories);
		return in;

	}

	public static ByteArrayInputStream InventoryToCSV(List<onHandModel> inventories) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			csvPrinter.printRecord(HEADERs);
			
			//On Hand model
			for (onHandModel inventory : inventories) {
				List<Object> data = Arrays.asList(inventory.getSubInventory(), inventory.getItem(),
						inventory.getItem_Description(), inventory.getCustomer(), inventory.getSanmina_Stock_Locators(),
						inventory.getQuantity(), String.valueOf(inventory.getTarget_Cost()),
						String.valueOf(inventory.getExtended_Target_Cost()), String.valueOf(inventory.getsTD_Cost()),
						String.valueOf(inventory.getExtended_STD_Cost()));
				csvPrinter.printRecord(data);
			}
			// On HAnd Model
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
//	
//	public static Setting searchPartNumberBySerial(String serial, String project, String localhost) throws JsonMappingException, JsonProcessingException {
//
//		String partNumber;
//		
//		String url = "https://production.42-q.com/mes-api/" + project + "/units/" + serial + "/summary";			
//		RestTemplate restTemplate = new RestTemplate();
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		try {		
//			JSONObject json = new JSONObject(restTemplate.getForObject(url, String.class));
//			
//			JSONObject data = json.getJSONObject("data");
//			JSONObject unitInformation = data.getJSONObject("unit_information");
//			
//			if(unitInformation.toString().equals("{}")) {
//				throw new RuntimeException("No se encontro el serial <"+ serial +"> en el proyecto <"+ project +"> \n"
//						+ "Favor de validarlo en 42Q");
//			} else {
//				
//				partNumber = unitInformation.getString("part_number");
//				System.out.println("=== searchPartNumberBySerial ->" + partNumber);
//				
//				url = localhost + "/PrintSOLabel/api/setting/partNumber/" + partNumber;
//				
//				JSONObject response = getJson(url);
//                
//				JSONArray dataArray = response.getJSONArray("data");
//					
//				if(dataArray.toString().equals("[]")) {
//					throw new RuntimeException("El numero de parte <"+ partNumber +"> no se encontro en configuración. \n"
//							+ "Revisar que este dado de alta en la aplicación");
//				} else {
//				
//					JSONObject setting = dataArray.getJSONObject(0);
//					System.out.println(setting);
//					if(setting.getString("partNumber").contains(partNumber.trim())) {
//						
//						return new 
//							Setting (
//							setting.getLong("id"),
//							setting.getString("partNumber"),
//							objectMapper.readValue(setting.getJSONObject("zpl").toString(), Zpl.class),
//							objectMapper.readValue(setting.getJSONObject("printer").toString(), Printer.class)
//							);
//					}
//				}
//			}
//			
//		} catch (IOException e) {
//			throw new RuntimeException("No se encontro el proyecto <"+ project +">. \n"
//					+ "Favor de revisar el archivo CSV cargado");			
//		}
//		
//		return null;
//	}
//	
//	public static JSONObject getJson(String api) {
//	    
//        SSLParameters sslParameters = new SSLParameters();
//        SNIHostName sniHostName = new SNIHostName("localhost");
//
//        List<SNIServerName> sniServerNames = new ArrayList<>();
//        sniServerNames.add(sniHostName);
//        sslParameters.setServerNames(sniServerNames);
//
//        
//        try {
//            URL url = new URL(api);
//            
//            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//            connection.setSSLSocketFactory(SSLContext.getDefault().getSocketFactory());
//            connection.setHostnameVerifier((hostname, session) -> {
//                if (hostname.equals("localhost")) {
//                return true;
//                }
//                return HttpsURLConnection.getDefaultHostnameVerifier().verify(hostname, session);
//            });
//            connection.setDoOutput(true);
//            
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Content-Type", "application/json");
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode != HttpURLConnection.HTTP_OK) {
//                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
//            }
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            in.close();
//            
//            return new JSONObject(response.toString());
//            
//        } catch (IOException | NoSuchAlgorithmException e) {
//			throw new RuntimeException("Error obteniendo informacion de la API: " + api + 
//					"\n" + e.getMessage()); 
//		}
//    }

}