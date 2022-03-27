package ShippingOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import ShippingOperations.ShippingServiceGrpc.ShippingServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class ShippingOperationServer extends ShippingServiceImplBase {
	

//	private static Collection<ShipRequest> shipmentList;

	
	private List<Shipment> shipments;
	private String personName;
	private String address;
	private String productName;
	private int quantity;
	private String date;
	int count = 0;
	
	public ShippingOperationServer() {

		shipments = new ArrayList<Shipment>();
		Shipment s1 = Shipment.newBuilder().setAddress("Dublin 4").setProductName("TV")
				.setQuantity(1).setDate("22-02-22").build();
		Shipment s2 = Shipment.newBuilder().setAddress("Dublin 8").setProductName("Bike")
				.setQuantity(1).setDate("13-02-22").build();
		Shipment s3 = Shipment.newBuilder().setAddress("Dublin 18").setProductName("Monitor")
				.setQuantity(1).setDate("22-02-22").build();
		shipments.add(s1);
		shipments.add(s2);
		shipments.add(s3);
		
	}
	public static void main(String[] args) {
		
		ShippingOperationServer shippingServer = new ShippingOperationServer();
		
		Properties prop = shippingServer.getProperties();
		
		shippingServer.registerService(prop);
		
		int port = Integer.valueOf(prop.getProperty("service_port"));// #.1926

		try {
			
			Server server = ServerBuilder.forPort(port)
					.addService(shippingServer)
					.build()
					.start();

			System.out.println("Shipping operation Server started, listening on " + port+"\n");

			server.awaitTermination();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
}
	
	/**
	 * JmDNS
	 */
	
	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/ShippingOperation.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Shipping service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port")+"\n");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}	
	
	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service_type");// "_smart_warehouse._tcp.local.";
			String service_name = prop.getProperty("service_name");// "shipping_service";
			
			int service_port = Integer.valueOf(prop.getProperty("service_port"));// #.1926;

			String service_description_properties = prop.getProperty("service_description");// "service for shipping";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

			// Wait a bit
			Thread.sleep(1000);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * service implementation for shipProduct client side streaming RPC
	 */
	
	public StreamObserver<ShipRequest> shipProduct(
			StreamObserver<ShipResponse> responseObserver) {
		
			
		return new StreamObserver<ShipRequest>() {
			
			ArrayList<ShipRequest> shipmentList = new ArrayList<>();
			
			
			@Override
			public void onNext(ShipRequest request) {

				System.out.println("received ship request Person name: "+ request.getPersonName()
				+ " address: " + request.getAddress() + " product name: " + request.getProductName()
				+ " quantity: " + request.getQuantity() + " date: " + request.getDate());
				
				shipmentList.add(request);
				count ++;
			}

			@Override
			public void onError(Throwable t) {
				
			}

			@Override
			public void onCompleted() {
				System.out.println("receiving ship request complete \n" );

				System.out.println("recieved shipment request "+ shipmentList.size()+"\n");

//				double temp = 0;	
//				for(Shipment v:  list) {
//					temp = temp + v;
//				}
//				float mean = (float) (temp/list.size());

//				CalculateResponse reply = CalculateResponse.newBuilder().setResult(mean).build();
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				  LocalDate localDate = LocalDate.now();
//				  System.out.println(dtf.format(localDate));
				  String currentDate = dtf.format(localDate);
				  			  
				  
//				  int requestNum = shipmentList.size();

					  
				ShipResponse response = ShipResponse.newBuilder().setResultMessage("Today-date: "+ currentDate +" \n shipment request :" +count).build();
				
				responseObserver.onNext(response);

				responseObserver.onCompleted();

			}

		};

	}
	
	
	
	
	/**
	 * service implementation for trackShipment server-side streaming RPC
	 */
	public void trackShipment(ShippingOperations.Day request, StreamObserver<Shipment> responseObserver) {
		
		
		System.out.println("requested track shipment on the day: " + request.getDay());

		for (Shipment shipment : shipments) {
			if (!shipment.getDate().equals(request.getDay())) {
				continue;
			}
			
			address = shipment.getAddress();
			productName = shipment.getProductName();
			quantity = shipment.getQuantity();
			date = shipment.getDate();
			
			responseObserver.onNext(shipment);

			// every 2 seconds
			try {

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} // close for loop

		responseObserver.onCompleted();
	}
}

