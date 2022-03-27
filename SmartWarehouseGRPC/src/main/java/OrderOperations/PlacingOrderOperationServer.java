package OrderOperations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;


import OrderOperations.PlacingOrderServiceGrpc.PlacingOrderServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PlacingOrderOperationServer extends PlacingOrderServiceImplBase {

	private List<Order> orders;
	private String personName;
	private String address;
	private String productName;
	private int quantity;
	private String date;

	public PlacingOrderOperationServer() {

		orders = new ArrayList<Order>();
		Order order1 = Order.newBuilder().setPersonName("Esther").setAddress("Dublin 4").setProductName("TV")
				.setQuantity(1).setDate("10-01-22").build();
		Order order2 = Order.newBuilder().setPersonName("Jane").setAddress("Dublin 8").setProductName("Monitor")
				.setQuantity(2).setDate("04-02-22").build();
		Order order3 = Order.newBuilder().setPersonName("John").setAddress("Dublin 18").setProductName("Microwave")
				.setQuantity(3).setDate("15-02-22").build();
		Order order4 = Order.newBuilder().setPersonName("Matthew").setAddress("Dublin 4").setProductName("Refrigerator")
				.setQuantity(2).setDate("04-02-22").build();
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);
	}

	public static void main(String[] args) {

		PlacingOrderOperationServer placeOrderServer = new PlacingOrderOperationServer();

		Properties prop = placeOrderServer.getProperties();

		placeOrderServer.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));// #.50051;

		try {
			
			Server server = ServerBuilder.forPort(port)
					.addService(placeOrderServer)
					.build()
					.start();

			System.out.println("Placing Order Operation Server started, listening on " + port + "\n");

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

		try (InputStream input = new FileInputStream("src/main/resources/PlacingOrderOperation.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("PlacingOrderServiceproperies ...");
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
			String service_name = prop.getProperty("service_name");// "placing_order";
			
			int service_port = Integer.valueOf(prop.getProperty("service_port"));// #.50051;

			String service_description_properties = prop.getProperty("service_description");// "service for place order and track orders operations";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

			// Wait a bit
			Thread.sleep(2000);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * service implementation for getOrders bidirectional streaming RPC
	 */
	public StreamObserver<OrdersRequest> getOrders(StreamObserver<OrdersResponse> responseObserver) {
		return new StreamObserver<OrdersRequest>() {

			@Override
			public void onNext(OrdersRequest request) {//receive massage
				
				System.out.println("personName: " + request.getPersonName()
						+ " address: " + request.getAddress() + " product name: " + request.getProductName()
						+ " quantity: " + request.getQuantity() + " date: " + request.getDate());

				// get data from request
				String personName = request.getPersonName();
				String address = request.getAddress();
				String productName = request.getProductName();
				int quantity = request.getQuantity();
				String date = request.getDate();

				// respose message
				String msg = request.getPersonName() + "'s order placed successfully \n";
				// size of random alphanumeric string for orderId
				int n = 20;
				String orderId = getAlphaNumericString(n);
				OrdersResponse response = OrdersResponse.newBuilder().setResultMessage(msg).setOrderId(orderId)
						.build();

				responseObserver.onNext(response);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("receiving orders completed \n");

				responseObserver.onCompleted();

			}
		};
	}

	/**
	 * service implementation for trackOrders server-side streaming RPC
	 */
	public void trackOrders(OrderOperations.Day request, StreamObserver<Order> responseObserver) {

		System.out.println("requested track order on the date: " + request.getDay()+"\n");

		for (Order order : orders) {
			if (!order.getDate().equals(request.getDay())) {
				continue;
			}
			personName = order.getPersonName();
			address = order.getAddress();
			productName = order.getProductName();
			quantity = order.getQuantity();
			date = order.getDate();
//			
//			Order response = Order.newBuilder().setPersonName(personName).setAddress(address)
//					.setProductName(productName).setQuantity(quantity).setDate(date).build();
//			responseObserver.onNext(response);
//			responseObserver.onNext(order);
			responseObserver.onNext(Order.newBuilder().setPersonName(personName).setAddress(address)
					.setProductName(productName).setQuantity(quantity).setDate(date).build());
			// every 2 seconds
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} // close for loop

		responseObserver.onCompleted();
	}

	// generate random orderId
	static String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// remove all special char
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaNumericString.length(); k++) {

			if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0)
					|| Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) {

				r.append(AlphaNumericString.charAt(k));
				n--;
			}
		}

		// return the resultant string
		return r.toString();
	}

}
