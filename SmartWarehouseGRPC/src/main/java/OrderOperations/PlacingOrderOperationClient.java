package OrderOperations;

import java.util.Iterator;
import java.util.Random;

import OrderOperations.PlacingOrderServiceGrpc.PlacingOrderServiceBlockingStub;
import OrderOperations.PlacingOrderServiceGrpc.PlacingOrderServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class PlacingOrderOperationClient {

	private static PlacingOrderServiceBlockingStub blockingStub;
	private static PlacingOrderServiceStub asyncStub;
	
	public static void main(String[] args) {
		//create channel that communicate with the grpc server 
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();

		//stubs to call API-- generate from proto
		//blocking stub -it cannot makes an asynchronous call= when I send a request to 
		//the server or client will wait for the server response
		//and it won't proceed with further operations
		blockingStub = PlacingOrderServiceGrpc.newBlockingStub(channel);

		//asyncronous calls //pass the channel to connect the stub
		asyncStub = PlacingOrderServiceGrpc.newStub(channel);

		getOrders();
		trackOrdersBlocking();
		trackOrdersAsyn();
	}
	
	/*
	 * bidirectional streaming RPC - getOrders
	 */
	public static void getOrders() {
				
		StreamObserver<OrdersResponse> responseObserver = new StreamObserver<OrdersResponse>() {

			int count =0 ;
			
			@Override
			public void onNext(OrdersResponse msg) {
				System.out.println("message: " + msg.getResultMessage() + " order id: " + msg.getOrderId() );
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " orders \n");
				
			}
		
		};//close getOrder StreamObserver response
		
		StreamObserver<OrdersRequest> requestObserver = asyncStub.getOrders(responseObserver);

		try {

			requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Mike").setAddress("Dublin 5").setProductName("Bike").setQuantity(2).setDate("10-01-22").build() );
			requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Samuel").setAddress("Dublin 6").setProductName("TV").setQuantity(1).setDate("10-01-22").build() );
			requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Terry").setAddress("Dublin 12").setProductName("Microwave").setQuantity(1).setDate("20-03-22").build() );
			requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("Luke").setAddress("Dublin 8").setProductName("Iron").setQuantity(1).setDate("30-04-22").build() );
			requestObserver.onNext(OrdersRequest.newBuilder().setPersonName("John").setAddress("Dublin 4").setProductName("Refrigerator").setQuantity(1).setDate("25-02-22").build() );


			// Mark the end of requests
			requestObserver.onCompleted();


			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);


		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}



		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * blocking server-streaming - trackOrders
	 */
	
		public static void trackOrdersBlocking() {
			Day request = Day.newBuilder()
					.setDay("10-01-22")
					.build();
			

			try {
				Iterator<Order> response = blockingStub.trackOrders(request);
				
				System.out.println("blocking stub Placing order detail");
				while(response.hasNext()) {
					Order temp = response.next();
					
					System.out.println("orderPersonName: "+ temp.getPersonName()+ 
							" address: "+ temp.getAddress() + " product name: "+ temp.getProductName() + 
							" quantity: "+ temp.getQuantity() + " date: "+ temp.getDate()+ "\n");				
				}

			} catch (StatusRuntimeException e) {
				e.printStackTrace();
			}
		}//close blocking


		public static void trackOrdersAsyn() {

			Day request = Day.newBuilder()
					.setDay("04-02-22").build();

			StreamObserver<Order> responseObserver = new StreamObserver<Order>() {

				int count =0 ;
				
				@Override
				public void onNext(Order value) {
					
					System.out.println("receiving orderPersonName: "+ value.getPersonName()+ 
						" address: "+ value.getAddress() + " product name: "+ value.getProductName() + 
						" quantity: "+ value.getQuantity() + " date: "+ value.getDate() );
					count += 1;
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();

				}

				@Override
				public void onCompleted() {
					System.out.println("stream is completed ... received "+ count+ " orders");
				}

			};

			asyncStub.trackOrders(request, responseObserver);

			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}


}
