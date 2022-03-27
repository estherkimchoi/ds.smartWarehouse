package OrderOperations;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import OrderOperations.PlacingOrderServiceGrpc.PlacingOrderServiceBlockingStub;
import OrderOperations.PlacingOrderServiceGrpc.PlacingOrderServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import javax.swing.JTextPane;
import java.util.Iterator;

public class PlacingOrderOperationGUI {

	private static PlacingOrderServiceBlockingStub blockingStub;
	private static PlacingOrderServiceStub asyncStub;

	private ServiceInfo placingOrderServiceInfo;
	

	private JFrame frame;
	private JTextField textPersonName;
	private JTextField textAddress;
	private JTextField textProductName;
	private JTextField textQuantity;
	private JTextField textDate;
	private JTextArea textResponse;
	private JTextArea textResponseA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlacingOrderOperationGUI window = new PlacingOrderOperationGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlacingOrderOperationGUI() {

		String place_order_service_type = "_smart_warehouse._tcp.local.";
		discoverPlacingOrderService(place_order_service_type);

		String host = placingOrderServiceInfo.getHostAddresses()[0];
		int port = placingOrderServiceInfo.getPort();

		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();

		// stubs -- generate from proto
		blockingStub = PlacingOrderServiceGrpc.newBlockingStub(channel);

		asyncStub = PlacingOrderServiceGrpc.newStub(channel);

		//build GUI
		initialize();

		
	}

	private void discoverPlacingOrderService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override //when the service is ready
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Placing Order Service resolved: " + event.getInfo());

					placingOrderServiceInfo = event.getInfo();

