package OrderOperations;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: PlacingOrderOperation.proto")
public final class PlacingOrderServiceGrpc {

  private PlacingOrderServiceGrpc() {}

  public static final String SERVICE_NAME = "smartWarehouse.PlacingOrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<OrderOperations.OrdersRequest,
      OrderOperations.OrdersResponse> getGetOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrders",
      requestType = OrderOperations.OrdersRequest.class,
      responseType = OrderOperations.OrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<OrderOperations.OrdersRequest,
      OrderOperations.OrdersResponse> getGetOrdersMethod() {
    io.grpc.MethodDescriptor<OrderOperations.OrdersRequest, OrderOperations.OrdersResponse> getGetOrdersMethod;
    if ((getGetOrdersMethod = PlacingOrderServiceGrpc.getGetOrdersMethod) == null) {
      synchronized (PlacingOrderServiceGrpc.class) {
        if ((getGetOrdersMethod = PlacingOrderServiceGrpc.getGetOrdersMethod) == null) {
          PlacingOrderServiceGrpc.getGetOrdersMethod = getGetOrdersMethod = 
              io.grpc.MethodDescriptor.<OrderOperations.OrdersRequest, OrderOperations.OrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "smartWarehouse.PlacingOrderService", "getOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OrderOperations.OrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OrderOperations.OrdersResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PlacingOrderServiceMethodDescriptorSupplier("getOrders"))
                  .build();
          }
        }
     }
     return getGetOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<OrderOperations.Day,
      OrderOperations.Order> getTrackOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "trackOrders",
      requestType = OrderOperations.Day.class,
      responseType = OrderOperations.Order.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<OrderOperations.Day,
      OrderOperations.Order> getTrackOrdersMethod() {
    io.grpc.MethodDescriptor<OrderOperations.Day, OrderOperations.Order> getTrackOrdersMethod;
    if ((getTrackOrdersMethod = PlacingOrderServiceGrpc.getTrackOrdersMethod) == null) {
      synchronized (PlacingOrderServiceGrpc.class) {
        if ((getTrackOrdersMethod = PlacingOrderServiceGrpc.getTrackOrdersMethod) == null) {
          PlacingOrderServiceGrpc.getTrackOrdersMethod = getTrackOrdersMethod = 
              io.grpc.MethodDescriptor.<OrderOperations.Day, OrderOperations.Order>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "smartWarehouse.PlacingOrderService", "trackOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OrderOperations.Day.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OrderOperations.Order.getDefaultInstance()))
                  .setSchemaDescriptor(new PlacingOrderServiceMethodDescriptorSupplier("trackOrders"))
                  .build();
          }
        }
     }
     return getTrackOrdersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PlacingOrderServiceStub newStub(io.grpc.Channel channel) {
    return new PlacingOrderServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PlacingOrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PlacingOrderServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PlacingOrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PlacingOrderServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PlacingOrderServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *stream of orders from different clients and send result to client from server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<OrderOperations.OrdersRequest> getOrders(
        io.grpc.stub.StreamObserver<OrderOperations.OrdersResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     *track orders of specific day
     * </pre>
     */
    public void trackOrders(OrderOperations.Day request,
        io.grpc.stub.StreamObserver<OrderOperations.Order> responseObserver) {
      asyncUnimplementedUnaryCall(getTrackOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetOrdersMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                OrderOperations.OrdersRequest,
                OrderOperations.OrdersResponse>(
                  this, METHODID_GET_ORDERS)))
          .addMethod(
            getTrackOrdersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                OrderOperations.Day,
                OrderOperations.Order>(
                  this, METHODID_TRACK_ORDERS)))
          .build();
    }
  }

  /**
   */
  public static final class PlacingOrderServiceStub extends io.grpc.stub.AbstractStub<PlacingOrderServiceStub> {
    private PlacingOrderServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlacingOrderServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlacingOrderServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlacingOrderServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *stream of orders from different clients and send result to client from server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<OrderOperations.OrdersRequest> getOrders(
        io.grpc.stub.StreamObserver<OrderOperations.OrdersResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetOrdersMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *track orders of specific day
     * </pre>
     */
    public void trackOrders(OrderOperations.Day request,
        io.grpc.stub.StreamObserver<OrderOperations.Order> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getTrackOrdersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PlacingOrderServiceBlockingStub extends io.grpc.stub.AbstractStub<PlacingOrderServiceBlockingStub> {
    private PlacingOrderServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlacingOrderServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlacingOrderServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlacingOrderServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *track orders of specific day
     * </pre>
     */
    public java.util.Iterator<OrderOperations.Order> trackOrders(
        OrderOperations.Day request) {
      return blockingServerStreamingCall(
          getChannel(), getTrackOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PlacingOrderServiceFutureStub extends io.grpc.stub.AbstractStub<PlacingOrderServiceFutureStub> {
    private PlacingOrderServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PlacingOrderServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PlacingOrderServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PlacingOrderServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_TRACK_ORDERS = 0;
  private static final int METHODID_GET_ORDERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PlacingOrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PlacingOrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRACK_ORDERS:
          serviceImpl.trackOrders((OrderOperations.Day) request,
              (io.grpc.stub.StreamObserver<OrderOperations.Order>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ORDERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getOrders(
              (io.grpc.stub.StreamObserver<OrderOperations.OrdersResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PlacingOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PlacingOrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return OrderOperations.OrderOperationServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PlacingOrderService");
    }
  }

  private static final class PlacingOrderServiceFileDescriptorSupplier
      extends PlacingOrderServiceBaseDescriptorSupplier {
    PlacingOrderServiceFileDescriptorSupplier() {}
  }

  private static final class PlacingOrderServiceMethodDescriptorSupplier
      extends PlacingOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PlacingOrderServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PlacingOrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PlacingOrderServiceFileDescriptorSupplier())
              .addMethod(getGetOrdersMethod())
              .addMethod(getTrackOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
