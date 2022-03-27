package grpc.smartWarehouse.pickingOperations;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jmdns.ServiceInfo;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import grpc.smartWarehouse.pickingOperations.PickingGrpc.PickingBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PickingOperationGUI  {


	private static PickingBlockingStub blockingStub; 



		private JFrame frame;
		private JTextField textProductName;
		private JTextField textQuantity;
		private JTextField textSku;
		private JTextArea textResponse;
		private JTextArea textResponseA;
//		private StreamObserver<ShipResponse> responseObserver;
		public static String respondText;
		private JTextField textBin;
		private JTextField textQ;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						PickingOperationGUI  window = new PickingOperationGUI ();
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
		public PickingOperationGUI() {

			

			String host = "localhost";
			int port = 1127;

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			// stubs -- generate from proto
			blockingStub = PickingGrpc.newBlockingStub(channel);


			// build GUI
			initialize();

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

			JLabel lblNewLabel_1 = new JLabel("SKU :");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(151, 41, 111, 13);
			panel_service_1.add(lblNewLabel_1);

			textSku= new JTextField();
			textSku.setBounds(198, 35, 136, 29);
			panel_service_1.add(textSku);
			textSku.setColumns(10);

			JLabel lblNewLabel_2 = new JLabel("Product Name : ");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(87, 84, 111, 14);
			panel_service_1.add(lblNewLabel_2);

			textProductName = new JTextField();
			textProductName.setBounds(198, 80, 136, 35);
			panel_service_1.add(textProductName);
			textProductName.setColumns(10);


			/*
			 * getBin client(request) implementation
			 */
			JButton btnBinRequest = new JButton("Bin Request");
			btnBinRequest.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnBinRequest.setBounds(383, 47, 145, 35);
			btnBinRequest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String sku = textSku.getText();
					String productName = textProductName.getText();
					
					 BinRequest request = BinRequest.newBuilder().setSku(sku).setProductName(productName).build();
					 
					 BinResponse response = blockingStub.getBin(request);
					 
					 textResponse.append("Reply Message: "+ response.getBin()+"\n");
					 
					 System.out.println("Reply Message: "+ response.getBin()+"\n");

					
				}
			});

			panel_service_1.add(btnBinRequest);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(423, 38, 2, 2);

			// textResponse.setSize(new Dimension(15, 30));
			panel_service_1.add(scrollPane);

			JLabel lblNewLabel = new JLabel("Results :");
			lblNewLabel.setBounds(26, 154, 145, 32);
			panel_service_1.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

			textResponse = new JTextArea(3, 20);
			textResponse.setBounds(114, 157, 471, 57);
			panel_service_1.add(textResponse);
			textResponse.setFont(new Font("Monospaced", Font.BOLD, 16));
			textResponse.setLineWrap(true);
			textResponse.setWrapStyleWord(true);

			JLabel lblNewLabel_6 = new JLabel("Product Name :");
			lblNewLabel_6.setBounds(62, 332, 110, 13);
			panel_service_1.add(lblNewLabel_6);
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));

			/*
			 * trackShipment client(request) implementation
			 */
			JTextField textProductName2 = new JTextField();
			textProductName2.setBounds(186, 323, 148, 35);
			panel_service_1.add(textProductName2);
			JButton btnPickProduct = new JButton("Pick Product");
			btnPickProduct.setBounds(371, 360, 157, 35);
			panel_service_1.add(btnPickProduct);
			btnPickProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String bin = textBin.getText();
					String productName = textProductName.getText();
					int quantity = Integer.parseInt(textQ.getText());
					
					 PickRequest request = PickRequest.newBuilder().setBin(bin).setProductName(productName).setQuantity(quantity).build();
					 
					 PickResponse response = blockingStub.pickProduct(request);
					 
					 textResponseA.append("Reply Message: "+ response.getResultMessage()+ "pick_id: "+response.getPickId()+"\n");
					 
					 System.out.println("Reply Message: "+ response.getResultMessage()+ "pick_id: "+response.getPickId()+"\n");
				}
			});
			btnPickProduct.setFont(new Font("Tahoma", Font.BOLD, 15));

			JLabel lblNewLabel_7 = new JLabel("Results :");
			lblNewLabel_7.setBounds(26, 402, 84, 28);
			panel_service_1.add(lblNewLabel_7);
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));

			textResponseA = new JTextArea();
			textResponseA.setBounds(50, 440, 529, 134);
			panel_service_1.add(textResponseA);
			textResponseA.setFont(new Font("Monospaced", Font.BOLD, 16));
			textResponseA.setWrapStyleWord(true);
			textResponseA.setColumns(20);
			textResponseA.setRows(3);
			textResponseA.setLineWrap(true);
			
			JLabel lblNewLabel_6_1 = new JLabel("Bin :");
			lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_6_1.setBounds(126, 282, 45, 13);
			panel_service_1.add(lblNewLabel_6_1);
			
			textBin = new JTextField();
			textBin.setBounds(183, 273, 148, 35);
			panel_service_1.add(textBin);
			
			JLabel lblNewLabel_6_2 = new JLabel("Quantity :");
			lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_6_2.setBounds(371, 282, 110, 13);
			panel_service_1.add(lblNewLabel_6_2);
			
			textQ = new JTextField();
			textQ.setBounds(451, 273, 60, 35);
			panel_service_1.add(textQ);

		}
}//close entire GUI 
