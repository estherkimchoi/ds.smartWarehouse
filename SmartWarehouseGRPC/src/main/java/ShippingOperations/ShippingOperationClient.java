package ShippingOperations;

import java.util.Iterator;


import ShippingOperations.ShippingServiceGrpc.ShippingServiceBlockingStub;
import ShippingOperations.ShippingServiceGrpc.ShippingServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class ShippingOperationClient {
	
	private static ShippingServiceBlockingStub blockingStub;
	private static ShippingServiceStub asyncStub;

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 1926)
				.usePlaintext()
				.build();

		blockingStub = ShippingServiceGrpc.newBlockingStub(channel);

		asyncStub = ShippingServiceGrpc.newStub(channel);
		
		shipProduct();
		trackShipmentBlocking();
		trackShipmentAsyn();
		
	}
	
	/*
	 * client streaming RPC - shipProduct
	 */
	public static void shipProduct() {

		StreamObserver<ShipResponse> responseObserver = new StreamObserver<ShipResponse>() {

			@Override
			public void onNext(ShipResponse msg) {
				System.out.println("result message  " + msg.getResultMessage() );
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
			requestObserver.onNext(ShipRequest.newBuilder().setPersonName("Esther").setAddress("Dublin 4").setProductName("TV")
					.setQuantity(1).setDate("10-01-22").build() );
			Thread.sleep(500);

			requestObserver.onNext(ShipRequest.newBuilder().setPersonName("Jane").setAddress("Dublin 8").setProductName("Monitor")
					.setQuantity(2).setDate("04-02-22").build() );
			Thread.sleep(500);

			requestObserver.onNext(ShipRequest.newBuilder().setPersonName("John").setAddress("Dublin 18").setProductName("Microwave")
					.setQuantity(3).setDate("15-02-22").build() );
			Thread.sleep(500);

			requestObserver.onNext(ShipRequest.newBuilder().setPersonName("Matthew").setAddress("Dublin 4").setProductName("Refrigerator")
					.setQuantity(2).setDate("04-02-22").build() );
			Thread.sleep(500);


			// Mark the end of requests
			requestObserver.onCompleted();

			
			Thread.sleep(10000);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}


	}
	

	/*
	 * blocking server-streaming - trackShipment
	 */
	
		public static void trackShipmentBlocking() {
			Day request = Day.newBuilder()
					.setDay("22-02-22")
					.build();
			

			try {
				Iterator<Shipment> response = blockingStub.trackShipment(request);

				while(response.hasNext()) {
					Shipment temp = response.next();
					System.out.println("requested day's shipping detail- address: "+ temp.getAddress() + " product name: "+ temp.getProductName() + 
							" quantity: "+ temp.getQuantity() + " date: "+ temp.getDate());				
				}

			} catch (StatusRuntimeException e) {
				e.printStackTrace();
			}
		}//close blocking
		
		public static void trackShipmentAsyn() {

			Day request = Day.newBuilder()
					.setDay("13-02-22").build();

			StreamObserver<Shipment> responseObserver = new StreamObserver<Shipment>() {

				int count =0 ;

				@Override
				public void onNext(Shipment value) {
					System.out.println("requested day's shipping detail- address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
						" quantity: "+ value.getQuantity() + " date: "+ value.getDate() );
					count ++ ;
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();

				}

				@Override
				public void onCompleted() {
//					System.out.println("stream is completed ... received "+ count+ " shipment");
				}

			};

			asyncStub.trackShipment(request, responseObserver);

			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	
	

}
