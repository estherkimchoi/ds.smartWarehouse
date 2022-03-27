package ShippingOperations;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import OrderOperations.Day;
import OrderOperations.Order;
import OrderOperations.OrdersRequest;
import OrderOperations.OrdersResponse;
import ShippingOperations.ShippingServiceGrpc.ShippingServiceBlockingStub;
import ShippingOperations.ShippingServiceGrpc.ShippingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ShippingOperationGUI {

	private static ShippingServiceBlockingStub blockingStub;
	private static ShippingServiceStub asyncStub;

	private ServiceInfo shippingServiceInfo;

	private JFrame frame;
	private JTextField textPersonName;
	private JTextField textAddress;
	private JTextField textProductName;
	private JTextField textQuantity;
	private JTextField textDate;
	private JTextArea textResponse;
	private JTextArea textResponseA;
//	private StreamObserver<ShipResponse> responseObserver;
	public static String respondText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShippingOperationGUI window = new ShippingOperationGUI();
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
	public ShippingOperationGUI() {

		String shipping_service_type = "_smart_warehouse._tcp.local.";
		discoverShippingService(shipping_service_type);

		String host = shippingServiceInfo.getHostAddresses()[0];
		int port = shippingServiceInfo.getPort();

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// stubs -- generate from proto
		blockingStub = ShippingServiceGrpc.newBlockingStub(channel);

		asyncStub = ShippingServiceGrpc.newStub(channel);

		// build GUI
		initialize();

	}

	private void discoverShippingService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override // when the service is ready
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Shipping Service resolved: " + event.getInfo());

					shippingServiceInfo = event.getInfo();

					int port = shippingServiceInfo.getPort();

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + shippingServiceInfo.getNiceTextString());
					System.out.println("\t host: " + shippingServiceInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Shipping Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Shipping Service added: " + event.getInfo());

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
		frame.setTitle("Shipping Service");
		frame.setBounds(100, 100, 623, 621);
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
		lblNewLabel_3.setBounds(72, 98, 111, 13);
		panel_service_1.add(lblNewLabel_3);

		textProductName = new JTextField();
		textProductName.setBounds(185, 92, 107, 29);
		panel_service_1.add(textProductName);
		textProductName.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Quantity :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(328, 98, 79, 13);
		panel_service_1.add(lblNewLabel_4);

		textQuantity = new JTextField();
		textQuantity.setBounds(400, 92, 108, 29);
		panel_service_1.add(textQuantity);
		textQuantity.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Date :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(84, 133, 105, 35);
		panel_service_1.add(lblNewLabel_5);

		textDate = new JTextField();
		textDate.setBounds(137, 138, 125, 29);
		panel_service_1.add(textDate);
		textDate.setColumns(10);

		/*
		 * shipProduct client(request) implementation
		 */
		JButton btnAddShipment = new JButton("Add Shipment");
		btnAddShipment.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddShipment.setBounds(272, 133, 145, 35);
		btnAddShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String personName = textPersonName.getText();
				String address = textAddress.getText();
				String productName = textProductName.getText();
				int quantity = Integer.parseInt(textQuantity.getText());
				String date = textDate.getText();

				StreamObserver<ShipResponse> responseObserver = new StreamObserver<ShipResponse>() {

					@Override
					public void onNext(ShipResponse msg) {
						respondText = "reply message:"+ msg.getResultMessage();
//						System.out.println("result message  " + msg.getResultMessage());
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
						System.out.println("stream is completed ... receiveing the number of today's shipment \n");
					}

				};

				StreamObserver<ShipRequest> requestObserver = asyncStub.shipProduct(responseObserver);
				try {
					requestObserver.onNext(ShipRequest.newBuilder().setPersonName(personName).setAddress(address)
							.setProductName(productName).setQuantity(quantity).setDate(date).build());

					// Mark the end of requests
					requestObserver.onCompleted();

					Thread.sleep(10000);

				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		panel_service_1.add(btnAddShipment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 38, 2, 2);

		// textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Results :");
		lblNewLabel.setBounds(26, 195, 145, 32);
		panel_service_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		textResponse = new JTextArea(3, 20);
		textResponse.setBounds(115, 198, 471, 105);
		panel_service_1.add(textResponse);
		textResponse.setFont(new Font("Monospaced", Font.BOLD, 16));
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		JLabel lblNewLabel_6 = new JLabel("Date :");
		lblNewLabel_6.setBounds(43, 337, 45, 13);
		panel_service_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));

		/*
		 * trackShipment client(request) implementation
		 */
		JTextField textDay = new JTextField();
		textDay.setBounds(114, 328, 148, 35);
		panel_service_1.add(textDay);
		JButton btnTrackShipment = new JButton("Track Shipment");
		btnTrackShipment.setBounds(286, 326, 157, 35);
		panel_service_1.add(btnTrackShipment);
		btnTrackShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String day = textDay.getText();
				
				ShippingOperations.Day request = ShippingOperations.Day.newBuilder()
						.setDay(day).build();

				StreamObserver<Shipment> responseObserver = new StreamObserver<Shipment>() {
					
					
					@Override
					public void onNext(Shipment value) {
						
					
						//textResponseA.append("reply message: "+ value.getPersonName() + value.getAddress() + value.getProductName() + value.getQuantity() + value.getDate());
//						textResponseA.append("reply message: \n receiving OrderPersonName: "+ value.getPersonName()+"\n" + " Address: "+ value.getAddress()+"\n" + " Product name: "+ value.getProductName()
//						+ "\n"+" Quantity: "+ value.getQuantity()+"\n" + " Date: "+ value.getDate() +"\n");
						textResponseA.append("shipping address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
							    " quantity: "+ value.getQuantity() + " date: "+ value.getDate() +"\n");
						System.out.println("requested day's shipping detail- address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
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

				asyncStub.trackShipment(request, responseObserver);

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		btnTrackShipment.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel_7 = new JLabel("Results :");
		lblNewLabel_7.setBounds(26, 402, 84, 28);
		panel_service_1.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));

		textResponseA = new JTextArea();
		textResponseA.setBounds(43, 429, 529, 134);
		panel_service_1.add(textResponseA);
		textResponseA.setFont(new Font("Monospaced", Font.BOLD, 16));
		textResponseA.setWrapStyleWord(true);
		textResponseA.setColumns(20);
		textResponseA.setRows(3);
		textResponseA.setLineWrap(true);

		JButton btnShipProduct = new JButton("Ship Product");
		btnShipProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textResponse.append(respondText);
				

			}
		});
		btnShipProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShipProduct.setBounds(427, 135, 145, 31);
		panel_service_1.add(btnShipProduct);

	}
}
