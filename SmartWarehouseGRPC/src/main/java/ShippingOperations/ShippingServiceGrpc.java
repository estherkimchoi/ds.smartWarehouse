package ShippingOperations;

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
    comments = "Source: ShippingOperation.proto")
public final class ShippingServiceGrpc {

  private ShippingServiceGrpc() {}

  public static final String SERVICE_NAME = "smartWarehouse.ShippingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ShippingOperations.ShipRequest,
      ShippingOperations.ShipResponse> getShipProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "shipProduct",
      requestType = ShippingOperations.ShipRequest.class,
      responseType = ShippingOperations.ShipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<ShippingOperations.ShipRequest,
      ShippingOperations.ShipResponse> getShipProductMethod() {
    io.grpc.MethodDescriptor<ShippingOperations.ShipRequest, ShippingOperations.ShipResponse> getShipProductMethod;
    if ((getShipProductMethod = ShippingServiceGrpc.getShipProductMethod) == null) {
      synchronized (ShippingServiceGrpc.class) {
        if ((getShipProductMethod = ShippingServiceGrpc.getShipProductMethod) == null) {
          ShippingServiceGrpc.getShipProductMethod = getShipProductMethod = 
              io.grpc.MethodDescriptor.<ShippingOperations.ShipRequest, ShippingOperations.ShipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "smartWarehouse.ShippingService", "shipProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ShippingOperations.ShipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ShippingOperations.ShipResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ShippingServiceMethodDescriptorSupplier("shipProduct"))
                  .build();
          }
        }
     }
     return getShipProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ShippingOperations.Day,
      ShippingOperations.Shipment> getTrackShipmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "trackShipment",
      requestType = ShippingOperations.Day.class,
      responseType = ShippingOperations.Shipment.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ShippingOperations.Day,
      ShippingOperations.Shipment> getTrackShipmentMethod() {
    io.grpc.MethodDescriptor<ShippingOperations.Day, ShippingOperations.Shipment> getTrackShipmentMethod;
    if ((getTrackShipmentMethod = ShippingServiceGrpc.getTrackShipmentMethod) == null) {
      synchronized (ShippingServiceGrpc.class) {
        if ((getTrackShipmentMethod = ShippingServiceGrpc.getTrackShipmentMethod) == null) {
          ShippingServiceGrpc.getTrackShipmentMethod = getTrackShipmentMethod = 
              io.grpc.MethodDescriptor.<ShippingOperations.Day, ShippingOperations.Shipment>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "smartWarehouse.ShippingService", "trackShipment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ShippingOperations.Day.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ShippingOperations.Shipment.getDefaultInstance()))
                  .setSchemaDescriptor(new ShippingServiceMethodDescriptorSupplier("trackShipment"))
                  .build();
          }
        }
     }
     return getTrackShipmentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ShippingServiceStub newStub(io.grpc.Channel channel) {
    return new ShippingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ShippingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ShippingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ShippingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ShippingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ShippingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<ShippingOperations.ShipRequest> shipProduct(
        io.grpc.stub.StreamObserver<ShippingOperations.ShipResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getShipProductMethod(), responseObserver);
    }

    /**
     */
    public void trackShipment(ShippingOperations.Day request,
        io.grpc.stub.StreamObserver<ShippingOperations.Shipment> responseObserver) {
      asyncUnimplementedUnaryCall(getTrackShipmentMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getShipProductMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                ShippingOperations.ShipRequest,
                ShippingOperations.ShipResponse>(
                  this, METHODID_SHIP_PRODUCT)))
          .addMethod(
            getTrackShipmentMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ShippingOperations.Day,
                ShippingOperations.Shipment>(
                  this, METHODID_TRACK_SHIPMENT)))
          .build();
    }
  }

  /**
   */
  public static final class ShippingServiceStub extends io.grpc.stub.AbstractStub<ShippingServiceStub> {
    private ShippingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ShippingOperations.ShipRequest> shipProduct(
        io.grpc.stub.StreamObserver<ShippingOperations.ShipResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getShipProductMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void trackShipment(ShippingOperations.Day request,
        io.grpc.stub.StreamObserver<ShippingOperations.Shipment> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getTrackShipmentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ShippingServiceBlockingStub extends io.grpc.stub.AbstractStub<ShippingServiceBlockingStub> {
    private ShippingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<ShippingOperations.Shipment> trackShipment(
        ShippingOperations.Day request) {
      return blockingServerStreamingCall(
          getChannel(), getTrackShipmentMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ShippingServiceFutureStub extends io.grpc.stub.AbstractStub<ShippingServiceFutureStub> {
    private ShippingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_TRACK_SHIPMENT = 0;
  private static final int METHODID_SHIP_PRODUCT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ShippingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ShippingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRACK_SHIPMENT:
          serviceImpl.trackShipment((ShippingOperations.Day) request,
              (io.grpc.stub.StreamObserver<ShippingOperations.Shipment>) responseObserver);
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
        case METHODID_SHIP_PRODUCT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.shipProduct(
              (io.grpc.stub.StreamObserver<ShippingOperations.ShipResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ShippingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ShippingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ShippingOperations.ShippingOperationServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ShippingService");
    }
  }

  private static final class ShippingServiceFileDescriptorSupplier
      extends ShippingServiceBaseDescriptorSupplier {
    ShippingServiceFileDescriptorSupplier() {}
  }

  private static final class ShippingServiceMethodDescriptorSupplier
      extends ShippingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ShippingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ShippingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ShippingServiceFileDescriptorSupplier())
              .addMethod(getShipProductMethod())
              .addMethod(getTrackShipmentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
