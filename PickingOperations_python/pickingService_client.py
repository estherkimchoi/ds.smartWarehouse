
from __future__ import print_function
import logging

import grpc

import pickingService_pb2
import pickingService_pb2_grpc


def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    with grpc.insecure_channel('localhost:1127') as channel:  # it creates channel on the localhost port and then
        stub = pickingService_pb2_grpc.PickingStub(channel) # from the channel, gets this stub
        binResponse = stub.getBin(pickingService_pb2.BinRequest(sku='YE98767', productName='Bike')) # on the stub method, pass parameter
        pickResponse = stub.pickProduct(pickingService_pb2.PickRequest(bin='T345', productName='TV', quantity=3))

    print(binResponse.bin) # print response
    print(pickResponse.resultMessage + pickResponse.pickId)


if __name__ == '__main__':
    logging.basicConfig()
    run()