					int port = placingOrderServiceInfo.getPort();

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + placingOrderServiceInfo.getNiceTextString());
					System.out.println("\t host: " + placingOrderServiceInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Placing Order Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Placing Order Service added: " + event.getInfo());

				}
			});

			// Wait a bit
			Thread.sleep(2000);

			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Placing Order Operation Service");
		frame.setBounds(100, 100, 623, 758);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Person Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(72, 41, 111, 13);
		panel_service_1.add(lblNewLabel_1);
		
		textPersonName = new JTextField();
		textPersonName.setBounds(173, 35, 119, 29);
		panel_service_1.add(textPersonName);
		textPersonName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(328, 40, 79, 14);
		panel_service_1.add(lblNewLabel_2);
		
		textAddress = new JTextField();
		textAddress.setBounds(400, 36, 108, 27);
		panel_service_1.add(textAddress);
		textAddress.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Product Name :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(72, 108, 111, 13);
		panel_service_1.add(lblNewLabel_3);
		
		textProductName = new JTextField();
		textProductName.setBounds(185, 102, 107, 29);
		panel_service_1.add(textProductName);
		textProductName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(328, 108, 79, 13);
		panel_service_1.add(lblNewLabel_4);
		
		textQuantity = new JTextField();
		textQuantity.setBounds(400, 102, 108, 29);
		panel_service_1.add(textQuantity);
		textQuantity.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(105, 164, 105, 35);
		panel_service_1.add(lblNewLabel_5);
		
		textDate = new JTextField();
		textDate.setBounds(173, 169, 125, 29);
		panel_service_1.add(textDate);
		textDate.setColumns(10);
		
		
	
		/*
		 * getOrder client(request) implementation
		 * */
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPlaceOrder.setBounds(371, 158, 164, 44);
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String personName = textPersonName.getText();
				String address = textAddress.getText();
				String productName = textProductName.getText();
				int quantity = Integer.parseInt(textQuantity.getText());
				String date =  textDate.getText();
	
				
				StreamObserver<OrdersResponse> responseObserver = new StreamObserver<OrdersResponse>() {

					int count =0 ;
					
					@Override
					public void onNext(OrdersResponse msg) {

						textResponse.append("reply message:"+ msg.getResultMessage() + " order id:"+ msg.getOrderId() + "\n");
						textResponse.append("\n");
						System.out.println("res: " + msg.getResultMessage() + " order id: " + msg.getOrderId());
						count += 1;
						
//						System.out.println("recieving message: " + msg.getResultMessage() + " order id: " + msg.getOrderId() );
//						count += 1;
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
						System.out.println("stream is completed ... received "+ count+ " orders");
						
					}
				
				};//close getOrder StreamObserver response
				
				StreamObserver<OrdersRequest> requestObserver = asyncStub.getOrders(responseObserver);

				try {

//					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Mike").setAddress("Dublin 5").setProductName("Bike").setQuantity(2).setDate("10-01-22").build() );
//					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Samuel").setAddress("Dublin 6").setProductName("TV").setQuantity(1).setDate("10-01-22").build() );
//					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Terry").setAddress("Dublin 12").setProductName("Microwave").setQuantity(1).setDate("20-03-22").build() );
//					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Luke").setAddress("Dublin 8").setProductName("Iron").setQuantity(1).setDate("30-04-22").build() );
//					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("John").setAddress("Dublin 4").setProductName("Refrigerator").setQuantity(1).setDate("25-02-22").build() );
					
					requestObserver.onNext(OrdersRequest.newBuilder().setPersonName(personName).setAddress(address).setProductName(productName).setQuantity(2).setDate(date).build() );
//					
					// Mark the end of requests
					requestObserver.onCompleted();


					// Sleep for a bit before sending the next one.
					Thread.sleep(new Random().nextInt(1000) + 500);


				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {			
					e1.printStackTrace();
				}



				try {
					Thread.sleep(15000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
//				OrdersRequest req = OrdersRequest.newBuilder().setPersonName(personName)
//						.setAddress(address).setProductName(productName).setQuantity(quantity)
//						.setDate(date).build();
//				
//				
//				OrdersResponse response = asyncStub.getOrders(req);
//
//				textResponse.append("reply message:"+ response.getResultMessage() + " order id:"+ response.getOrderId() + "\n");
//				
//				System.out.println("res: " + response.getResultMessage() + " order id: " + response.getOrderId());

			}
		}); 
		
		panel_service_1.add(btnPlaceOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 38, 2, 2);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);
		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(null);
		
		textResponse = new JTextArea(3, 20);
		textResponse.setFont(new Font("Monospaced", Font.BOLD, 16));
		textResponse.setBounds(20, 55, 564, 149);
		panel_service_2.add(textResponse);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JLabel lblNewLabel = new JLabel("Results :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(69, 21, 145, 32);
		panel_service_2.add(lblNewLabel);
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
		panel_service_3.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Date :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(56, 22, 45, 13);
		panel_service_3.add(lblNewLabel_6);
		
		JTextField textDay = new JTextField();
		textDay.setBounds(111, 10, 148, 35);
		panel_service_3.add(textDay);
		
		/*
		 * trackOrder client(request) implementation
		 * */
		JButton btnNewButton = new JButton("Track Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				String day = textDay.getText();
//			Day request = Day.newBuilder().setDay(day).build();
//	            Iterator<Order> orders;
//	            
//	            try {
//	                orders = blockingStub.trackOrders(request);
//	            for (int i = 1; orders.hasNext(); i++) {
//	                Order order = orders.next();
//	                
//	                System.out.println("Result #" + i + ": " + (order).toString());
//	                textResponseArea.append("hello");
//	               // textResponseArea.append("Result #" + i + ": " + (order).toString());
////	                textResponseArea.append("reply message:"+ order.getPersonName() 
////	                + order.getAddress() + order.getProductName() +
////	                order.getQuantity() + order.getDate());
//	                
//	            }
//	        } catch (RuntimeException e) {
//	            System.out.println(e.getMessage());
//	            return;
//	        }
				String day = textDay.getText();
				
				Day request = Day.newBuilder()
						.setDay(day).build();

				StreamObserver<Order> responseObserver = new StreamObserver<Order>() {
					
					@Override
					public void onNext(Order value) {
						
					
						//textResponseA.append("reply message: "+ value.getPersonName() + value.getAddress() + value.getProductName() + value.getQuantity() + value.getDate());
//						textResponseA.append("reply message: \n receiving OrderPersonName: "+ value.getPersonName()+"\n" + " Address: "+ value.getAddress()+"\n" + " Product name: "+ value.getProductName()
//						+ "\n"+" Quantity: "+ value.getQuantity()+"\n" + " Date: "+ value.getDate() +"\n");
						textResponseA.append("receiving orderPersonName: "+ value.getPersonName()+" address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
							    " quantity: "+ value.getQuantity() + " date: "+ value.getDate() +"\n");
						textResponseA.append("\n");
						System.out.println("receiving orderPersonName: "+ value.getPersonName()+" address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
								    " quantity: "+ value.getQuantity() + " date: "+ value.getDate() +"\n");
						
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.println("stream is completed.");
					}

				};

				asyncStub.trackOrders(request, responseObserver);

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(310, 11, 157, 35);
		panel_service_3.add(btnNewButton);
		
		textResponseA = new JTextArea();
		textResponseA.setFont(new Font("Monospaced", Font.BOLD, 16));
		textResponseA.setWrapStyleWord(true);
		textResponseA.setColumns(20);
		textResponseA.setRows(3);
		textResponseA.setLineWrap(true);
		textResponseA.setBounds(30, 87, 549, 143);
		panel_service_3.add(textResponseA);
		
		JLabel lblNewLabel_7 = new JLabel("Results :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(73, 55, 84, 28);
		panel_service_3.add(lblNewLabel_7);
		
		
	}
}
