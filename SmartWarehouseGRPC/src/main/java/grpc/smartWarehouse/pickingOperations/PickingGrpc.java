package grpc.smartWarehouse.pickingOperations;

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
    comments = "Source: PickingOperation.proto")
public final class PickingGrpc {

  private PickingGrpc() {}

  public static final String SERVICE_NAME = "pickingOperations.Picking";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.BinRequest,
      grpc.smartWarehouse.pickingOperations.BinResponse> getGetBinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBin",
      requestType = grpc.smartWarehouse.pickingOperations.BinRequest.class,
      responseType = grpc.smartWarehouse.pickingOperations.BinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.BinRequest,
      grpc.smartWarehouse.pickingOperations.BinResponse> getGetBinMethod() {
    io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.BinRequest, grpc.smartWarehouse.pickingOperations.BinResponse> getGetBinMethod;
    if ((getGetBinMethod = PickingGrpc.getGetBinMethod) == null) {
      synchronized (PickingGrpc.class) {
        if ((getGetBinMethod = PickingGrpc.getGetBinMethod) == null) {
          PickingGrpc.getGetBinMethod = getGetBinMethod = 
              io.grpc.MethodDescriptor.<grpc.smartWarehouse.pickingOperations.BinRequest, grpc.smartWarehouse.pickingOperations.BinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pickingOperations.Picking", "getBin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.smartWarehouse.pickingOperations.BinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.smartWarehouse.pickingOperations.BinResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PickingMethodDescriptorSupplier("getBin"))
                  .build();
          }
        }
     }
     return getGetBinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.PickRequest,
      grpc.smartWarehouse.pickingOperations.PickResponse> getPickProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pickProduct",
      requestType = grpc.smartWarehouse.pickingOperations.PickRequest.class,
      responseType = grpc.smartWarehouse.pickingOperations.PickResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.PickRequest,
      grpc.smartWarehouse.pickingOperations.PickResponse> getPickProductMethod() {
    io.grpc.MethodDescriptor<grpc.smartWarehouse.pickingOperations.PickRequest, grpc.smartWarehouse.pickingOperations.PickResponse> getPickProductMethod;
    if ((getPickProductMethod = PickingGrpc.getPickProductMethod) == null) {
      synchronized (PickingGrpc.class) {
        if ((getPickProductMethod = PickingGrpc.getPickProductMethod) == null) {
          PickingGrpc.getPickProductMethod = getPickProductMethod = 
              io.grpc.MethodDescriptor.<grpc.smartWarehouse.pickingOperations.PickRequest, grpc.smartWarehouse.pickingOperations.PickResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pickingOperations.Picking", "pickProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.smartWarehouse.pickingOperations.PickRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.smartWarehouse.pickingOperations.PickResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PickingMethodDescriptorSupplier("pickProduct"))
                  .build();
          }
        }
     }
     return getPickProductMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PickingStub newStub(io.grpc.Channel channel) {
    return new PickingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PickingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PickingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PickingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PickingFutureStub(channel);
  }

  /**
   */
  public static abstract class PickingImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *check bin (storage)location number of items by sku(product number)
     * </pre>
     */
    public void getBin(grpc.smartWarehouse.pickingOperations.BinRequest request,
        io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.BinResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBinMethod(), responseObserver);
    }

    /**
     */
    public void pickProduct(grpc.smartWarehouse.pickingOperations.PickRequest request,
        io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.PickResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPickProductMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.smartWarehouse.pickingOperations.BinRequest,
                grpc.smartWarehouse.pickingOperations.BinResponse>(
                  this, METHODID_GET_BIN)))
          .addMethod(
            getPickProductMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.smartWarehouse.pickingOperations.PickRequest,
                grpc.smartWarehouse.pickingOperations.PickResponse>(
                  this, METHODID_PICK_PRODUCT)))
          .build();
    }
  }

  /**
   */
  public static final class PickingStub extends io.grpc.stub.AbstractStub<PickingStub> {
    private PickingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PickingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PickingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PickingStub(channel, callOptions);
    }

    /**
     * <pre>
     *check bin (storage)location number of items by sku(product number)
     * </pre>
     */
    public void getBin(grpc.smartWarehouse.pickingOperations.BinRequest request,
        io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.BinResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pickProduct(grpc.smartWarehouse.pickingOperations.PickRequest request,
        io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.PickResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPickProductMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PickingBlockingStub extends io.grpc.stub.AbstractStub<PickingBlockingStub> {
    private PickingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PickingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PickingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PickingBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *check bin (storage)location number of items by sku(product number)
     * </pre>
     */
    public grpc.smartWarehouse.pickingOperations.BinResponse getBin(grpc.smartWarehouse.pickingOperations.BinRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBinMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.smartWarehouse.pickingOperations.PickResponse pickProduct(grpc.smartWarehouse.pickingOperations.PickRequest request) {
      return blockingUnaryCall(
          getChannel(), getPickProductMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PickingFutureStub extends io.grpc.stub.AbstractStub<PickingFutureStub> {
    private PickingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PickingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PickingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PickingFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *check bin (storage)location number of items by sku(product number)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.smartWarehouse.pickingOperations.BinResponse> getBin(
        grpc.smartWarehouse.pickingOperations.BinRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.smartWarehouse.pickingOperations.PickResponse> pickProduct(
        grpc.smartWarehouse.pickingOperations.PickRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPickProductMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BIN = 0;
  private static final int METHODID_PICK_PRODUCT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PickingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PickingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BIN:
          serviceImpl.getBin((grpc.smartWarehouse.pickingOperations.BinRequest) request,
              (io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.BinResponse>) responseObserver);
          break;
        case METHODID_PICK_PRODUCT:
          serviceImpl.pickProduct((grpc.smartWarehouse.pickingOperations.PickRequest) request,
              (io.grpc.stub.StreamObserver<grpc.smartWarehouse.pickingOperations.PickResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PickingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PickingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.smartWarehouse.pickingOperations.PickingOperationServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Picking");
    }
  }

  private static final class PickingFileDescriptorSupplier
      extends PickingBaseDescriptorSupplier {
    PickingFileDescriptorSupplier() {}
  }

  private static final class PickingMethodDescriptorSupplier
      extends PickingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PickingMethodDescriptorSupplier(String methodName) {
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
      synchronized (PickingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PickingFileDescriptorSupplier())
              .addMethod(getGetBinMethod())
              .addMethod(getPickProductMethod())
              .build();
        }
      }
    }
    return result;
  }
}
